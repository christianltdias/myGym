package com.services;

import java.util.List;
import java.util.Optional;

import com.domain.User;
import com.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

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

}