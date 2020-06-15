package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Program;
import com.exercisetracker.repositories.ProgramRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProgramService {
    
    @Autowired
    private ProgramRepository ProgramRepository;

    public Program find(Integer id) {
		Optional<Program> obj = ProgramRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Program.class.getName()));
	}

	public List<Program> findAll() {
		return ProgramRepository.findAll();
	}

    @Transactional
	public Program insert(Program obj) {
		obj.setId(null);
        obj = ProgramRepository.save(obj);
        return obj;
	}

}