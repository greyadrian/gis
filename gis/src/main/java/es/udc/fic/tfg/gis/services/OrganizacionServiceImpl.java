package es.udc.fic.tfg.gis.services;

import es.udc.fic.tfg.gis.model.Organizacion;
import es.udc.fic.tfg.gis.model.Usuario;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.tfg.gis.daos.OrganizacionDAO;



@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
public class OrganizacionServiceImpl implements OrganizacionService {

	@Autowired
	OrganizacionDAO organizacionDAO;
	
	static Logger log = Logger.getLogger("Gis");
	
	@Transactional(value = "meuTransactionManager")
	public void registrarOrganizacion(Organizacion miOrganizacion) {
		try{System.out.println("2");
			if(organizacionDAO.buscarPorNif(miOrganizacion.getNif())!=null){
				throw new DataIntegrityViolationException ("Ya existe una empresa con el NIF: " + miOrganizacion.getNif());
			}else{
				organizacionDAO.crear(miOrganizacion);
				log.info("Organizacion creada: " + miOrganizacion);	
			}
		}catch (DataAccessException e){
			log.error("Fallo durante la creación de la Organizacion: " + miOrganizacion);
			throw e;
		}
	}
	
	@Transactional(value = "meuTransactionManager")
	public void actualizarOrganizacion(Organizacion miOrganizacion) {
		try{
			organizacionDAO.modificar(miOrganizacion);
			log.info("Organizacion actualizada");
		}catch(DataAccessException e){
			log.error("Fallo durante la actualizacion de la organizacion");
			throw e;
		}
		
		
	}

	@Transactional(value = "meuTransactionManager")
	public void borrarOrganizacion(Organizacion miOrganizacion) {
		try{
			List<Usuario> usuarios = (List<Usuario>) organizacionDAO.buscarUsuariosOrganizacion(miOrganizacion);
			if(usuarios.isEmpty()){
				organizacionDAO.borrar(miOrganizacion);
				log.info("Curso " + miOrganizacion.getNombre() + "ha sido borrada");
			}else{
				throw new DataIntegrityViolationException ("No se puede eliminar una organizacion con usuarios");
			}
		}catch(DataAccessException e){
			log.error("Error al borrar la organizacion: "+ miOrganizacion.getNombre());
			throw e;
		}
		
	}


	public Organizacion buscarOrganizacionPorId(Long idOrganizacion) {
		 return	organizacionDAO.buscarPorId(idOrganizacion);
		
	}

}
