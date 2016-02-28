/**
 * 
 */
package printworld.descuentosbanorte.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import printworld.descuentosbanorte.dao.PrivilegioDAO;
import printworld.descuentosbanorte.domain.Organizacion;
import printworld.descuentosbanorte.domain.Privilegios;
import printworld.descuentosbanorte.domain.Usuarios;
import printworld.descuentosbanorte.utils.HibernateDAOSuportUtil;
import printworld.descuentosbanorte.utils.SessionUtils;
import printworld.descuentosbanorte.utils.UserPrivileges;

/**
 * @author Carlos Palalía López
 */
@Repository
public class PrivilegioDAOImpl extends HibernateDAOSuportUtil implements
		PrivilegioDAO {

	@Autowired
	private SessionUtils sessionUtils;

	@Transactional
	public void save(Privilegios privilegios) {
		getHibernateTemplate().saveOrUpdate(privilegios);
	}

	@Transactional
	public void delete(Privilegios privilegios) {
		getHibernateTemplate().delete(privilegios);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		List<Privilegios> privilegios = getHibernateTemplate().find(
				"FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u "
						+ "WHERE p.usuarios = ?", usuarios);
		return privilegios;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Privilegios> getUsuariosByPrivilegio(UserPrivileges privilegio) {
		List<Privilegios> usuarios = getHibernateTemplate().find(
				"FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u "
						+ "LEFT JOIN FETCH u.persona.contacto.email as e "
						+ "WHERE p.userPrivileges = ? "
						+ "AND u.organizacion = ?", privilegio,
				(Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA));
		return usuarios;
	}
}
