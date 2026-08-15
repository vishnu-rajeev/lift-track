package com.vishnurajeev.lifttrack.workout;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkoutSetService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutSetRepository workoutSetRepository;

    public WorkoutSetService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutSetRepository workoutSetRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutSetRepository = workoutSetRepository;
    }

    @Transactional
    public WorkoutSet addSet (Long workoutExerciseId, AddWorkoutSetRequest request) {
        WorkoutExercise workoutExercise = workoutExerciseRepository
                                            .findById(workoutExerciseId)
                                            .orElseThrow(() -> new WorkoutExerciseNotFoundException(workoutExerciseId));

        WorkoutSet workoutSet = new WorkoutSet(
                workoutExercise,
                request.setNumber(),
                request.weightKg(),
                request.reps()
        );

        return workoutSetRepository.save(workoutSet);
    }
}
