package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Circolo;

public interface CampoRepository extends CrudRepository<Campo, Long> {

	public List<Campo> findByNumeroCampo(String numeroCampo);

	public List<Campo> findByNumeroCampoAndSportAndCircolo(String numeroCampo, String sport,Circolo c);
}