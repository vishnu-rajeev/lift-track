package com.vishnurajeev.lifttrack.workout;

import com.vishnurajeev.lifttrack.exercise.Exercise;
import com.vishnurajeev.lifttrack.exercise.ExerciseNotFoundException;
import com.vishnurajeev.lifttrack.exercise.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExerciseProgressService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseProgressService(WorkoutExerciseRepository workoutExerciseRepository,
                                   WorkoutSetRepository workoutSetRepository,
                                   ExerciseRepository exerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutSetRepository = workoutSetRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseProgressResponse getExerciseProgress(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException(exerciseId));

        List<ExerciseProgressPointResponse> history =
                workoutExerciseRepository
                .findByExerciseIdAndWorkoutSessionFinishedAtIsNotNullOrderByWorkoutSessionStartedAtAsc(exerciseId)
                .stream()
                .map(this::toProgressPoint)
                .toList();

        return new ExerciseProgressResponse(exercise.getId(), exercise.getName(), history);

    }

    private ExerciseProgressPointResponse toProgressPoint(WorkoutExercise workoutExercise) {
        List<WorkoutSet> sets = workoutSetRepository.findByWorkoutExerciseIdOrderBySetNumberAsc(workoutExercise.getId());

        BigDecimal maxWeight = sets
                .stream()
                .map(WorkoutSet::getWeightKg)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalVolume = sets.stream()
                .map(set -> set.getWeightKg().multiply(BigDecimal.valueOf(set.getReps())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

                return new ExerciseProgressPointResponse(
                        workoutExercise.getWorkoutSession().getId(),
                        workoutExercise.getWorkoutSession().getStartedAt(),
                        maxWeight,
                        totalVolume);
    }
}
