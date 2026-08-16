package com.vishnurajeev.lifttrack.workout;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "workout_sessions")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    protected WorkoutSession() {
    }

    public WorkoutSession(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Long getId() {
        return id;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void finish() {
        this.finishedAt = Instant.now();
    }
}
