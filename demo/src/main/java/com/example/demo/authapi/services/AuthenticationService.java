package com.example.demo.authapi.services;

import com.example.demo.authapi.LoginUserDto;
import com.example.demo.authapi.RegisterUserDto;
import com.example.demo.model.Persona;
import com.example.demo.model.Phones;
import com.example.demo.repository.PersonaRepository;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	private final PersonaRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(PersonaRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Persona signup(RegisterUserDto input) {
		Persona user = new Persona();

		user.setName(input.getFullName());
		user.setEmail(input.getEmail());
	
		//user.setPhones(input.getPhones());
		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return userRepository.save(user);
	}

	public Persona authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}


	 
	public List<Persona> findAll() {
		return userRepository.findAll();
	}
	
}
