/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.CategoriaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	public void save(Categoria categoria) {
		categoriaDAO.save(categoria);
	}

	public void delete(Categoria categoria) {
		categoriaDAO.delete(categoria);
	}

	public Categoria getById(Long idCategoria) {
		return categoriaDAO.getById(idCategoria);
	}

	public List<Categoria> getByPrograma(Programas programa) {
		return categoriaDAO.getByPrograma(programa);
	}

	public List<Categoria> getAll() {
		return categoriaDAO.getAll();
	}

	public List<Categoria> getPrivados(Boolean privado) {
		return categoriaDAO.getPrivados(privado);
	}

	public List<Categoria> getCategoriasNoPertenecenAPrograma(List<CategoriaPrograma> idsCategrogias) {
		return categoriaDAO.getCategoriasNoPertenecenAPrograma(idsCategrogias);
	}

	public List<Categoria> getByProgramaLikeNombreCategoria(Programas programa, String nombre) {
		return categoriaDAO.getByProgramaLikeNombreCategoria(programa, nombre);
	}
	
	public List<Categoria> getFiltering(String nombre) {
		return categoriaDAO.getFiltering(nombre);
	}
	
	public Categoria getByIdJSon(Integer id){
		return categoriaDAO.getByIdJSon(id);
	}

	public List<Categoria> getByProgramaVisibles(Programas programa){
		return categoriaDAO.getByProgramaVisibles(programa);
	}
	public Categoria getByIdSqlNative(Long idCategoria){
		return categoriaDAO.getByIdSqlNative(idCategoria);
	}
}
