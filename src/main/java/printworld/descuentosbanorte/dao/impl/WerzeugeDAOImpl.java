/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.WerzeugeDAO;
import printworld.descuentosbanorte.domain.Werzeuge;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class WerzeugeDAOImpl extends HibernateDAOSuportUtil implements
WerzeugeDAO {

	@Transactional
	public void save(Werzeuge werzeuge) {
		getHibernateTemplate().saveOrUpdate(werzeuge);
	}

	@Transactional
	public void delete(Werzeuge werzeuge) {
		getHibernateTemplate().saveOrUpdate(werzeuge);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Werzeuge getById(Long idWerzeuge) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Werzeuge.class);
		criteria.add(Restrictions.eq("idWerzeuge", idWerzeuge));
		List<Werzeuge> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Werzeuge> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Werzeuge.class);
		List<Werzeuge> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
}
