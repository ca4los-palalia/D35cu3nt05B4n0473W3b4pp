/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.UbicacionDAO;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.ReporteTemplate;
import printworld.descuentosbanorte.domain.Ubicacion;

/**
 * @author Carlos Palalía López
 */

@Service
public class UbicacionService {

	@Autowired
	private UbicacionDAO ubicacionDAO;

	public void save(Ubicacion ubicacion) {
		ubicacionDAO.save(ubicacion);
	}

	public void delete(Ubicacion ubicacion) {
		ubicacionDAO.delete(ubicacion);
	}

	public Ubicacion getById(Long idUbicacion) {
		return ubicacionDAO.getById(idUbicacion);
	}

	public List<Ubicacion> getByPromocion(Promocion promocion) {
		return ubicacionDAO.getByPromocion(promocion);
	}

	public List<Ubicacion> getAll() {
		return ubicacionDAO.getAll();
	}
	
	public List<ReporteTemplate> getNumeroSucursalesPorEstado() {
		return ubicacionDAO.getNumeroSucursalesPorEstado();
	}

}
