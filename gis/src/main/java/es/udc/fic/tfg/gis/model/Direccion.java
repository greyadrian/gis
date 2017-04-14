package es.udc.fic.tfg.gis.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	
	

	private String pais;
	private String ciudad;
	private String provincia;
	private String calle;
	private int numero;
	private int codigoPostal;
	
	public Direccion(){};
	
	public Direccion(String pais, String provincia, String ciudad, String calle, int numero, int codigoPostal) {
		super();
		this.pais = pais;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
	}
	
	@Column(nullable=false)
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Column(nullable=false)
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@Column(nullable=false)
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getCodigoPostal() {
		return codigoPostal;
	}
	
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
