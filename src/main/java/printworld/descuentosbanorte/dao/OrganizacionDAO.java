/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */
public interface OrganizacionDAO {

	void save(Organizacion organizacion);

	void delete(Organizacion organizacion);

	Organizacion getById(Long idOrganizacion);
	
	List<Organizacion> getOrganizaciones();

	List<Organizacion> getCompaniasByNombreRFC(String compania, String rfc);

	List<Organizacion> getCompaniasByNombre(String compania);

	List<Organizacion> getCompaniasByRFC(String rfc);

	List<Organizacion> getAll();

}
