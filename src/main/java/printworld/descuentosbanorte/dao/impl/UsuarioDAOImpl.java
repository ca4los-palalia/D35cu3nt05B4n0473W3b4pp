/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.UsuarioDAO;
import printworld.descuentosbanorte.domain.Organizacion;
import printworld.descuentosbanorte.domain.Usuarios;

/**
 * @author Carlos Palalía López
 */
@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory)
			throws DataAccessException {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuarios getUsuarioByCredentials(String usuario, String password) {
		List<Usuarios> user = getHibernateTemplate()
				.find("FROM Usuarios as u "
						+ "LEFT JOIN FETCH u.organizacion as o WHERE u.benutzer = ? "
						+ "AND u.kennwort = ?", usuario, password);
		return user.size() > 0 ? user.get(0) : null;
	}

	@Transactional
	public void save(Usuarios usuarios) {
		getHibernateTemplate().saveOrUpdate(usuarios);
	}

	@Transactional
	public void delete(Usuarios usuario) {
		getHibernateTemplate().delete(usuario);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate()
				.find("FROM Usuarios as u "
						+ "LEFT JOIN FETCH u.organizacion as o "
						+ "WHERE u.organizacion = ?"
						+ "AND u.client = ? AND u.owner = ?", organizacion,
						false, false);
		return usuarios.size() > 0 ? usuarios : null;
	}

	@Transactional(readOnly = true)
	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Usuarios.class);
		criteria.add(Restrictions.like("benutzer", "%" + benutzer + "%"));
		criteria.add(Restrictions.ne("idUsuario", idUsuario));
		return criteria.list().size() > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate().find(
				"FROM Usuarios as u WHERE u.organizacion = ? AND u.client = ?",
				organizacion, true);
		return usuarios.size() > 0 ? usuarios.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuarios getOwner(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate().find(
				"FROM Usuarios as u WHERE u.owner = ? ", true);
		return usuarios.size() > 0 ? usuarios.get(0) : null;

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuarios> getAll() {
		List<Usuarios> usuarios = getHibernateTemplate()
				.find("FROM Usuarios as u ");
		return usuarios.size() > 0 ? usuarios : null;
	}
}
