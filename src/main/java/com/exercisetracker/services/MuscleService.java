package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Muscle;
import com.exercisetracker.repositories.MuscleRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MuscleService {
    
    @Autowired
    private MuscleRepository MuscleRepository;

    public Muscle find(Integer id) {
		Optional<Muscle> obj = MuscleRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Muscle.class.getName()));
	}

	public List<Muscle> findAll() {
		return MuscleRepository.findAll();
	}

    @Transactional
	public Muscle insert(Muscle obj) {
		obj.setId(null);
        obj = MuscleRepository.save(obj);
        return obj;
	}

}