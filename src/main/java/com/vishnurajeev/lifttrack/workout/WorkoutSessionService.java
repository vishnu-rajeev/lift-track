package com.vishnurajeev.lifttrack.workout;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutSetRepository workoutSetRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository,
                                 WorkoutExerciseRepository workoutExerciseRepository,
                                 WorkoutSetRepository workoutSetRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutSetRepository = workoutSetRepository;
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
                .map(this::toWorkoutSessionResponse)
                .toList();

    }

    private WorkoutSessionResponse toWorkoutSessionResponse(WorkoutSession workoutSession) {
        List<WorkoutExerciseHistoryResponse> exercises = workoutExerciseRepository
                .findByWorkoutSessionIdOrderByPositionAsc(workoutSession.getId())
                .stream()
                .map(this::toWorkoutExerciseHistoryResponse)
                .toList();

        return new WorkoutSessionResponse(
                workoutSession.getId(),
                workoutSession.getStartedAt(),
                workoutSession.getFinishedAt(),
                exercises
        );
    }

    private WorkoutExerciseHistoryResponse toWorkoutExerciseHistoryResponse(WorkoutExercise workoutExercise) {
        List<WorkoutSetHistoryResponse> sets = workoutSetRepository
                .findByWorkoutExerciseIdOrderBySetNumberAsc(workoutExercise.getId())
                .stream()
                .map(workoutSet -> new WorkoutSetHistoryResponse(
                        workoutSet.getSetNumber(),
                        workoutSet.getWeightKg(),
                        workoutSet.getReps()
                ))
                .toList();

        return new WorkoutExerciseHistoryResponse(
                workoutExercise.getExercise().getId(),
                workoutExercise.getExercise().getName(),
                workoutExercise.getPosition(),
                sets
        );
    }
}
