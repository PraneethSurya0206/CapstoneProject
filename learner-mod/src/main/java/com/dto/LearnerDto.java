package com.dto;

import com.model.Learner;

import jakarta.validation.constraints.NotEmpty;

public class LearnerDto {
	
	@NotEmpty(message = "Required Email")
	private String email;
	
	@NotEmpty(message ="Required Password")
	private String password;

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

	public LearnerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LearnerDto(@NotEmpty(message = "Required Email") String email,
			@NotEmpty(message = "Required Password") String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LearnerDto [email=" + email + ", password=" + password + "]";
	}
	

	public  Learner toEnity() {
		return  new Learner(email, password);
		
	}

	
}


