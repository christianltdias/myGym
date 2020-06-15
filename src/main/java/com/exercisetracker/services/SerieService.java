package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Serie;
import com.exercisetracker.repositories.SerieRepository;
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
public class SerieService {
    
    @Autowired
    private SerieRepository SerieRepository;

    public Serie find(Integer id) {
		Optional<Serie> obj = SerieRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Serie.class.getName()));
	}

	public List<Serie> findAll() {
		return SerieRepository.findAll();
	}

    @Transactional
	public Serie insert(Serie obj) {
		obj.setId(null);
        obj = SerieRepository.save(obj);
        return obj;
	}

	
	public Serie update(Serie newObj) {
		Serie obj = find(newObj.getId());
		return SerieRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			SerieRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Serie com entidades entrelaçadas");
		}
	}

	public Page<Serie> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return SerieRepository.findAll(pageRequest);
	}
}