/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.EmailDAO;
import printworld.descuentosbanorte.domain.Email;

/**
 * @author Carlos Palalía López
 */

@Service
public class EmailService {

	@Autowired
	private EmailDAO emailDAO;

	public void save(Email email) throws DataAccessException {
		emailDAO.save(email);
	}

	public void delete(Email email) throws DataAccessException {
		emailDAO.delete(email);
	}

	public Email getById(Long proyecto) throws DataAccessException {
		return emailDAO.getById(proyecto);
	}

	public List<Email> getAll() throws DataAccessException {
		return emailDAO.getAll();
	}
	
	public Email getUltimoRegistroEmail() throws DataAccessException {
		return emailDAO.getUltimoRegistroEmail();
	}

}
