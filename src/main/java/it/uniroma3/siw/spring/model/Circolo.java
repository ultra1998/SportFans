package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@NamedQuery(name = "findAllCircoli", query = "SELECT a FROM Circolo a")
public class Circolo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String nomeCircolo;
	
	@Column(nullable=false)
	private String indirizzo;
	
	@Column
	private String recapito;
	
	@Column(nullable=false)
	private String email;
	
	@OneToMany(mappedBy="circolo")
	private List<User> persone;
	
	@OneToMany(mappedBy="circolo")
	private List<Maestro> maestri;
	
	@OneToMany(mappedBy="circolo")
	private List<Campo> campi;

	public String getNomeCircolo() {
		return nomeCircolo;
	}

	public void setNomeCircolo(String nomeCircolo) {
		this.nomeCircolo = nomeCircolo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<User> getPersone() {
		return persone;
	}

	public void setPersone(List<User> persone) {
		this.persone = persone;
	}

	public List<Maestro> getMaestri() {
		return maestri;
	}

	public void setMaestri(List<Maestro> maestri) {
		this.maestri = maestri;
	}

	public List<Campo> getCampi() {
		return campi;
	}

	public void setCampi(List<Campo> campi) {
		this.campi = campi;
	}

	public long getId() {
		return id;
	}
	
	@ModelAttribute(value = "circolo")
	public Circolo getCircolo() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCircolo == null) ? 0 : nomeCircolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circolo other = (Circolo) obj;
		if (nomeCircolo == null) {
			if (other.nomeCircolo != null)
				return false;
		} else if (!nomeCircolo.equals(other.nomeCircolo))
			return false;
		return true;
	}
	
	
	

}
