package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.repository.CircoloRepository;

@Service
public class CircoloService {
	
	@Autowired
	private CircoloRepository circoloRepository; 
	
	@Autowired
	private CredentialsService credentialsService; 
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public Circolo inserisci(Circolo circolo) {
		return circoloRepository.save(circolo);
	}
	
	@Transactional
	public List<Circolo> circoloPerNome(String nome) {
		return circoloRepository.findByNomeCircolo(nome);
	}

	@Transactional
	public List<Circolo> tutti() {
		return (List<Circolo>) circoloRepository.findAll();
	}

	@Transactional
	public Circolo circoloPerId(Long id) {
		Optional<Circolo> optional = circoloRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Circolo circolo) {
		List<Circolo> circoli = this.circoloRepository.findByNomeCircoloAndIndirizzo(circolo.getNomeCircolo(), circolo.getIndirizzo());
		if (circoli.size() > 0)
			return true;
		else 
			return false;
	}

	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
}
