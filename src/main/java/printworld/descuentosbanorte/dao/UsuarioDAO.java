/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Organizacion;
import printworld.descuentosbanorte.domain.Usuarios;

/**
 * @author Carlos Palalía López
 */
public interface UsuarioDAO {

	Usuarios getUsuarioByCredentials(String usuario, String password);

	void save(Usuarios usuarios);

	void delete(Usuarios usuario);

	List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion);
	
	public List<Usuarios> getAll();

	boolean verificarNombreUsuario(String benutzer, Long idUsuario);

	Usuarios getClienteByOrganizacion(Organizacion organizacion);

	Usuarios getOwner(Organizacion organizacion);

}
