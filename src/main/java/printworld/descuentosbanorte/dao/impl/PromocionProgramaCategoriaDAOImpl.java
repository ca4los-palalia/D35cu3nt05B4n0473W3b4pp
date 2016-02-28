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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.PromocionProgramaCategoriaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.BuildingTables;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class PromocionProgramaCategoriaDAOImpl extends HibernateDAOSuportUtil implements PromocionProgramaCategoriaDAO {

	@Transactional
	public void save(PromocionProgramaCategoria promocion) {
		getHibernateTemplate().saveOrUpdate(promocion);
	}

	@Transactional
	public void delete(PromocionProgramaCategoria promocion) {
		getHibernateTemplate().delete(promocion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PromocionProgramaCategoria getById(Long idPromocionProgramaCategoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("idPromocionProgramaCategoria", idPromocionProgramaCategoria));
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PromocionProgramaCategoria> getByCategoria(Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("categoria", categoria));
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PromocionProgramaCategoria> getByPrograma(Programas programa) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("programas", programa));
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PromocionProgramaCategoria> getByCategoriaPrograma(Programas programa, Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("programas", programa));
		criteria.add(Restrictions.eq("categoria", categoria));
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PromocionProgramaCategoria> getByEstado(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PromocionProgramaCategoria> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PromocionProgramaCategoria getByPromocion(Promocion promocion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("promocion", promocion));
		List<PromocionProgramaCategoria> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PromocionProgramaCategoria getByPromocionSqlNative(long idPromocion) {
		
		List<PromocionProgramaCategoria> lista = null;
		/*SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession(); 
		Query query = session.createSQLQuery("SELECT * FROM PromocionProgramaCategoria "
				+ "WHERE promocion = " + idPromocion);
		List<Object[]> listDatos = query.list();
		if(listDatos != null){
			lista = new ArrayList<PromocionProgramaCategoria>();
			for (Object[] datos : listDatos) {
				PromocionProgramaCategoria item = new PromocionProgramaCategoria();
				item.setIdPromocionProgramaCategoria(Long.parseLong(String.valueOf(datos[0])));
				
				query = session.createSQLQuery("SELECT * FROM Categoria "
						+ "WHERE idCategoria = " + Long.parseLong(String.valueOf(datos[1])));
				List<Object[]> listDatos2 = query.list();
				if(listDatos2 != null){
					BuildingTables building = new BuildingTables();
					List<Categoria> lista2 = building.construirCategorias(listDatos2);
					item.setCategoria(lista2.get(0));
				}
				
				
				query = session.createSQLQuery("SELECT * FROM Programas "
						+ "WHERE idPrograma = " + Long.parseLong(String.valueOf(datos[2])));
				List<Object[]> listDatos3 = query.list();
				if(listDatos3 != null){
					BuildingTables building = new BuildingTables();
					List<Programas> lista3 = building.construirPrograma(listDatos3);
					item.setProgramas(lista3.get(0));
				}
				
				
				query = session.createSQLQuery("SELECT * FROM Promocion "
						+ "WHERE idPromocion = " + Long.parseLong(String.valueOf(datos[3])));
				List<Object[]> listDatos4 = query.list();
				
				if(listDatos4 != null){
					BuildingTables building = new BuildingTables();
					List<Promocion> lista4 = building.construirPromocion(listDatos4);
					item.setPromocion(lista4.get(0));
				}
				
				lista.add(item);
			}
		}
		
		*/
		
		
		/*Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(PromocionProgramaCategoria.class);
		criteria.add(Restrictions.eq("promocion", promocion));
		List<PromocionProgramaCategoria> lista = criteria.list();*/
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}
}
