package es.udc.fic.tfg.gis.services;
import es.udc.fic.tfg.gis.model.*;

public interface OrganizacionService {
	
	Long registrarOrganizacion (Organizacion miOrganizacion);
	void actualizarOrganizacion (Organizacion miOrganizacion);
	void borrarOrganizacion (Long idOrganizacion);
	Organizacion buscarOrganizacionPorId (Long idOrganizacion);



}
