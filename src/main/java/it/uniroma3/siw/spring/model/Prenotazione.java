package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@NamedQuery(name = "findAllPrenotazione", query = "SELECT a FROM Prenotazione a")
public class Prenotazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=false)
	private LocalDate data;

	@Column(nullable=false)
	private int oraInizio;

	@Column(nullable=false)
	private int oraFine;
	
	@ManyToOne
	private Maestro maestro;
	
	@ManyToOne
	private User persona;
	
	@ManyToOne
	private Campo campo;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}
	public int getOraFine() {
		return oraFine;
	}
	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}
	public Maestro getMaestro() {
		return maestro;
	}
	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}
	public User getPersona() {
		return persona;
	}
	public void setPersona(User persona) {
		this.persona = persona;
	}
	public Campo getCampo() {
		return campo;
	}
	public void setCampo(Campo campo) {
		this.campo = campo;
	}
	
	@ModelAttribute("prenotazione.id")
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + oraInizio;
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
		Prenotazione other = (Prenotazione) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (oraInizio != other.oraInizio)
			return false;
		return true;
	}
	
	
	
}
