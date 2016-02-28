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

import printworld.descuentosbanorte.dao.UbicacionDAO;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.ReporteTemplate;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.BuildingTables;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class UbicacionDAOImpl extends HibernateDAOSuportUtil implements UbicacionDAO {

	@Transactional
	public void save(Ubicacion ubicacion) {
		getHibernateTemplate().saveOrUpdate(ubicacion);
	}

	@Transactional
	public void delete(Ubicacion ubicacion) {
		getHibernateTemplate().delete(ubicacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Ubicacion getById(Long idUbicacion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Ubicacion.class);
		criteria.add(Restrictions.eq("idUbicacion", idUbicacion));
		List<Ubicacion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ubicacion> getByPromocion(Promocion promocion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Ubicacion.class);
		criteria.add(Restrictions.eq("promocion", promocion));
		List<Ubicacion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Ubicacion> getAll() {
		List<Ubicacion> lista = null;
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession(); 
		Query query = session.createSQLQuery("SELECT " + 
			"u.idUbicacion, " +
			"u.lat, " +
			"u.lon, " +
			"p.idPromocion, " +
			"p.id, " +
			"u.estado, " + 
			"u.descripcion " +
			"FROM Ubicacion as u " +
			"LEFT JOIN promocion as p " +
			"ON p.idPromocion = u.promocion");
		
		List<Object[]> listDatos = query.list();
		if(listDatos != null){
			lista = new ArrayList<Ubicacion>();
			for (Object[] datos : listDatos) {
				Ubicacion item = new Ubicacion();
				if(!String.valueOf(datos[0]).equals("null"))
					item.setIdUbicacion(Long.parseLong(String.valueOf(datos[0])));
				if(!String.valueOf(datos[1]).equals("null"))
					item.setLat(Double.parseDouble(String.valueOf(datos[1])));
				if(!String.valueOf(datos[2]).equals("null"))
					item.setLon(Double.parseDouble(String.valueOf(datos[2])));
				
				Promocion promo = new Promocion();
				if(!String.valueOf(datos[3]).equals("null"))
					promo.setIdPromocion(Long.parseLong(String.valueOf(datos[3])));
				if(!String.valueOf(datos[4]).equals("null"))
					promo.setId(String.valueOf(datos[4]));
				item.setPromocion(promo);
				
				if(!String.valueOf(datos[5]).equals("null")){
					Estado estado = new Estado();
					estado.setIdEstado(Long.parseLong(String.valueOf(datos[5])));
					item.setEstado(estado);
				}
					
				
				lista.add(item);
			}
		}
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ReporteTemplate> getNumeroSucursalesPorEstado() {
		List<ReporteTemplate> lista = null;
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession();
		String query1String = "SELECT u.promocion, e.nombre, COUNT(e.nombre) as total "
				+ "FROM ubicacion AS u "
				+ "LEFT JOIN estado AS e "
				+ "ON e.id_estado = u.estado "
				+ "GROUP BY e.nombre ";
		
		Query query = session.createSQLQuery(query1String);
		List<Object[]> listDatos = query.list();
		
		if(listDatos != null){
			BuildingTables building = new BuildingTables();
			lista = building.construirConteoPromocionesPorEstados(listDatos);
		}
		return lista != null && !lista.isEmpty() ? lista : null;
	}
}
