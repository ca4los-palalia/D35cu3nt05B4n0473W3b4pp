/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.ContactoDAO;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Service
public class ContactoService {

	@Autowired
	private ContactoDAO contactoDAO;

	public void save(final Contacto contacto) throws DataAccessException {
		contactoDAO.save(contacto);
	}

	public void delete(final Contacto contacto) throws DataAccessException {
		contactoDAO.delete(contacto);
	}

	public Contacto getById(final Long idContacto) throws DataAccessException {
		return contactoDAO.getById(idContacto);
	}

	public Contacto getByTelefono(final Telefono telefono)
			throws DataAccessException {
		return contactoDAO.getByTelefono(telefono);
	}

	public Contacto getByIdEmail(final Email email) throws DataAccessException {
		return contactoDAO.getByIdEmail(email);
	}

	public List<Contacto> getAll() throws DataAccessException {
		return contactoDAO.getAll();
	}

	public Contacto getUltimoRegistroContacto() {
		return contactoDAO.getUltimoRegistroContacto();
	}

	public Contacto getContactoByEmail(final Email email) {
		return contactoDAO.getContactoByEmail(email);
	}

}
