/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Telefono;

/**
 * @author Carlos Palalía López
 */
public interface TelefonoDAO {
	public void save(Telefono telefono);

	public void delete(Telefono telefono);

	public Telefono getById(Long idTelefono);

	public List<Telefono> getAll();
	
	public Telefono getUltimoregistroEmail();
}
