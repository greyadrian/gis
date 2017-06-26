package es.udc.fic.tfg.gis.daos;

import java.util.List;

import es.udc.fic.tfg.gis.model.Organizacion;
import es.udc.fic.tfg.gis.model.Usuario;

public interface OrganizacionDAO {
	Long crear(Organizacion miOrganizacion);
	void borrar(Long idOrganizacion);
	void modificar(Organizacion miOrganizacion);
	Organizacion buscarPorId(Long Id);
	Organizacion buscarPorNif(String nif);
	List<Usuario> buscarUsuariosOrganizacion(Long  idOrganizacion); 
}
