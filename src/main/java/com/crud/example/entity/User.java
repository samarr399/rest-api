package com.crud.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Size(min = 2)
	private String name;
	@Size(max = 5)
	private String school;

	@Past
	private Date dob;

	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(@Size(min = 2) String name, @Size(max = 5) String school, @Past Date dob, String password) {
		super();
		this.name = name;
		this.school = school;
		this.dob = dob;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ",\n name=" + name + ",\n school=" + school + ",\n dob=" + dob + ",\n password=" + password
				+ "]";
	}

}
