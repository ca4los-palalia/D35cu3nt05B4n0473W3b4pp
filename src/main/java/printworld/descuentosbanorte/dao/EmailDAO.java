package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Email;

/**
 * @author Carlos Palalía López
 */

public interface EmailDAO {

	public void save(Email email);

	public void delete(Email email);

	public Email getById(Long idEmail);

	public List<Email> getAll();
	
	public Email getUltimoRegistroEmail();
}
