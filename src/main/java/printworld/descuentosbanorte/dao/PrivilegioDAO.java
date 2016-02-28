/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Privilegios;
import printworld.descuentosbanorte.domain.Usuarios;
import printworld.descuentosbanorte.utils.UserPrivileges;

/**
 * @author Carlos Palalía López
 */
public interface PrivilegioDAO {

	void save(Privilegios privilegios);

	void delete(Privilegios privilegios);

	List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios);

	List<Privilegios> getUsuariosByPrivilegio(UserPrivileges cotizarAutorizar);

}
