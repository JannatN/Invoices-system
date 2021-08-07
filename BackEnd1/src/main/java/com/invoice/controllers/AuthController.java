package com.invoice.controllers;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.invoice.controllers.dto.ItemDto;
import com.invoice.controllers.dto.LoginDto;
import com.invoice.controllers.dto.SignUpDto;
import com.invoice.entities.Item;
import com.invoice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.invoice.repositories.*;
import com.invoice.entities.ERole;
import com.invoice.entities.Role;
import com.invoice.entities.User;
import com.invoice.payload.request.LoginRequest;
import com.invoice.payload.request.SignupRequest;
import com.invoice.payload.response.JwtResponse;
import com.invoice.payload.response.MessageResponse;
import com.invoice.security.services.UserDetailsImpl;
import com.invoice.security.jwt.JwtUtils;
import com.invoice.services.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param loginRequest
     * @param response
     * @return
     * @throws ParseException
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest, HttpServletResponse response) throws ParseException {
        LoginRequest req = Mapper.convertToEntity(loginRequest);
        return Mapper.convertToDto(authService.authenticateUser(req, response));
    }

    /**
     * @param signUpRequest
     * @return
     * @throws ParseException
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) throws ParseException {
        SignupRequest req = Mapper.convertToEntity(signUpRequest);
        return Mapper.convertToDto(authService.registerUser(req));

    }


}