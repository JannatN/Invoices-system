package com.invoice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//	public List<User> getAllUsers(String username) {
//		try {
//			List<User> users = new ArrayList<User>();
//
//			if (username == null)
//				userRepository.findAll().forEach(users::add);
//			else
//				userRepository.findByusername(username).forEach(users::add);
//
//			if (users.isEmpty()) {
//				return userRepository.findAll();
//
//			}
//			return userRepository.findAll();
////			return new ResponseEntity<>(users, HttpStatus.OK);
//		} catch (Exception e) {
//			return null;
//		}
//	}

//	public ResponseEntity<User> getUserById(Long userId) throws ResourceNotFoundException {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//		return ResponseEntity.ok().body(user);
//	}
	public User getUserById(Long userId) throws ResourceNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

	}

	public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

		user.setEmail(userDetails.getEmail());
		user.setUsername(userDetails.getUsername());
		user.setFirstname(userDetails.getFirstname());
		user.setLastname(userDetails.getLastname());
		user.setAddress(userDetails.getAddress());
		user.setPhoneNumber(userDetails.getPhoneNumber());
//		final User updatedUser = userRepository.save(user);
		return userRepository.save(user);
	}

	public Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}