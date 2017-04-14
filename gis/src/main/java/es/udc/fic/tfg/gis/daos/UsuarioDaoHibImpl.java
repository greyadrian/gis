package es.udc.fic.tfg.gis.daos;

import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import es.udc.fic.tfg.gis.model.Usuario;

@Repository
@EnableTransactionManagement
public class UsuarioDaoHibImpl implements UsuarioDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	public Long crear(Usuario miUsuario) {
		if (miUsuario.getIdUsuario()!=null){
			throw new RuntimeException("Intento de crear un usuario ya persistente");
		}
		Long idUsuario = (Long) sessionfactory.getCurrentSession().save(miUsuario);
		System.out.println(idUsuario.longValue());
		return idUsuario;
	}

	public void borrar(Usuario miUsuario) {
		sessionfactory.getCurrentSession().delete(miUsuario);
		
	}

	public void modificar(Usuario miUsuario) {
		sessionfactory.getCurrentSession().update(miUsuario);
	}

	public Usuario buscarPorId(Long id) {
		Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("idUsuario", id));
		if (criteria.list().isEmpty()) return null;
		Usuario  miUsuario = (Usuario) criteria.list().get(0);
		return miUsuario;
	
	}

	//Esta mal
//	public List<Usuario> buscarporOrganizacion(Long id) {
//		Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Usuario.class);
//		criteria.add(Restrictions.eq("idOrganizacion", id));
//		if(criteria.list().isEmpty()){
//			return null;
//		}
//	
//		@SuppressWarnings("unchecked")
//		List<Usuario> listaUsuarios = (List<Usuario>) criteria.list();
//		return listaUsuarios;
//	}

	public Usuario buscarPorEmail(String email) {
		Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		if (criteria.list().isEmpty()) return null;
		Usuario  miUsuario = (Usuario) criteria.list().get(0);
		return miUsuario;
	}

}
