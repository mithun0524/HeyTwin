-- HeyTwin Digital Twin Adaptive Learning Database Schema
-- Target database: PostgreSQL 15+

CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "citext";

CREATE TYPE difficulty_level AS ENUM ('EASY', 'MEDIUM', 'HARD');
CREATE TYPE question_type AS ENUM ('MULTIPLE_CHOICE', 'TRUE_FALSE', 'SHORT_ANSWER', 'FILL_BLANK');
CREATE TYPE session_type AS ENUM ('DIAGNOSTIC', 'PRACTICE', 'EXAM_SIM');
CREATE TYPE session_strategy AS ENUM ('BALANCED', 'WEAK_AREAS', 'MASTERY', 'CUSTOM');

-- USERS ---------------------------------------------------------------------
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(120) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('STUDENT', 'ADMIN')),
    grade_level VARCHAR(20),
    timezone VARCHAR(64) DEFAULT 'UTC',
    last_login_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_users_role ON users (role);

-- TOPICS --------------------------------------------------------------------
CREATE TABLE topics (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(120) NOT NULL,
    subject VARCHAR(80) NOT NULL,
    description TEXT,
    difficulty_band VARCHAR(20) DEFAULT 'MEDIUM',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX idx_topics_subject_name ON topics (subject, name);

-- QUESTIONS -----------------------------------------------------------------
CREATE TABLE questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    topic_id UUID NOT NULL REFERENCES topics(id) ON DELETE CASCADE,
    prompt TEXT NOT NULL,
    options JSONB NOT NULL,
    correct_answer JSONB NOT NULL,
    explanation TEXT,
    difficulty difficulty_level NOT NULL,
    question_type question_type NOT NULL,
    tags TEXT[] DEFAULT ARRAY[]::TEXT[],
    estimated_time_sec INT NOT NULL DEFAULT 45,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_questions_topic ON questions (topic_id);
CREATE INDEX idx_questions_difficulty ON questions (difficulty);

-- PRACTICE / DIAGNOSTIC SESSIONS -------------------------------------------
CREATE TABLE practice_sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    session_type session_type NOT NULL,
    selection_strategy session_strategy NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'IN_PROGRESS',
    question_count INT NOT NULL,
    ai_model_id VARCHAR(64),
    started_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    completed_at TIMESTAMPTZ,
    metadata JSONB DEFAULT '{}'::JSONB
);

CREATE INDEX idx_sessions_student ON practice_sessions (student_id, started_at DESC);

-- RESPONSES -----------------------------------------------------------------
CREATE TABLE responses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    session_id UUID NOT NULL REFERENCES practice_sessions(id) ON DELETE CASCADE,
    student_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    question_id UUID NOT NULL REFERENCES questions(id),
    selected_option JSONB,
    is_correct BOOLEAN,
    ai_pred_prob NUMERIC(5,4),
    time_taken_sec INT CHECK (time_taken_sec >= 0),
    submitted_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_responses_student ON responses (student_id);
CREATE INDEX idx_responses_question ON responses (question_id);
CREATE INDEX idx_responses_session ON responses (session_id);

-- STUDENT TOPIC PROFILES (Digital Twin core) --------------------------------
CREATE TABLE student_topic_profile (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    topic_id UUID NOT NULL REFERENCES topics(id) ON DELETE CASCADE,
    accuracy NUMERIC(5,4) NOT NULL DEFAULT 0,
    avg_time_sec NUMERIC(6,2) NOT NULL DEFAULT 0,
    difficulty_score NUMERIC(5,4) NOT NULL DEFAULT 0,
    consistency_score NUMERIC(5,4) NOT NULL DEFAULT 0,
    forgetting_score NUMERIC(5,4) NOT NULL DEFAULT 0,
    mastery_level NUMERIC(5,4) NOT NULL DEFAULT 0,
    recent_window JSONB DEFAULT '[]'::JSONB,
    last_updated TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX idx_profile_student_topic
    ON student_topic_profile (student_id, topic_id);

-- DIGITAL TWIN HISTORY (for analytics)
CREATE TABLE digital_twin_history (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    snapshot JSONB NOT NULL,
    captured_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_twin_history_student ON digital_twin_history (student_id, captured_at DESC);

-- ACHIEVEMENTS --------------------------------------------------------------
CREATE TABLE achievements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(60) UNIQUE NOT NULL,
    title VARCHAR(120) NOT NULL,
    description TEXT,
    criteria JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE student_achievements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    achievement_id UUID NOT NULL REFERENCES achievements(id) ON DELETE CASCADE,
    progress NUMERIC(5,4) NOT NULL DEFAULT 0,
    awarded BOOLEAN NOT NULL DEFAULT FALSE,
    awarded_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE (student_id, achievement_id)
);

-- AI MODEL TRAINING RUNS ----------------------------------------------------
CREATE TABLE model_training_runs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model_id VARCHAR(64) NOT NULL,
    model_type VARCHAR(40) NOT NULL,
    training_mode VARCHAR(20) NOT NULL,
    accuracy NUMERIC(5,4),
    f1 NUMERIC(5,4),
    data_version VARCHAR(60),
    artifact_path TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_model_runs_model_id ON model_training_runs (model_id);

-- TRIGGERS ------------------------------------------------------------------
CREATE OR REPLACE FUNCTION touch_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_users_updated
BEFORE UPDATE ON users
FOR EACH ROW EXECUTE FUNCTION touch_updated_at();

CREATE TRIGGER trg_student_profile_updated
BEFORE UPDATE ON student_topic_profile
FOR EACH ROW EXECUTE FUNCTION touch_updated_at();

CREATE TRIGGER trg_student_achievements_updated
BEFORE UPDATE ON student_achievements
FOR EACH ROW EXECUTE FUNCTION touch_updated_at();
