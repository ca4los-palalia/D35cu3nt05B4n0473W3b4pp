/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.EstadoDAO;
import printworld.descuentosbanorte.domain.Estado;

/**
 * @author Carlos Palalía López
 */

@Service
public class EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	public void save(Estado estado) throws DataAccessException {
		estadoDAO.save(estado);
	}

	public void delete(Estado estado) throws DataAccessException {
		estadoDAO.delete(estado);
	}

	public Estado getById(Long estado) throws DataAccessException {
		return estadoDAO.getById(estado);
	}

	public List<Estado> getAll() throws DataAccessException {
		return estadoDAO.getAll();
	}
	
	
	public Estado getByNombre(String nombre){
		return estadoDAO.getByNombre(nombre);
	}
	public Estado getByClave(String clave){
		return estadoDAO.getByClave(clave);
	}
	public Estado getByAbreviatura(String abreviatura){
		return estadoDAO.getByAbreviatura(abreviatura);
	}

}
