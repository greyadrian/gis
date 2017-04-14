package es.udc.fic.tfg.gis.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import es.udc.fic.tfg.gis.model.Organizacion;
import es.udc.fic.tfg.gis.model.Usuario;

@Repository
@EnableTransactionManagement
public class OrganizacionDAOHibImpl implements OrganizacionDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	public Long crear(Organizacion miOrganizacion) {
		if (miOrganizacion.getIdOrganizacion()!=null){
			throw new RuntimeException("Intento de crear una organizacion ya persistente");
		}
		Long idOrganizacion= (Long) sessionfactory.getCurrentSession().save(miOrganizacion);
		return idOrganizacion;
	}

	public void borrar(Organizacion miOrganizacion) {
		sessionfactory.getCurrentSession().delete(miOrganizacion);
		
	}

	public void modificar(Organizacion miOrganizacion) {
		sessionfactory.getCurrentSession().update(miOrganizacion);
	}

	public Organizacion buscarPorId(Long id) {
		Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("idOrganizacion", id));
		if (criteria.list().isEmpty()) return null;
		Organizacion  miOrganizacion = (Organizacion) criteria.list().get(0);
		return miOrganizacion;
	}
	
	public Organizacion buscarPorNif(String nif) {
		Criteria criteria = sessionfactory.getCurrentSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("nif", nif));
		if (criteria.list().isEmpty()) return null;
		Organizacion  miOrganizacion = (Organizacion) criteria.list().get(0);
		return miOrganizacion;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosOrganizacion(Organizacion miOrganizacion) {
		Query q = sessionfactory.getCurrentSession().createQuery("from Usuario where idOrganizacion = :idOrganizacion");
		  q.setParameter("idOrganizacion",miOrganizacion.getIdOrganizacion());
		  
		  return (List<Usuario>) q.list();

	}

}
