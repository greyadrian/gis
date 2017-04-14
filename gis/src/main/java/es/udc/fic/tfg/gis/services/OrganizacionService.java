package es.udc.fic.tfg.gis.services;
import es.udc.fic.tfg.gis.model.*;

public interface OrganizacionService {
	
	void registrarOrganizacion (Organizacion miOrganizacion);
	void actualizarOrganizacion (Organizacion miOrganizacion);
	void borrarOrganizacion (Organizacion miOrganizacion);
	Organizacion buscarOrganizacionPorId (Long idOrganiazcion);



}
