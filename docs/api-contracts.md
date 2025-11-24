# API Contracts

## Base URLs

- Backend (Spring Boot): `https://api.heytwin.local/v1`
- AI Service (FastAPI): `https://ai.heytwin.local`
- All backend responses wrap payloads using:

```json
{
  "timestamp": "2025-11-22T10:32:05Z",
  "status": 200,
  "path": "/api/practice/session/start",
  "data": {
    /* endpoint-specific payload */
  }
}
```

- Errors utilize RFC 7807 Problem Details structure.

---

## Authentication & User Management

### POST `/api/auth/register`

Registers a student account.

```json
// request
{
  "fullName": "Ava Patel",
  "email": "ava@example.com",
  "password": "S3cure!",
  "gradeLevel": "10",
  "role": "STUDENT" // optional, defaults to STUDENT
}

// response 201
{
  "userId": "ad2e1b84-...",
  "email": "ava@example.com",
  "role": "STUDENT"
}
```

### POST `/api/auth/login`

```json
// request
{
  "email": "ava@example.com",
  "password": "S3cure!"
}

// response 200
{
  "accessToken": "jwt...",
  "refreshToken": "jwt...",
  "expiresIn": 3600,
  "user": {
    "id": "ad2e1b84-...",
    "fullName": "Ava Patel",
    "role": "STUDENT"
  }
}
```

### POST `/api/auth/refresh`

```json
// request
{
  "refreshToken": "jwt..."
}

// response
{
  "accessToken": "new-jwt",
  "expiresIn": 3600
}
```

---

## Dashboard & Digital Twin Summary

### GET `/api/dashboard/overview`

Returns snapshot metrics rendered on the dashboard.

```json
{
  "diagnosticCompleted": true,
  "lastSession": {
    "sessionId": "sess-123",
    "completedAt": "2025-11-20T15:12:00Z",
    "score": 78
  },
  "twinSummary": {
    "overallMastery": 0.73,
    "strongTopics": ["Algebra", "Geometry"],
    "weakTopics": ["Trigonometry"],
    "estimatedExamScore": 82
  },
  "reminders": [{ "topic": "Trigonometry", "due": "2025-11-23" }]
}
```

### GET `/api/digital-twin/summary`

```json
{
  "studentId": "uuid",
  "metrics": {
    "accuracy": 0.74,
    "avgTimeSec": 31.2,
    "difficultyPerformance": {
      "easy": 0.9,
      "medium": 0.72,
      "hard": 0.48
    },
    "consistency": 0.68,
    "forgettingScore": 0.42
  },
  "topics": [
    {
      "topicId": "uuid",
      "mastery": 0.81,
      "recentTrend": "+0.04",
      "recommendation": "Maintain"
    }
  ]
}
```

### GET `/api/digital-twin/topics/{topicId}`

Detail view for a single topic.

### POST `/api/digital-twin/recalculate`

(Admin) Forces recalculation for given student(s).

```json
{
  "studentIds": ["uuid", "uuid"],
  "reasons": "backfill"
}
```

---

## Question Bank Ingestion

### POST `/api/admin/questions/sync`

- Requires `ROLE_ADMIN` JWT.
- Optional query parameter `amount` (default `ingestion.opentdb.default-amount`, max 100).
- Triggers an on-demand import from OpenTDB and returns the batch summary.

```json
// response 200
{
  "requested": 75,
  "fetched": 50,
  "inserted": 45,
  "skipped": 5
}
```

Scheduled imports run via `ingestion.opentdb.cron` (defaults to nightly at 02:00) and can be disabled by setting
`ingestion.opentdb.enabled=false`.

---

## Diagnostic Engine

### GET `/api/diagnostic/questions`

Returns 15 curated questions (mixed topics/difficulties).

```json
{
  "sessionId": "diag-2025-11-22-001",
  "questions": [
    {
      "questionId": "uuid",
      "topic": "Algebra",
      "difficulty": "MEDIUM",
      "questionType": "MULTIPLE_CHOICE",
      "prompt": "What is 2x + 5 = 11?",
      "options": ["x=2", "x=3", "x=4", "x=6"],
      "estimatedTimeSec": 45
    }
  ]
}
```

### POST `/api/diagnostic/responses`

Persists answers and triggers initial twin creation.

