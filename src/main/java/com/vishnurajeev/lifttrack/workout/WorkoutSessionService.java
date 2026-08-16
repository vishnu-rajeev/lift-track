package com.vishnurajeev.lifttrack.workout;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    @Transactional
    public WorkoutSession startWorkout() {
        WorkoutSession workoutSession = new WorkoutSession(Instant.now());

        return workoutSessionRepository.save(workoutSession);
    }

    @Transactional
    public WorkoutSession finishWorkout(Long workoutId) {
        WorkoutSession workoutSession = workoutSessionRepository
                                        .findById(workoutId)
                                        .orElseThrow(() -> new WorkoutSessionNotFoundException(workoutId));
        workoutSession.finish();
        return workoutSession;
    }

    public List<WorkoutSessionResponse> getWorkoutHistory() {
        return workoutSessionRepository
                .findByFinishedAtIsNotNullOrderByStartedAtDesc()
                .stream()
                .map(workoutSession -> new WorkoutSessionResponse(
                        workoutSession.getId(),
                        workoutSession.getStartedAt(),
                        workoutSession.getFinishedAt()
                ))
                .toList();

    }
}
