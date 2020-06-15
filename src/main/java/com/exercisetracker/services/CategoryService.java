package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Category;
import com.exercisetracker.repositories.CategoryRepository;
import com.exercisetracker.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository CategoryRepository;

    public Category find(Integer id) {
		Optional<Category> obj = CategoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public List<Category> findAll() {
		return CategoryRepository.findAll();
	}

    @Transactional
	public Category insert(Category obj) {
		obj.setId(null);
        obj = CategoryRepository.save(obj);
        return obj;
	}

}