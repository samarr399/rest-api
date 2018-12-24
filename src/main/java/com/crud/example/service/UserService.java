package com.crud.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.example.entity.User;
import com.crud.example.exception.UserNotFound;
import com.crud.example.repository.UserRepository;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository repository;
	private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> userinOptional = repository.findById(id);
		if (!userinOptional.isPresent()) {
			throw new UserNotFound("User is not found with given id");
		}
		return userinOptional.get();
	}

	public String save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
		return "hello";
	}

	public void delete(Long id) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFound("User not found with given id");
		}
		repository.delete(user.get());
	}

	public boolean login(HashMap<String, String> map) {
		User user = repository.findByName(map.get("username"));
		boolean authenticate = encoder.matches(map.get("password"), user.getPassword());
		if(!authenticate) {
			throw new UserNotFound("username or password is invalid");
		}
		return authenticate;
	}
}
