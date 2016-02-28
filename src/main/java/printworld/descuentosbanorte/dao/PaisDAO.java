/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Pais;

/**
 * @author Carlos Palalía López
 */
public interface PaisDAO {

	public void save(Pais pais);
	public void delete(Pais pais);
	public List<Pais> getAll();
	public Pais findById(Long idPais);
}
