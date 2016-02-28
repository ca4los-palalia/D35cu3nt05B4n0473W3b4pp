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

import printworld.descuentosbanorte.dao.TelefonoDAO;
import printworld.descuentosbanorte.domain.Telefono;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class TelefonoDAOImpl extends HibernateDAOSuportUtil implements TelefonoDAO{

	@Transactional
	public void save(Telefono telefono) {
		getHibernateTemplate().saveOrUpdate(telefono);
	}

	@Transactional
	public void update(Telefono telefono) {
		getHibernateTemplate().update(telefono);
	}

	@Transactional
	public void delete(Telefono telefono) {
		getHibernateTemplate().delete(telefono);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Telefono getById(Long idTelefono) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Telefono.class);
		criteria.add(Restrictions.eq("idTelefono", idTelefono));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Telefono> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Telefono.class);
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Telefono getUltimoregistroEmail() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Telefono.class);
		criteria.addOrder(Order.desc("idTelefono"));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}


   
}
