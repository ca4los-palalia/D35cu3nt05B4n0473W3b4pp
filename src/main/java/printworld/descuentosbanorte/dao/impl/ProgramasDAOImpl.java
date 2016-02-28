/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.ProgramasDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.utils.BuildingTables;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class ProgramasDAOImpl extends HibernateDAOSuportUtil implements ProgramasDAO {

	@Transactional
	public void save(Programas programas) {
		getHibernateTemplate().saveOrUpdate(programas);
	}

	@Transactional
	public void delete(Programas programas) {
		getHibernateTemplate().delete(programas);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Programas getById(Long idPrograma) {
		List<Programas> lista = null;

		try {
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
					.createCriteria(Programas.class);
			criteria.add(Restrictions.eq("idPrograma", idPrograma));
			lista = criteria.list();
		} finally {
			
		}

		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Programas> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Programas.class);
		List<Programas> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Programas getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Programas.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<Programas> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Programas> getPrivados(boolean privado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Programas.class);
		criteria.add(Restrictions.eq("privado", privado));
		List<Programas> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Programas getByIdJSon(Integer id) {
		List<Programas> lista = null;

		try {
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
					.createCriteria(Programas.class);
			criteria.add(Restrictions.eq("id", id));
			lista = criteria.list();
		} finally {
			
		}
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Programas getByIdSqlNative(Long idPrograma) {
		List<Programas> lista = null;
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession(); 
		Query query = session.createSQLQuery("SELECT * FROM Programas "
				+ "WHERE idPrograma = " + idPrograma);
		List<Object[]> listDatos = query.list();
		if(listDatos != null){
			BuildingTables building = new BuildingTables();
			lista = building.construirPrograma(listDatos);
		}
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
