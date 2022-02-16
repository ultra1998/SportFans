package it.uniroma3.siw.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.repository.CampoRepository;

@Service
public class CampoService {
	
	@Autowired
	private CampoRepository campoRepository; 
	
	@Autowired
	private CredentialsService credentialsService; 
	
	@Transactional
	public Campo inserisci(Campo campo) {
		return campoRepository.save(campo);
	}
	
	@Transactional
	public List<Campo> campoPerNomeAndNumero(String nome, String numeroCampo,Circolo c) {
		return campoRepository.findByNumeroCampoAndSportAndCircolo(nome, numeroCampo,c);
	}

	@Transactional
	public List<Campo> tutti() {
		return (List<Campo>) campoRepository.findAll();
	}

	@Transactional
	public Campo campoPerId(Long id) {
		Optional<Campo> optional = campoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Campo campo) {
		List<Campo> campi = this.campoRepository.findByNumeroCampoAndSportAndCircolo(campo.getNumeroCampo(), campo.getSport(),campo.getCircolo());
		if (campi.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public List<Campo> filtraPerCircolo(Circolo c) {
		List<Campo> campi = new ArrayList<>();
		for(Campo campo: this.tutti()) {
			if(campo.getCircolo().equals(c)) {
				campi.add(campo);
			}
		}
		return campi;
	}
	
	@Transactional
	public boolean IsDisponibile(Campo c, LocalDate data, int oraInizio, int oraFine) {
		for(Prenotazione p: c.getPrenotazioni()) {
			if(p.getCampo()==c && p.getData()==data && p.getOraInizio()==oraInizio && p.getOraFine()==oraFine) {
				return false;
			}
		}
		return true;
	}
		
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}
	
	
}
