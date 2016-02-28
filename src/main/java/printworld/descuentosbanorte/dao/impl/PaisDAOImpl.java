/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.PaisDAO;
import printworld.descuentosbanorte.domain.Pais;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class PaisDAOImpl extends HibernateDAOSuportUtil implements PaisDAO{

	@Transactional
	public void save(Pais pais) {
		getHibernateTemplate().save(pais);
	}

	@Transactional
	public void delete(Pais pais) {
		getHibernateTemplate().delete(pais);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Pais> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Pais.class);
		List<Pais> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Pais findById(Long idPais) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Pais.class);
		criteria.add(Restrictions.eq("idPais", idPais));
		List<Pais> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

}
