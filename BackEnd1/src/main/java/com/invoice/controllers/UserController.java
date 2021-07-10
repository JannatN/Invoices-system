package com.invoice.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.dto.InvoiceDto;
import com.invoice.dto.UserDto;
import com.invoice.entities.Invoice;
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

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN') ")
	public List<UserDto> getAllUsers() {
		return convertToDto(userService.getAllUsers());
	}

//	@GetMapping("/users/{id}")
//	@PreAuthorize("hasRole(" + "'ADMIN') or hasRole('USER') or hasRole('AUDITOR') ")
//	public UserDto getUserById(@PathVariable(value = "id") Long userId)
//			throws ResourceNotFoundException {
//		return convertToDto(userService.getUserById(userId));
//	}
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole(" + "'ADMIN') or hasRole('USER') or hasRole('AUDITOR') ")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		return userService.getUserById(userId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public UserDto createUser(@Valid @RequestBody UserDto userDto) throws ParseException {
		User user = convertToEntity(userDto);
		ResponseEntity<User> userCreated = userService.saveUser(user);
		return convertToDto(userCreated);
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

	private UserDto convertToDto(ResponseEntity<User> responseEntity) {
		UserDto userDto = modelMapper.map(responseEntity, UserDto.class);
		return userDto;
	}

	private User convertToEntity(@Valid UserDto userDto) throws ParseException {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	private List<UserDto> convertToDto(List<User> allUsers) {
		List<UserDto> userDtoList = mapList(allUsers, UserDto.class);
		return userDtoList;
	}

	<S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}
}
