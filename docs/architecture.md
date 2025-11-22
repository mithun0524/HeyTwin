# HeyTwin Digital Twin AI Adaptive Learning System

## High-Level Overview

The platform is a modular microservice ecosystem composed of:

1. **Spring Boot backend** (`backend/`)
   - Auth (JWT), diagnostic engine, adaptive pipeline, digital twin calculator, analytics APIs, AI-service client.
2. **FastAPI AI microservice** (`ai-service/`)
   - Scikit-learn models (RandomForestClassifier, LogisticRegression) for correctness probability predictions, /train + /predict endpoints, pickle persistence.
3. **React SPA frontend** (`frontend/`)
   - Dashboard, diagnostic flow, practice sessions, mastery analytics, spaced repetition reminders, achievements.
4. **SQL database (PostgreSQL preferred)** (`db/`)
   - Users, topics, questions, responses, student topic profiles, achievements, model metadata.

## ASCII Architecture Diagram

```
+-----------------------+             +-----------------------------+
|  React Frontend (SPA) |<---JWT----->| Spring Boot API Gateway     |
|  Dashboard / Sessions |    REST     | Auth, Diagnostics, Adaptive |
+-----------+-----------+             +---------------+-------------+
            |                                             |
            |                                             | Batched feature payloads
            |                                             v
            |                                   +--------------------+
            |                                   | FastAPI AI Service |
            |                                   | /train & /predict  |
            |                                   +---------+----------+
            |                                             |
            |                                   Model pickle storage
            |                                             |
            v                                             v
+-----------------------+                      +---------------------+
| PostgreSQL Database   |<-------------------->| Data Access Layer   |
| users/topics/...      |    JPA/Hibernate     | Repos + Twin stored |
+-----------------------+                      +---------------------+
```

## Data Flow Snapshot (Adaptive Session)

1. Student clicks **Start Practice Session** (frontend → `/api/practice/session/start`).
2. Backend authenticates JWT, loads student digital twin metrics.
3. Backend fetches 10–20 candidate questions (topic coverage + difficulty mix).
4. Backend builds feature vectors per question & student state.
5. Backend calls FastAPI `/predict` with `[ { studentId, questionId, features... } ]`.
6. AI service returns probabilities `[ { "questionId": 12, "prob": 0.81 }, ... ]`.
7. Backend sorts into high/medium/low, composes balanced 10-question set.
8. Frontend renders questions sequentially; responses posted to `/api/practice/session/{id}/response`.
9. Backend persists responses, updates rule-based digital twin metrics (accuracy, avg time, difficulty slope, consistency, forgetting score).
10. Analytics endpoints feed mastery graphs, learning curves, topic pie, estimated exam score, spaced repetition reminders.

## Folder Structure Blueprint

```
Heytwin/
  backend/
    src/main/java/com/heytwin/
      HeytwinApplication.java
      config/                # SecurityConfig, JwtProvider, CORS
      auth/
      diagnostic/
      adaptive/
      digitaltwin/
      analytics/
      achievement/
      client/                # AI service REST client
      domain/
        entity/
        repository/
      dto/
      util/
    src/main/resources/
      application.properties
      schema.sql (optional auto-init)
    pom.xml

  ai-service/
    app/
      main.py
      api/
        train.py
        predict.py
      models/
        classifier.py
      services/
        trainer.py
        predictor.py
      schemas.py
      config.py
    data/
      synthetic_generator.py
      model.pkl
    requirements.txt

  frontend/
    src/
      api/
      components/
        Dashboard/
        Diagnostic/
        Practice/
        Charts/
        Achievements/
        Reminders/
      hooks/
      state/
      App.jsx
      index.jsx
    public/
      index.html
    package.json

  db/
    schema.sql
    migrations/

  docs/
    architecture.md
    api-contracts.md
    digital-twin-formulas.md
```

## Technology Stack & Cross-Cutting Concerns

- **Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA, MapStruct (optional), OpenFeign/WebClient, Lombok.
- **AI Service:** Python 3.10, FastAPI, scikit-learn, pandas, numpy, joblib/pickle.
- **Frontend:** React 18 + Vite (fast dev) or CRA, Recharts/Victory for graphs, Axios, React Query/Zustand for state.
- **Database:** PostgreSQL 15; Flyway/Liquibase migrations.
- **Auth:** JWT access tokens + refresh tokens; roles `STUDENT`, `ADMIN`.
- **Observability:** Spring Actuator, combined request tracing IDs in logs.

## Deployment Considerations

- Containerize each service (Docker) with docker-compose for local dev.
- Use environment variables for DB credentials, AI service URL, JWT secrets.
- Introduce message queue later (e.g., RabbitMQ) if async model retraining needed.

## Next Steps

1. Finalize API contracts (`docs/api-contracts.md`).
2. Lock SQL schema (`db/schema.sql`) and migrations.
3. Scaffold backend + AI microservice + frontend.
4. Implement digital twin logic, diagnostic + adaptive flows.
5. Integrate charts and UI enhancements.
