# Containerization Plan

This document summarizes the Docker strategy for the HeyTwin platform.

## Services

| Service                     | Dockerfile                      | Base image                         | Ports                               | Notes                                                                                                                         |
| --------------------------- | ------------------------------- | ---------------------------------- | ----------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| Backend API (Spring Boot)   | `backend/Dockerfile`            | `eclipse-temurin:21-jre` (runtime) | 8080 (host 8081)                    | Multi-stage build using `maven:3.9-eclipse-temurin-21` for compilation. Copies `backend/.env` values at runtime via env vars. |
| AI microservice (FastAPI)   | `ai-service/Dockerfile`         | `python:3.11-slim`                 | 8000                                | Installs `requirements.txt`, runs `uvicorn app.main:app --host 0.0.0.0 --port 8000`.                                          |
| Frontend SPA (React + Vite) | `frontend/Dockerfile`           | `nginx:1.27-alpine`                | 4173 (dev) / 5173 (dev) / 80 (prod) | Node 20 build stage compiles static assets, served via NGINX.                                                                 |
| PostgreSQL                  | Docker Hub `postgres:15-alpine` | n/a                                | 5432                                | Uses initialized volume + `db/schema.sql`.                                                                                    |

## Docker Compose

A new `docker-compose.yml` at the repo root orchestrates the stack:

- `db`: PostgreSQL 15 with volume `pgdata` and environment variables for user/password/db.
- `ai-service`: builds from `ai-service/Dockerfile`, depends on `db` for future ML storage, exposes 8000.
- `backend`: builds from `backend/Dockerfile`, waits for `db` and `ai-service`, exposes 8080 internally (mapped to host 8081), passes JDBC + AI URLs via env vars.
- `frontend`: builds from `frontend/Dockerfile`, serves static bundle at port 5173 (mapped to host 5173) or 80.

All services share a default bridge network `heytwin-net`. Compose file also mounts:

- `backend/.env.example` → container environment (converted to real env vars when running `docker compose`).
- `db/schema.sql` executed automatically via an entrypoint script to bootstrap the schema if the database volume is empty.

## Local Workflow

1. Build and run everything:

```bash
docker compose build
docker compose up -d
```

2. Tail logs:

```bash
docker compose logs -f backend ai-service frontend
```

3. Stop and clean:

```bash
docker compose down -v
```

## Future Enhancements

- Publish pre-built images to a registry with GitHub Actions.
- Add `docker compose -f docker-compose.yml -f docker-compose.prod.yml` overlay for production secrets/resources.
- Replace environment variables with secrets managers (Azure Key Vault, AWS Secrets Manager, etc.).
