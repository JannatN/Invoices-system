package com.invoice.controllers;

import java.text.ParseException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.invoice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.controllers.dto.UserDto;
import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * @param page
     * @return
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public Page<UserDto> findPaginatedUsers(Pageable page) {
        return Mapper.convertToDtoUser(userService.findPaginatedUsers(page));
    }

    /**
     *
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole(" + "'ADMIN') or hasRole('USER') or hasRole('AUDITOR') ")
    public UserDto getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return Mapper.convertToDto(userService.getUserById(userId));
    }

    /**
     *
     * @param userId
     * @param userDetails
     * @return
     * @throws ResourceNotFoundException
     * @throws ParseException
     */
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('AUDITOR')")
    public UserDto updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody UserDto userDetails)
            throws ResourceNotFoundException, ParseException {
        User user = Mapper.convertToEntity(userDetails);
        return Mapper.convertToDto(userService.updateUser(userId, user));
    }

    /**
     *
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return userService.deleteUser(userId);
    }

}
