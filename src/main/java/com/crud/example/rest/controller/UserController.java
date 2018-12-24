package com.crud.example.rest.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crud.example.entity.User;
import com.crud.example.exception.UserNotFound;
import com.crud.example.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new UserNotFound("id-" + id);
		}

		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@PostMapping("/login")
	@ResponseStatus(value = HttpStatus.OK)
	public String login(@RequestBody HashMap<String, String> map) {
		userService.login(map);
		return "user logged in successfully";
	}
}
