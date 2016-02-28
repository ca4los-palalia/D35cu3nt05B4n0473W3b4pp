/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.UsuarioDAO;
import printworld.descuentosbanorte.domain.Organizacion;
import printworld.descuentosbanorte.domain.Usuarios;

/**
 * @author Carlos Palalía López
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuarios getUsuarioByCredentials(final String usuario,
			final String password) throws DataAccessException {
		return usuarioDAO.getUsuarioByCredentials(usuario, password);
	}

	public void save(final Usuarios usuarios) {
		usuarioDAO.save(usuarios);
	}

	public void delete(final Usuarios usuario) {
		usuarioDAO.delete(usuario);
	}

	public List<Usuarios> getUsuariosByOrganizacion(
			final Organizacion organizacion) {
		return usuarioDAO.getUsuariosByOrganizacion(organizacion);
	}

	public boolean verificarNombreUsuario(final String benutzer,
			final Long idUsuario) {
		return usuarioDAO.verificarNombreUsuario(benutzer, idUsuario);
	}

	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		return usuarioDAO.getClienteByOrganizacion(organizacion);
	}

	public Usuarios getOwner(Organizacion organizacion) {
		return usuarioDAO.getOwner(organizacion);
	}

	public List<Usuarios> getAll(){
		return usuarioDAO.getAll();
	}
}
