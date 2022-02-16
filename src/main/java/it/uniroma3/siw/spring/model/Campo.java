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
@NamedQuery(name = "findAllCampo", query = "SELECT a FROM Campo a")
public class Campo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String numeroCampo;
	
	@Column
	private String terreno;
	
	@Column(nullable=false)
	private String sport;
	
	@Column(nullable=false)
	private String prezzoOrario;
	
	@ManyToOne
	private Circolo circolo;
	
	@OneToMany(mappedBy="campo")
	private List<Prenotazione> prenotazioni;

	public String getNumeroCampo() {
		return numeroCampo;
	}

	public void setNumeroCampo(String numeroCampo) {
		this.numeroCampo = numeroCampo;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(String prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((circolo == null) ? 0 : circolo.hashCode());
		result = prime * result + ((numeroCampo == null) ? 0 : numeroCampo.hashCode());
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
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
		Campo other = (Campo) obj;
		if (circolo == null) {
			if (other.circolo != null)
				return false;
		} else if (!circolo.equals(other.circolo))
			return false;
		if (numeroCampo == null) {
			if (other.numeroCampo != null)
				return false;
		} else if (!numeroCampo.equals(other.numeroCampo))
			return false;
		if (sport == null) {
			if (other.sport != null)
				return false;
		} else if (!sport.equals(other.sport))
			return false;
		return true;
	}
	
	
	
}
