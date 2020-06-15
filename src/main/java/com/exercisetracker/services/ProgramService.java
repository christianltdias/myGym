package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Program;
import com.exercisetracker.repositories.ProgramRepository;
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

	
	public Program update(Program newObj) {
		Program obj = find(newObj.getId());
		return ProgramRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			ProgramRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Program com entidades entrelaçadas");
		}
	}

	public Page<Program> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ProgramRepository.findAll(pageRequest);
	}


}