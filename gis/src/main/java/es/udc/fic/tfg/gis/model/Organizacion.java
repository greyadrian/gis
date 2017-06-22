package es.udc.fic.tfg.gis.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="organizacion")
public class Organizacion {
	
	private Long idOrganizacion;
	private String nombre;
	private String nif;
	private String descripcion;
	private Direccion direccion;
	
	
	//Set -sin elementos repetidos ni ordenestablecido
		private Set<String> telefonos = new HashSet<String>();
		private String email;
		
		private Set<Usuario> usuarios=new HashSet<Usuario>();

	public Organizacion(){
	}
	
	public Organizacion(String nombre, String nif, String descripcion, Direccion direccion, Set<String> telefonos,
			String email) {
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.telefonos = telefonos;
		this.email = email;
		
	}
		
	@Id
	@SequenceGenerator(name = "idOrganizacion", sequenceName = "id_organizacion_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idOrganizacion")
	@Column(name = "idOrganizacion")
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}
	
	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}
	
	@Column(nullable=false)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(nullable=false)
	public String getNif() {
		return nif;
	}
	
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Embedded
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="TelefonosOrganizacion", joinColumns=@JoinColumn(name="idOrganizacion"))
	@Column(name="Telefono", nullable=false)
	public Set<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(Set<String> telefonos) {
		this.telefonos = telefonos;
	}
	
	
	@Column(nullable=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ORGANIZACION")
	//@Column(insertable=false, updatable=false)
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Organizacion [idOrganizacion=" + idOrganizacion + ", nombre=" + nombre + ", nif=" + nif
				+ ", descripcion=" + descripcion + ", direccion=" + direccion + ", telefonos=" + telefonos + ", email="
				+ email + ", usuarios=" + usuarios + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idOrganizacion == null) ? 0 : idOrganizacion.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Organizacion other = (Organizacion) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idOrganizacion == null) {
			if (other.idOrganizacion != null)
				return false;
		} else if (!idOrganizacion.equals(other.idOrganizacion))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	

	
	
	
	

}
