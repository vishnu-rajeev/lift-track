package com.vishnurajeev.lifttrack.exercise;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public Exercise createExercise(CreateExerciseRequest request) {
        String name = request.name().trim();
        String muscleGroup = request.muscleGroup().trim();

        if(exerciseRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateExerciseException(name);
        }

        Exercise exercise = new Exercise(
                name,
                muscleGroup
        );

        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if(exerciseOptional.isEmpty()) {
            throw new ExerciseNotFoundException(id);
        }
        return exerciseOptional.get();
    }
}
