/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.EstadoDAO;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class EstadoDAOImpl extends HibernateDAOSuportUtil implements EstadoDAO{
	
	@Transactional
	public void save(Estado estado) {
		getHibernateTemplate().save(estado);
	}

	@Transactional
	public void update(Estado estado) {
		getHibernateTemplate().update(estado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getById(Long estado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		criteria.add(Restrictions.eq("idEstado", estado));
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
		
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Estado> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	public void delete(Estado estado) {
		getHibernateTemplate().delete(estado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getByClave(String clave) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		criteria.add(Restrictions.eq("clave", clave));
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getByAbreviatura(String abreviatura) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Estado.class);
		criteria.add(Restrictions.eq("abreviatura", abreviatura));
		List<Estado> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

   
}
