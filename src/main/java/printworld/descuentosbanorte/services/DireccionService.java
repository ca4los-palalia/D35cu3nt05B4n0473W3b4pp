/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.DireccionDAO;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Municipio;

/**
 * @author Carlos Palalía López
 */

@Service
public class DireccionService {

	@Autowired
	private DireccionDAO direccionDAO;

	public void save(Direccion direccion) throws DataAccessException {
		direccionDAO.save(direccion);
	}

	public Direccion getById(Long direccion) throws DataAccessException {
		return direccionDAO.getById(direccion);
	}

	public Direccion getUltimoRegistroDireccion(){
		return direccionDAO.getUltimoRegistroDireccion();
	}
	
	public List<Direccion> getByCodigoPostalId(String codigoPostal)
			throws DataAccessException {
		return direccionDAO.getByCodigoPostalId(codigoPostal);
	}

	public List<Direccion> getByEstado(Estado estado)
			throws DataAccessException {
		return direccionDAO.getByEstado(estado);
	}

	public List<Direccion> getByMunicipio(Municipio municipio)
			throws DataAccessException {
		return direccionDAO.getByMunicipio(municipio);
	}

	public List<Direccion> getAll() throws DataAccessException {
		return direccionDAO.getAll();
	}

}
