# HeyTwin – Digital Twin Adaptive Learning Platform

Modular AI tutoring stack that combines a Spring Boot API, FastAPI ML microservice, React SPA, and PostgreSQL schema to deliver diagnostic testing, adaptive practice, analytics, and a rule-based digital twin.

## Repo layout

```
Heytwin/
├─ backend/        # Spring Boot API (auth, diagnostics, adaptive, digital twin, analytics)
├─ ai-service/     # FastAPI + scikit-learn prediction service
├─ frontend/       # React + Vite dashboard & session UI
├─ db/             # PostgreSQL schema and migrations
└─ docs/           # Architecture, API contracts, digital twin formulas
```

Key reference docs:

- `docs/architecture.md`
- `docs/api-contracts.md`
- `docs/digital-twin-formulas.md`

## Requirements

- **Git** 2.40+ (used for cloning and keeping the repo up to date)
- **Java 21 LTS** (backend now compiles with Java 21.0.8 — set `JAVA_HOME` accordingly)
- **Maven 3.9+** (3.9.10 verified locally; wrapper not yet checked in)
- **Node.js 18+** and npm (tested with Node 23.10.0 / npm 10.9.2)
- **Python 3.10+** (ai-service uses 3.11.3 in local testing)
- **PostgreSQL 15+** (or a compatible managed instance)
- **PowerShell 5.1+** or any Unix-like shell for running the listed commands

> Verified toolchain snapshot (Nov 2025): Java 21.0.8 (Temurin/Microsoft build), Maven 3.9.10, Node 23.10.0, npm 10.9.2, Python 3.11.3. If you use other versions, keep them within the listed minimums.

> Tip: if you prefer containers, you can satisfy these requirements by running each service inside Docker once images are added to the repo roadmap.

## Getting started

1. Clone the repository and move into it:
   ```powershell
   git clone https://github.com/mithun0524/HeyTwin.git
   cd HeyTwin
   ```
2. Create `backend/.env` and add your database connection string (`DB_URL`), username, password, and any JWT/AI secrets. The file is ignored by git, so keep a local backup if needed.
3. Verify each requirement is installed (`java -version`, `mvn -v`, `node -v`, `python --version`, `psql --version`). Confirm that `java -version` reports a 21.x build before running Maven.
4. Follow the service-specific instructions below to start the database, backend, AI service, and frontend.

## Bringing the platform online

1. **Prep the database** – Start PostgreSQL locally (or connect to your managed instance) and apply `db/schema.sql`.
2. **Launch the AI microservice** – Create/activate `ai-service/.venv`, install requirements, and run `uvicorn app.main:app --reload --port 8000`.
3. **Run the Spring Boot API** – Export `DB_*`, `JWT_*`, and `AI_SERVICE_URL=http://localhost:8000`, then run `mvn spring-boot:run` from `backend/` using Java 21.
4. **Start the React SPA** – From `frontend/`, run `npm install && npm run dev` (or `npm run build && npm run preview` for a production bundle).
5. **Smoke test the stack** – Visit `http://localhost:5173`, verify the dashboard loads data from `/api`, and watch the backend logs for AI-service calls.

Once these services are healthy, you can iterate on adaptive logic, the analytics UI, or the ML models with live feedback.

## 1. Database bootstrap

1. Create a database (default name `heytwin`).
2. Set credentials in environment variables used by Spring: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.
3. Apply `db/schema.sql` (psql example):
   ```psql
   psql $DB_URL -f db/schema.sql
   ```

## 2. Backend API (Spring Boot)

```powershell
cd backend
mvn clean package     # or mvn test to run unit tests (once Maven is installed)
mvn spring-boot:run   # serves at http://localhost:8080/api
```

If you have multiple JDKs installed, run Maven with `JAVA_HOME=/path/to/jdk-21 mvn ...` so the compiler uses Java 21 features.

Important environment variables (defaults live in `application.yml`):

- `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
- `JWT_SECRET`, `JWT_EXPIRATION`
- `AI_SERVICE_URL` (default `http://localhost:8000`)
- `AI_SERVICE_KEY`

> **Heads-up:** Maven is not installed in this workspace yet, so the backend build will fail until Maven 3.9+ is added to your path. Install it or add the Maven Wrapper before running the commands above.

## 3. AI prediction microservice (FastAPI)

```powershell
cd ai-service
python -m venv .venv
.\.venv\Scripts\activate
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000
```

- `POST /train` trains RandomForest or LogisticRegression on provided or synthetic data (see `app/synthetic.py`).
- `POST /predict` batches correctness probabilities; auto-trains with synthetic data on first call if no model exists.

## 4. Frontend SPA (React + Vite)

```powershell
cd frontend
npm install
npm run dev          # http://localhost:5173
npm run build        # production bundle (verified)
```

If `npm run build` fails with `Permission denied` for Vite on macOS/Linux, run `chmod +x node_modules/.bin/vite` once after installing dependencies. This happens when your umask removes the execute bit from installed binaries.

To keep dependencies patched, run `npm audit` periodically (or `npm audit fix --force` in a disposable branch) and review the reported vulnerabilities before promoting builds.
The dashboard visualizes mastery, learning curves, topic pies, achievements, reminders, and anchors the diagnostic/practice workflows. Axios calls expect the backend to be proxied under `/api`.

## Quality gates

- **Backend:** `mvn test` (once Maven is installed) and `mvn -DskipTests=false clean package` before releases.
- **Frontend:** `npm run build` (already passing) and optional linting via `npx eslint src --max-warnings=0` once eslint is configured.
- **AI service:** add unit tests via `pytest` (not yet scaffolded); run `uvicorn` locally plus `curl http://localhost:8000/health` for smoke tests.

## Environment matrix

| Service    | Port          | Main command                    | Notes                                          |
| ---------- | ------------- | ------------------------------- | ---------------------------------------------- |
| Backend    | 8080 (`/api`) | `mvn spring-boot:run`           | Requires PostgreSQL + AI service URL           |
| AI service | 8000          | `uvicorn app.main:app --reload` | Auto-trains on synthetic data if needed        |
| Frontend   | 5173          | `npm run dev`                   | Configure proxy or set `VITE_API_URL` (future) |
| PostgreSQL | 5432          | `psql`                          | Apply `db/schema.sql`                          |

## Roadmap & next steps

- Install Maven or commit the Maven Wrapper so CI/backends can build without manual setup.
- Add automated tests (JUnit, pytest, React Testing Library) to cover the adaptive and diagnostic flows.
- Containerize services and add a `docker-compose.yml` for one-command local orchestration.
- Wire real data into the AI `/train` endpoint and schedule retraining jobs.
- Add CI (e.g., GitHub Actions) that runs `mvn verify`, `pytest`, and `npm run build` on every pull request using Java 21.
- Track frontend bundle size by enabling Vite's analyzer or splitting large routes with `React.lazy`/dynamic imports.
- Script environment bring-up with `make` or `taskfile` targets (`db-up`, `api-dev`, `ai-dev`, `web-dev`) to reduce manual steps.

Let me know when you want to tackle deployments, CI/CD, or containerization.
