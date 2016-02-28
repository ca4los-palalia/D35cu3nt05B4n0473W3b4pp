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
public interface CategoriaDAO {

	public void save(Categoria categoria);

	public void delete(Categoria categoria);

	public Categoria getById(Long idCategoria);
	
	public Categoria getByIdSqlNative(Long idCategoria);

	public List<Categoria> getByPrograma(Programas programa);
	public List<Categoria> getByProgramaVisibles(Programas programa);
	
	public List<Categoria> getByProgramaLikeNombreCategoria(Programas programa, String nombre);

	public List<Categoria> getAll();

	public List<Categoria> getPrivados(Boolean privado);

	public List<Categoria> getCategoriasNoPertenecenAPrograma(List<CategoriaPrograma> idsCategrogias);
	
	public List<Categoria> getFiltering(String nombre);

	public Categoria getByIdJSon(Integer id);
}
