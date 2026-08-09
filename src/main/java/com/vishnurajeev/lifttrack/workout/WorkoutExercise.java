package com.vishnurajeev.lifttrack.workout;

import com.vishnurajeev.lifttrack.exercise.Exercise;
import jakarta.persistence.*;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Integer position;

    protected WorkoutExercise() {

    }

    public WorkoutExercise(WorkoutSession workoutSession, Exercise exercise, Integer position) {
        this.workoutSession = workoutSession;
        this.exercise = exercise;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    public Integer getPosition() {
        return position;
    }
}
