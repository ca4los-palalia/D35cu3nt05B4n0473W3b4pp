/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.DireccionDAO;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Municipio;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class DireccionDAOImpl extends HibernateDAOSuportUtil implements DireccionDAO{

	@Transactional
	public void save(Direccion direccion) {
		getHibernateTemplate().save(direccion);
		
	}
	@Transactional
	public void update(Direccion direccion) {
		getHibernateTemplate().update(direccion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Direccion getById(Long direccion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		criteria.add(Restrictions.eq("idDireccion", direccion));
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Direccion> getByCodigoPostalId(String codigoPostal) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		criteria.add(Restrictions.eq("cp", codigoPostal));
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Direccion> getByEstado(Estado estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		criteria.add(Restrictions.eq("estado", estado));
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Direccion> getByMunicipio(Municipio municipio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		criteria.add(Restrictions.eq("municipio", municipio));
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Direccion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Direccion getUltimoRegistroDireccion() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Direccion.class);
		criteria.addOrder(Order.desc("idDireccion"));
		criteria.setMaxResults(1);
		List<Direccion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

   
}
