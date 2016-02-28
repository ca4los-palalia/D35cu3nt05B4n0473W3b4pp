/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.CategoriaProgramaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class CategoriaProgramaDAOImpl extends HibernateDAOSuportUtil implements
CategoriaProgramaDAO {

	@Transactional
	public void save(CategoriaPrograma categoriaPrograma) {
		getHibernateTemplate().saveOrUpdate(categoriaPrograma);
	}

	@Transactional
	public void delete(CategoriaPrograma categoriaPrograma) {
		getHibernateTemplate().delete(categoriaPrograma);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CategoriaPrograma getById(Long idCategoriaPrograma) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(CategoriaPrograma.class);
		criteria.add(Restrictions.eq("idCategoriaPrograma", idCategoriaPrograma));
		List<CategoriaPrograma> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CategoriaPrograma> getByPrograma(Programas programa) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(CategoriaPrograma.class);
		criteria.add(Restrictions.eq("programa", programa));
		List<CategoriaPrograma> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CategoriaPrograma> getByCategoria(Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(CategoriaPrograma.class);
		criteria.add(Restrictions.eq("categoria", categoria));
		List<CategoriaPrograma> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CategoriaPrograma> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(CategoriaPrograma.class);
		List<CategoriaPrograma> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CategoriaPrograma getProgramaCategoria(Programas programa, Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(CategoriaPrograma.class);
		criteria.add(Restrictions.eq("programa", programa));
		criteria.add(Restrictions.eq("categoria", categoria));
		List<CategoriaPrograma> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
