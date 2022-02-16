package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Circolo;

public interface CircoloRepository extends CrudRepository<Circolo, Long> {

	public List<Circolo> findByNomeCircolo(String nomeCircolo);

	public List<Circolo> findByNomeCircoloAndIndirizzo(String nomeCircolo, String indirizzo);

}