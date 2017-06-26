package es.udc.fic.tfg.gis.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	

	private Long idUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String password;
	private Set<String> telefonos = new HashSet<String>();
	private boolean esAdministrador;
	private Timestamp fechaAlta;
	
	private Organizacion organizacion;
	
	
	public Usuario(){};
	
	public Usuario(String nombre, String apellido1, String apellido2, String email, String password,
			Set<String> telefonos, boolean esAdministrador, Timestamp fechaAlta, Organizacion organizacion) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.password = password;
		this.telefonos = telefonos;
		this.esAdministrador = esAdministrador;
		this.fechaAlta = fechaAlta;
		this.organizacion = organizacion;
	}

	@Id
	@SequenceGenerator(name = "idUsuario", sequenceName = "id_usuario_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUsuario")
	@Column(name = "idUsuario")
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(nullable=false)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(nullable=false)
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	@Column(nullable=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="TelefonosUsuario", joinColumns=@JoinColumn(name="idUsuario"))
	@Column(name = "Telefono")
	public Set<String> getTelefonos() {
		return telefonos;
	}
	
	public void setTelefonos(Set<String> telefonos) {
		this.telefonos = telefonos;
	}
	
	@Column(nullable=false)
	public boolean isEsAdministrador() {
		return esAdministrador;
	}
	
	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
	}
	
	@Column(nullable=false)
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ORGANIZACION")
	//@Column(insertable=false, updatable=false)
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result + ((apellido2 == null) ? 0 : apellido2.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (esAdministrador ? 1231 : 1237);
		result = prime * result + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Usuario other = (Usuario) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (esAdministrador != other.esAdministrador)
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
