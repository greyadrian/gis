package es.udc.fic.tfg.gis.services;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.tfg.gis.daos.UsuarioDAO;
import es.udc.fic.tfg.gis.model.Usuario;


@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioDAO usuarioDAO;

	static Logger log = Logger.getLogger("Gis");
	
	@Transactional(value = "meuTransactionManager")
	public void RegistrarUsuario(Usuario miUsuario) {
		try{
			if(usuarioDAO.buscarPorEmail(miUsuario.getEmail())!=null){
				throw new DataIntegrityViolationException ("El correo: " + miUsuario.getEmail() +" ya existe.");
			}else{
				usuarioDAO.crear(miUsuario);
				log.info("Usuario creado");	
			}
				
		}catch (DataAccessException e){
			log.error("Fallo durante la creación del Usuario");
			throw e;
		}
		
		
	}

	public void actualizarUsuario(Usuario miUsuario) {
		try{
			usuarioDAO.modificar(miUsuario);
			log.info("Usuario actualizado");
		}catch(DataAccessException e){
			log.error("Fallo durante el actualizado del usuario");
			throw e;
		}
		
	}

	public void eliminarUsuario(Usuario miUsuario) {
		try{	
			usuarioDAO.borrar(miUsuario);
			log.info("Usuario borrado");
		}catch(DataAccessException e){
			log.error("Fallo durante el borrado del usuario");
			throw e;
		}
	}
		
	

	public Usuario autenticarUsuario(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario buscarUsuarioPorId(Long id) {
		 return	usuarioDAO.buscarPorId(id);
	
	}
	
	

}
