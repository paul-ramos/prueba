package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authapi.ErroresDTO;
import com.example.demo.authapi.LoginUserDto;
import com.example.demo.authapi.RegisterUserDto;
import com.example.demo.authapi.responses.LoginResponse;
import com.example.demo.authapi.services.AuthenticationService;
import com.example.demo.model.Persona;
import com.example.demo.model.Phones;
import com.example.demo.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
/*
    @PostMapping("/signup")
    public ResponseEntity<Persona> register(@RequestBody RegisterUserDto registerUserDto) {
    	System.out.println("/signup" );
        Persona registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
*/
    @RequestMapping(value = "/crear", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crear( 
    		                   @Valid	@RequestBody RegisterUserDto registerUserDto,
                               BindingResult result){
              
              System.out.println("Crear");
    	      Persona registeredUser = null;
    	   //   LoginUserDto loginUserDto = new LoginUserDto();
    	  //    loginUserDto.setEmail(registerUserDto.getEmail());
    	 //     Persona authenticatedUser = authenticationService.authenticate(loginUserDto);
    	 //     String jwtToken = jwtService.generateToken(authenticatedUser);
    	  //    registerUserDto.setToken(jwtToken);
    	      try 
    	      
    	      {
    	    	  
    	    	  registeredUser = authenticationService.signup(registerUserDto);
    	      }
    	      catch (Exception e)
    	      {
    	    	 String tipoError = e.getLocalizedMessage();
    	    	 
    	    	 if (tipoError != null && tipoError.toLowerCase().indexOf("could not execute statement")>=0)
    	    	 {
    	    		 return  ResponseEntity.ok(new ErroresDTO("El Correo ya esta registrado"));
    	    	 }
    	    	 else
    	    	 {
    	    		 
    	    		 return  ResponseEntity.ok(new ErroresDTO(e.getLocalizedMessage()));
    	    	 }
    	    	 
    	    	 
    	      }
    	      System.out.println("Ok");
              return  ResponseEntity.ok(registeredUser);
    }   
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	Persona authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
    
	@GetMapping("/todos")
	public List<Persona> todos() {
		return authenticationService.findAll();

	}   
	@PostMapping("/test-request")
	 public ResponseEntity<String> testPostRequest() {
	    return ResponseEntity.ok("POST request successful");
	}	
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}	
}