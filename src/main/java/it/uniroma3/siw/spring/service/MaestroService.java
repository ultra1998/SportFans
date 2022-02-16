package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.model.Maestro;
import it.uniroma3.siw.spring.repository.MaestroRepository;

@Service
public class MaestroService {
	
	@Autowired
	private MaestroRepository maestroRepository; 
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Maestro inserisci(Maestro maestro) {
		return maestroRepository.save(maestro);
	}
	
	@Transactional
	public List<Maestro> maestriPerNomeAndCognome(String nome, String cognome) {
		return maestroRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public List<Maestro> tutti() {
		return (List<Maestro>) maestroRepository.findAll();
	}

	@Transactional
	public Maestro maestroPerId(Long id) {
		Optional<Maestro> optional = maestroRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Maestro maestro) {
		List<Maestro> studenti = this.maestroRepository.findByNomeAndCognome(maestro.getNome(), maestro.getCognome());
		if (studenti.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public List<Maestro> filtraPerCircolo(Circolo circolo){
		List<Maestro> maestri = new ArrayList<Maestro>();
		for(Maestro m: this.tutti()) {
			if(m.getCircolo().equals(circolo)) {
				maestri.add(m);
			}
		}
		return maestri;
	} 

	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}
	
	
}
