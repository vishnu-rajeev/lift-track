CREATE TABLE workout_sessions
(
    id          BIGSERIAL PRIMARY KEY,
    started_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    finished_at TIMESTAMPTZ,

    CONSTRAINT chk_workout_finish_time
        CHECK (
            finished_at IS NULL
                OR finished_at >= started_at
            )
);