package es.udc.fic.tfg.gis.services;

import es.udc.fic.tfg.gis.model.Usuario;

public interface UsuarioService {
	
		void registrarUsuario (Usuario miUsuario);
		void actualizarUsuario (Usuario miUsuario);
		void eliminarUsuario (Long idUsuario);
		Usuario autenticarUsuario (String login, String password);
		Usuario buscarUsuarioPorId(Long id);
}
