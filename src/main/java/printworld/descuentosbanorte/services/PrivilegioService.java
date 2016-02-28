/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.PrivilegioDAO;
import printworld.descuentosbanorte.domain.Privilegios;
import printworld.descuentosbanorte.domain.Usuarios;
import printworld.descuentosbanorte.utils.UserPrivileges;

/**
 * @author Carlos Palalía López
 */
@Service
public class PrivilegioService {

	@Autowired
	private PrivilegioDAO privilegioDAO;

	public void save(final Privilegios privilegios) {
		privilegioDAO.save(privilegios);
	}

	public void delete(final Privilegios privilegios) {
		privilegioDAO.delete(privilegios);
	}

	public List<Privilegios> getPrivilegiosByUsuario(final Usuarios usuarios) {
		return privilegioDAO.getPrivilegiosByUsuario(usuarios);
	}

	public List<Privilegios> getUsuariosByPrivilegio(
			UserPrivileges cotizarAutorizar) {
		return privilegioDAO.getUsuariosByPrivilegio(cotizarAutorizar);
	}
}
