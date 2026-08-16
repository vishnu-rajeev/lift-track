package com.vishnurajeev.lifttrack.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    List<WorkoutSession> findByFinishedAtIsNotNullOrderByStartedAtDesc();
}
