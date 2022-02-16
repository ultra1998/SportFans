package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Maestro;

public interface MaestroRepository extends CrudRepository<Maestro, Long> {

	public List<Maestro> findByNome(String nome);

	public List<Maestro> findByNomeAndCognome(String nome, String cognome);

	public List<Maestro> findByNomeOrCognome(String nome, String cognome);
}