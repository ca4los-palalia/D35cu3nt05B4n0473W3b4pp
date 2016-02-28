/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */
public interface CategoriaProgramaDAO {

	public void save(CategoriaPrograma categoriaPrograma);
	public void delete(CategoriaPrograma categoriaPrograma);
	public CategoriaPrograma getById(Long idCategoriaPrograma);
	public List<CategoriaPrograma> getByPrograma(Programas programa);
	public List<CategoriaPrograma> getByCategoria(Categoria categoria);
	public List<CategoriaPrograma> getAll();
	public CategoriaPrograma getProgramaCategoria(Programas programa, Categoria categoria);
}
