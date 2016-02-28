/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Persona;

/**
 * @author Carlos Palalía López
 */
public interface PersonaDAO {

	public void save(Persona persona);

	public void delete(Persona persona);

	public List<Persona> getByDireccion(Direccion direccion);

	public List<Persona> getByContacto(Contacto contacto);

	public Persona getById(Long persona);

	public List<Persona> getAll();

	public List<Persona> getBySexo(Long sexo);

	public Persona getUltimoRegistroPersona();
}
