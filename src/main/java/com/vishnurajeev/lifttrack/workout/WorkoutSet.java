package com.vishnurajeev.lifttrack.workout;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "workout_sets")
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(name = "weight_kg", nullable = false, precision = 6, scale = 2)
    private BigDecimal weightKg;

    @Column(nullable = false)
    private Integer reps;

    protected WorkoutSet() {
    }

    public WorkoutSet(WorkoutExercise workoutExercise, Integer setNumber, BigDecimal weightKg, Integer reps) {
        this.workoutExercise = workoutExercise;
        this.setNumber = setNumber;
        this.weightKg = weightKg;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public Integer getReps() {
        return reps;
    }
}

