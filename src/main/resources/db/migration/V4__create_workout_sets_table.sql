CREATE TABLE workout_sets
(
    id BIGSERIAL PRIMARY KEY,
    workout_exercise_id BIGINT NOT NULL,
    set_number INTEGER NOT NULL,
    weight_kg NUMERIC(6,2) NOT NULL,
    reps INTEGER NOT NULL,

    CONSTRAINT fk_workout_sets_workout_exercise
        FOREIGN KEY (workout_exercise_id)
            REFERENCES workout_exercises (id)
            ON DELETE CASCADE,

    CONSTRAINT uq_workout_set_number
        UNIQUE (workout_exercise_id, set_number),

    CONSTRAINT chk_workout_set_number
        CHECK (set_number > 0),

    CONSTRAINT chk_workout_set_weight
        CHECK (weight_kg >= 0),

    CONSTRAINT chk_workout_set_reps
        CHECK (reps > 0)
);