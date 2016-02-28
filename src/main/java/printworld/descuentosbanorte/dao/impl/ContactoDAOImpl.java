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

import printworld.descuentosbanorte.dao.ContactoDAO;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Telefono;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ContactoDAOImpl extends HibernateDAOSuportUtil implements
		ContactoDAO {

	@Transactional
	public void save(Contacto contacto) {
		getHibernateTemplate().saveOrUpdate(contacto);
	}

	@Transactional
	public void update(Contacto contacto) {
		getHibernateTemplate().update(contacto);
	}

	@Transactional
	public void delete(Contacto contacto) {
		getHibernateTemplate().delete(contacto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getById(Long idContacto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("", idContacto));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getByTelefono(Telefono telefono) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("telefono", telefono));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getByIdEmail(Email email) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("email", email));
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Contacto> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contacto.class);
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getUltimoRegistroContacto() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Contacto.class);
		criteria.addOrder(Order.desc("idContacto"));
		criteria.setMaxResults(1);
		List<Contacto> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Contacto getContactoByEmail(Email email) {
		List<Contacto> contactos = getHibernateTemplate()
				.find("FROM Contacto as c LEFT JOIN FETCH c.email as e WHERE c.email = ?",
						email);
		return contactos.size() > 0 ? contactos.get(0) : null;
	}

}
