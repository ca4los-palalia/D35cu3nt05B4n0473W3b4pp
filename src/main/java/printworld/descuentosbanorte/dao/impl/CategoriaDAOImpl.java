/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.CategoriaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;
import printworld.descuentosbanorte.utils.BuildingTables;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class CategoriaDAOImpl extends HibernateDAOSuportUtil implements
		CategoriaDAO {

	@Transactional
	public void save(Categoria categoria) {
		getHibernateTemplate().saveOrUpdate(categoria);
	}

	@Transactional
	public void delete(Categoria categoria) {
		getHibernateTemplate().delete(categoria);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Categoria getById(Long idCategoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("idCategoria", idCategoria));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getByPrograma(Programas programa) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("programa", programa));
		criteria.addOrder(Order.asc("orden"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.addOrder(Order.asc("nombreCategoria"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getPrivados(Boolean privado) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("privado", privado));
		criteria.addOrder(Order.asc("nombreCategoria"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getCategoriasNoPertenecenAPrograma(List<CategoriaPrograma> idsCategrogias) {
		List<Categoria> lista = null;
		List<Long> idsCategoriasPrograma = new ArrayList<Long>();
		if(idsCategrogias != null){
			for (CategoriaPrograma item : idsCategrogias) {
				idsCategoriasPrograma.add(item.getCategoria().getIdCategoria());
			}
			Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
					createCriteria(Categoria.class);
			criteria.add(Restrictions.not(Restrictions.in("idCategoria", idsCategoriasPrograma)));
			criteria.add(Restrictions.eq("privado", false));
			criteria.addOrder(Order.asc("nombreCategoria"));
			lista = criteria.list();
		}
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getByProgramaLikeNombreCategoria(Programas programa, String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		
		criteria.add(Restrictions.eq("programa", programa));
		criteria.add(Restrictions.sqlRestriction(" nombre LIKE '" + nombre
				+ "%'"));
		criteria.addOrder(Order.asc("nombreCategoria"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getFiltering(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		
		criteria.add(Restrictions.sqlRestriction(" nombreCategoria LIKE '" + nombre
				+ "%'"));
		criteria.addOrder(Order.asc("nombreCategoria"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Categoria getByIdJSon(Integer id) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("id", id));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Categoria> getByProgramaVisibles(Programas programa){
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("visible", true));
		criteria.add(Restrictions.eq("programa", programa));
		criteria.addOrder(Order.asc("orden"));
		List<Categoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Categoria getByIdSqlNative(Long idCategoria) {
		List<Categoria> lista = null;
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession(); 
		Query query = session.createSQLQuery("SELECT * FROM Categoria "
				+ "WHERE idCategoria = " + idCategoria);
		List<Object[]> listDatos = query.list();
		if(listDatos != null){
			BuildingTables building = new BuildingTables();
			lista = building.construirCategorias(listDatos);
		}
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
