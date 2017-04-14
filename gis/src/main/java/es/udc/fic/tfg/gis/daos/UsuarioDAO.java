package es.udc.fic.tfg.gis.daos;



import es.udc.fic.tfg.gis.model.Usuario;

public interface UsuarioDAO {
	
	Long crear(Usuario miUsuario);
	void borrar (Usuario miUsuario);
	void modificar(Usuario miUsuario);
	Usuario buscarPorId (Long id);
	Usuario buscarPorEmail (String email);
//	List<Usuario> buscarporOrganizacion(Long id);
	

}
