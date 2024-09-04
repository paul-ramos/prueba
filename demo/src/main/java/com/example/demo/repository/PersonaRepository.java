package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Persona;
import java.util.Optional;
public interface PersonaRepository extends JpaRepository<Persona,Long> {
	 Optional<Persona> findByEmail(String email);
}
