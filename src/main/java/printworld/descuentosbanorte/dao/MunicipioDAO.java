/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Municipio;

/**
 * @author Carlos Palalía López
 */
public interface MunicipioDAO {

	public void save(Municipio estado);

	public void delete(Municipio estado);

	public Municipio getById(Long idMunicipio);

	public List<Municipio> getAll();
}
