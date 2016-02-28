/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */
public interface ProgramasDAO {

	public void save(Programas programas);
	public void delete(Programas programas);
	public Programas getById(Long idPrograma);
	public Programas getByIdJSon(Integer id);
	public Programas getByNombre(String nombre);
	public List<Programas> getAll();
	public List<Programas> getPrivados(boolean privado);
	public Programas getByIdSqlNative(Long idPrograma);
}
