/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.TelefonoDAO;
import printworld.descuentosbanorte.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Service
public class TelefonoService {

	@Autowired
	private TelefonoDAO telefonoDAO;

	public void save(Telefono telefono) throws DataAccessException {
		telefonoDAO.save(telefono);
	}

	public void delete(Telefono telefono) throws DataAccessException {
		telefonoDAO.delete(telefono);
	}

	public Telefono getById(Long idTelefono) throws DataAccessException {
		return telefonoDAO.getById(idTelefono);
	}

	public List<Telefono> getAll() throws DataAccessException {
		return telefonoDAO.getAll();
	}
	
	public Telefono getUltimoregistroEmail() throws DataAccessException {
		return telefonoDAO.getUltimoregistroEmail();
	}

}
