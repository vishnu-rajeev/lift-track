CREATE TABLE workout_exercises
(
    id                  BIGSERIAL PRIMARY KEY,
    workout_session_id  BIGINT NOT NULL,
    exercise_id         BIGINT NOT NULL,
    position            INTEGER NOT NULL,

    CONSTRAINT fk_workout_exercises_workout
        FOREIGN KEY (workout_session_id)
            REFERENCES workout_sessions (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_workout_exercises_exercise
        FOREIGN KEY (exercise_id)
            REFERENCES exercises (id),

    CONSTRAINT uq_workout_exercise_position
        UNIQUE(workout_session_id, position),

    CONSTRAINT chk_workout_exercise_position
        CHECK (position > 0)
);