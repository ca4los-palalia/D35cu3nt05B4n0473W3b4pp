/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Estado;


/**
 * @author Carlos Palalía López
 */
public interface EstadoDAO {
	public void save(Estado estado);

	public void delete(Estado estado);

	public Estado getById(Long idEstado);
	public Estado getByNombre(String nombre);
	public Estado getByClave(String clave);
	public Estado getByAbreviatura(String abreviatura);

	public List<Estado> getAll();
}
