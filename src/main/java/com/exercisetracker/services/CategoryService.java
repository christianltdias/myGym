package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.Category;
import com.exercisetracker.repositories.CategoryRepository;
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
	
	public Category update(Category newObj) {
		Category obj = find(newObj.getId());
		return CategoryRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			CategoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Category com entidades entrelaçadas");
		}
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return CategoryRepository.findAll(pageRequest);
	}

}