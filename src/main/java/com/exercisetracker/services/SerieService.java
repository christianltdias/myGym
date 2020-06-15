package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Serie;
import com.exercisetracker.repositories.SerieRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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

}