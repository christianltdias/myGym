package com.exercisetracker.services;

import java.util.List;
import java.util.Optional;

import com.exercisetracker.domain.User;
import com.exercisetracker.repositories.UserRepository;
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
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User find(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

    @Transactional
	public User insert(User obj) {
		obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
	}

	public User update(User newObj) {
		User obj = find(newObj.getId());
		updateData(newObj, obj);
		return userRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma User com entidades entrelaçadas");
		}
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return userRepository.findAll(pageRequest);
	}

	private void updateData(User newObj, User obj) {
		obj.setName(newObj.getName());
		obj.setEmail(newObj.getEmail());
	}
}