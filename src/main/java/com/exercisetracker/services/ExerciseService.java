package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Exercise;
import com.exercisetracker.repositories.ExerciseRepository;
import com.exercisetracker.services.exceptions.DataIntegrityException;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	
	public Exercise update(Exercise newObj) {
		Exercise obj = find(newObj.getId());
		return ExerciseRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			ExerciseRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Exercise com entidades entrelaçadas");
		}
	}

	public Page<Exercise> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ExerciseRepository.findAll(pageRequest);
	}


}