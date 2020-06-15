package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.SerieItem;
import com.exercisetracker.repositories.SerieItemRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SerieItemService {
    
    @Autowired
    private SerieItemRepository SerieItemRepository;

    public SerieItem find(Integer id) {
		Optional<SerieItem> obj = SerieItemRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + SerieItem.class.getName()));
	}

	public List<SerieItem> findAll() {
		return SerieItemRepository.findAll();
	}

    @Transactional
	public SerieItem insert(SerieItem obj) {
		obj.setId(null);
        obj = SerieItemRepository.save(obj);
        return obj;
	}

}