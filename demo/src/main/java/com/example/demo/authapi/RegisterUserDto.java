package com.example.demo.authapi;

import java.util.List;

import com.example.demo.model.Phones;

public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;
    private String token;
    
   
 

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
    
}
