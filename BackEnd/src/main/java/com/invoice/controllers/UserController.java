package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.UserRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllEmployees() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public User createEmployee(@Valid @RequestBody User employee) {
		return userRepository.save(employee);
	}

	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

		user.setEmail(userDetails.getEmail());
		user.setUsername(userDetails.getUsername());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

//	@DeleteMapping("/users/{id}")
//	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
//			throws ResourceNotFoundException {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + employeeId));
//
//		userRepository.delete(user);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}
}
