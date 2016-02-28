/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.PromocionDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.utils.BuildingTables;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;

/**
 * @author Carlos Palalía López
 */
@Repository
public class PromocionDAOImpl extends HibernateDAOSuportUtil implements
		PromocionDAO {

	@Transactional
	public void save(Promocion promocion) {
		getHibernateTemplate().saveOrUpdate(promocion);
	}

	@Transactional
	public void delete(Promocion promocion) {
		getHibernateTemplate().delete(promocion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Promocion getById(Long idPromocion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		criteria.add(Restrictions.eq("idPromocion", idPromocion));
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getByCategoria(Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		criteria.add(Restrictions.eq("categoria", categoria));
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getByPrograma(Programas programa) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		criteria.add(Restrictions.eq("programas", programa));
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getByCategoriaPrograma(Programas programa, Categoria categoria) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		criteria.add(Restrictions.eq("programas", programa));
		criteria.add(Restrictions.eq("categoria", categoria));
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getByEstado(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Promocion getByCatId(String cat_id) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Promocion.class);
		criteria.add(Restrictions.eq("cat_id", cat_id));
		List<Promocion> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Promocion> getAllSqlNative() {
		
		List<Promocion> lista = null;
		SessionFactory factory = getHibernateTemplate().getSessionFactory();
		Session session = factory.openSession();
		String query1String = "SELECT * FROM Promocion";
		String query2String = "SELECT " +
				"promo.idPromocion, " +
				"promo.accion, " +
				"promo.banner, " +
				"promo.bannerByte, " +
				"promo.cat_id, " +
				"promo.descripcion, " +
				"promo.descripcionPromocion, " +
				"promo.editingStatus, " +
				"promo.footer, " +
				"promo.id, " +
				"promo.logo, " +
				"promo.logoByte, " +
				"promo.`name`, " +
				"promo.nombre, " +
				"promo.notificar, " +
				"promo.oferta, " +
				"promo.privado, " +
				"promo.slogan, " +
				"promo.telefono, " +
				"promo.ultimaActualizacion, " +
				"promo.url, " +
				"promo.visible, " +
				"promo.contacto, " +
				"promo.direccion, " +
				"cat.nombreCategoria, " +
				"programa.nombre " +
				"FROM promocion as promo " +
				"LEFT JOIN PromocionProgramaCategoria as detail " +
				"ON promo.idPromocion = detail.promocion  " +
				"LEFT JOIN categoria as cat " +
				"ON cat.idCategoria = detail.categoria " +

				"LEFT JOIN programas as programa " +
				"ON programa.idPrograma = detail.programas " +

				"ORDER BY promo.nombre ";
		Query query = session.createSQLQuery(query2String);
		
		List<Object[]> listDatos = query.list();
		
		if(listDatos != null){
			BuildingTables building = new BuildingTables();
			lista = building.construirPromocion(listDatos, true);
		}
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	public Promocion getByIdSqlNative(Long idPromocion) {
		// TODO Auto-generated method stub
		return null;
	}
}
