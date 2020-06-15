package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Exercise;
import com.exercisetracker.repositories.ExerciseRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository ExerciseRepository;

    public Exercise find(Integer id) {
		Optional<Exercise> obj = ExerciseRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Exercise.class.getName()));
	}

	public List<Exercise> findAll() {
		return ExerciseRepository.findAll();
	}

    @Transactional
	public Exercise insert(Exercise obj) {
		obj.setId(null);
        obj = ExerciseRepository.save(obj);
        return obj;
	}

}