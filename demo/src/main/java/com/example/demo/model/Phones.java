package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table
public class Phones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String number;
	private long citycode;
	private long countrycode;
	@ManyToOne
	private Persona persona;	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getCitycode() {
		return citycode;
	}
	public void setCitycode(long citycode) {
		this.citycode = citycode;
	}
	public long getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(long countrycode) {
		this.countrycode = countrycode;
	}
 

}