```json
{
  "sessionId": "diag-2025-11-22-001",
  "responses": [
    {
      "questionId": "uuid",
      "selectedOption": "x=3",
      "timeTakenSec": 37
    }
  ]
}
```

Response returns summary + baseline twin metrics.

---

## Practice & Adaptive Engine

### POST `/api/practice/session/start`

Starts adaptively curated session.

```json
{
  "mode": "BALANCED", // BALANCED | WEAK_AREAS | EXAM_SIM
  "topicFilter": ["uuid"],
  "targetDifficulty": ["EASY", "MEDIUM"],
  "count": 10
}
```

```json
// response
{
  "sessionId": "sess-123",
  "questions": [ { ... } ],
  "selectionRationale": {
    "highProb": 3,
    "mediumProb": 4,
    "lowProb": 3
  }
}
```

### POST `/api/practice/session/{sessionId}/response`

Stream responses during session.

```json
{
  "questionId": "uuid",
  "selectedOption": "A",
  "isSkipped": false,
  "timeTakenSec": 42
}
```

Returns updated progress + current mastery deltas.

### POST `/api/practice/session/{sessionId}/complete`

Marks session finished, recalculates twin.

---

## Analytics & Visualization

### GET `/api/analytics/mastery`

Returns data for mastery prediction graph.

```json
{
  "points": [
    { "timestamp": "2025-11-01", "mastery": 0.56 },
    { "timestamp": "2025-11-15", "mastery": 0.71 }
  ]
}
```

### GET `/api/analytics/learning-curve`

```json
{
  "sessions": [
    { "sessionId": "sess-100", "score": 0.62, "avgTimeSec": 38 },
    { "sessionId": "sess-101", "score": 0.74, "avgTimeSec": 31 }
  ]
}
```

### GET `/api/analytics/topic-pie`

```json
{
  "topics": [
    { "name": "Algebra", "mastery": 0.81 },
    { "name": "Trigonometry", "mastery": 0.42 }
  ]
}
```

### GET `/api/analytics/spaced-repetition`

Returns reminders list.

### GET `/api/analytics/achievements`

```json
{
  "earned": [{ "code": "STREAK_5", "awardedAt": "2025-11-19" }],
  "available": [{ "code": "FOCUS_MASTER", "progress": 0.6 }]
}
```

### GET `/api/analytics/estimated-score`

```json
{
  "estimatedScore": 83,
  "confidence": 0.72,
  "inputs": {
    "mastery": 0.74,
    "consistency": 0.69,
    "recentAverage": 0.78
  }
}
```

---

## AI Service (FastAPI)

Base URL `https://ai.heytwin.local`.

### POST `/train`

Triggers training on synthetic or latest DB snapshot.

```json
{
  "modelType": "RandomForest",
  "trainingMode": "SYNTHETIC", // SYNTHETIC | REAL
  "records": [
    {
      "topicMastery": 0.62,
      "recentAccuracy": 0.55,
      "avgTimeSec": 34,
      "difficulty": 2,
      "questionType": "MULTIPLE_CHOICE",
      "label": 0
    }
  ]
}
```

```json
{
  "modelId": "rf-20251122-01",
  "metrics": {
    "accuracy": 0.78,
    "f1": 0.75
  },
  "savedAt": "2025-11-22T10:40:00Z"
}
```

### POST `/predict`

Accepts batch of question-feature rows per student.

```json
{
  "modelId": "rf-20251122-01",
  "instances": [
    {
      "studentId": "uuid",
      "questionId": "uuid",
      "features": {
        "topicMastery": 0.71,
        "recentAccuracy": 0.68,
        "avgTimeSec": 29,
        "difficulty": 3,
        "questionType": "SHORT_ANSWER",
        "responseTimeTrend": -0.12
      }
    }
  ]
}
```

```json
{
  "predictions": [
    { "questionId": "uuid", "prob": 0.81 },
    { "questionId": "uuid", "prob": 0.39 }
  ],
  "modelId": "rf-20251122-01"
}
```

Errors return validation details plus `traceId` for observability.

---

## Notes

- All endpoints secured via JWT except `/api/auth/**` and health checks.
- Backend-to-AI requests include service token header `X-Service-Key`.
- Rate limiting applied per student for practice sessions (max 5 concurrent).
