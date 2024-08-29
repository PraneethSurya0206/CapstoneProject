package com.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "learners")
public class Learner {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(nullable = false)
    private int learner_id;

    @Column(name = "first_name" , nullable = false)
    @NotEmpty(message ="first_name  required")
    private String first_name;

    @Column(name = "last_name" , nullable = false)
    @NotEmpty(message ="last_name  required")
    private String last_name;

    @Column(name = "birth_date")
    @NotNull(message = "Birthdate required")
    @Past
    private LocalDate birth_date;

    @Column(name = "gender" , length = 5)
    @NotEmpty(message="gender required")
    private String gender;

  
    @Column(name = "email", unique = true)
	@NotEmpty(message ="Email is required")
    private String email;
    
    @Column(name = "password")
    @NotEmpty(message = "password required")
    private String password;
    
	// One-to-Many relationship with Enrollment
	/*@OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Enrollment> enrollments;*/


	public int getLearner_id() {
		return learner_id;
	}

	public void setLearner_id(int learner_id) {
		this.learner_id = learner_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Learner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Learner(String first_name, String last_name, LocalDate birth_date, String gender, String email,
			String password) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.gender = gender;
		this.email = email;
		this.password = password;
	}

	public Learner(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Learner toEnity() {
		// TODO Auto-generated method stub
		return null;
	}

		
	
	
    
	
}
