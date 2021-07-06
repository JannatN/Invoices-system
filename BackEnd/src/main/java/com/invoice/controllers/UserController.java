package com.invoice.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username, String firstname) {
		return userService.getAllUsers(username, firstname);
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole(" + "'ADMIN') or hasRole('USER') or hasRole('AUDITOR') ")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		return userService.getUserById(userId);
	}

	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public User createUser(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('AUDITOR')")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		return userService.updateUser(userId, userDetails);
	}

	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		return userService.deleteUser(userId);
	}
}
