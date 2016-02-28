package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

public interface ContactoDAO {

	public void save(Contacto contacto);

	public void delete(Contacto contacto);

	public Contacto getById(Long idContacto);

	public Contacto getByTelefono(Telefono telefono);

	public Contacto getByIdEmail(Email email);

	public List<Contacto> getAll();

	public Contacto getUltimoRegistroContacto();

	public Contacto getContactoByEmail(Email email);
}
