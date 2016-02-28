/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.PersonaDAO;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Persona;

/**
 * @author Carlos Palalía López
 */
@Service
public class PersonaService {

	@Autowired
	private PersonaDAO personaDAO;

	public void save(final Persona persona) throws DataAccessException {
		personaDAO.save(persona);
	}

	public Persona getById(final Long persona) throws DataAccessException {
		return personaDAO.getById(persona);
	}

	public List<Persona> getAll() throws DataAccessException {
		return personaDAO.getAll();
	}

	public List<Persona> getBySexo(final Long sexo) throws DataAccessException {
		return personaDAO.getBySexo(sexo);
	}

	public void delete(final Persona persona) throws DataAccessException {
		personaDAO.delete(persona);
	}

	public List<Persona> getByDireccion(final Direccion direccion)
			throws DataAccessException {
		return personaDAO.getByDireccion(direccion);
	}

	public List<Persona> getByContacto(final Contacto contacto)
			throws DataAccessException {
		return personaDAO.getByContacto(contacto);
	}
	
	public Persona getUltimoRegistroPersona() throws DataAccessException {
		return personaDAO.getUltimoRegistroPersona();
	}

}
