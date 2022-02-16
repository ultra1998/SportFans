package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllMaestro", query = "SELECT a FROM Maestro a")
public class Maestro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	private String recapito;
	
	@Column(nullable=false)
	private String sportInsegnato;
	
	@Column(nullable=false)
	private int prezzoOraLezione;
	
	@ManyToOne
	private Circolo circolo;
	
	@OneToMany(mappedBy="maestro")
	private List<Prenotazione> prenotazioni;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public int getPrezzoOraLezione() {
		return prezzoOraLezione;
	}

	public void setPrezzoOraLezione(int prezzoOraLezione) {
		this.prezzoOraLezione = prezzoOraLezione;
	}

	public Circolo getCircolo() {
		return circolo;
	}

	public void setCircolo(Circolo circolo) {
		this.circolo = circolo;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public long getId() {
		return id;
	}
	
	

	public String getSportInsegnato() {
		return sportInsegnato;
	}

	public void setSportInsegnato(String sportInsegnato) {
		this.sportInsegnato = sportInsegnato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Maestro other = (Maestro) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
