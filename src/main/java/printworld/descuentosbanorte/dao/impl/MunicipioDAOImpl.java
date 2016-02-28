/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.MunicipioDAO;
import printworld.descuentosbanorte.domain.Municipio;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */

@Repository
public class MunicipioDAOImpl extends HibernateDAOSuportUtil implements MunicipioDAO{

	@Transactional
	public void save(Municipio municipio) {
		getHibernateTemplate().save(municipio);
	}

	@Transactional
	public void update(Municipio municipio) {
		getHibernateTemplate().update(municipio);
	}

	@Transactional
	public void delete(Municipio municipio) {
		getHibernateTemplate().delete(municipio);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Municipio getById(Long idMunicipio) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Municipio.class);
		criteria.add(Restrictions.eq("idMunicipio", idMunicipio));
		List<Municipio> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Municipio> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Municipio.class);
		List<Municipio> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
   
}
