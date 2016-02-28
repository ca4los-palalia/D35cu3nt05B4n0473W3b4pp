/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.ReporteTemplate;
import printworld.descuentosbanorte.domain.Ubicacion;

/**
 * @author Carlos Palalía López
 */
public interface UbicacionDAO {

	public void save(Ubicacion ubicacion);
	public void delete(Ubicacion ubicacion);
	public Ubicacion getById(Long idUbicacion);
	public List<Ubicacion> getByPromocion(Promocion promocion);
	public List<Ubicacion> getAll();
	public List<ReporteTemplate> getNumeroSucursalesPorEstado();

}
