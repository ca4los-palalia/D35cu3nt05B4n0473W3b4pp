/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.CategoriaProgramaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */

@Service
public class CategoriaProgramaService {

	@Autowired
	private CategoriaProgramaDAO categoriaProgramaDAO;

	public void save(CategoriaPrograma categoriaPrograma) {
		categoriaProgramaDAO.save(categoriaPrograma);
	}

	public void delete(CategoriaPrograma categoriaPrograma) {
		categoriaProgramaDAO.delete(categoriaPrograma);
	}

	public CategoriaPrograma getById(Long idCategoriaPrograma) {
		return categoriaProgramaDAO.getById(idCategoriaPrograma);
	}

	public List<CategoriaPrograma> getByPrograma(Programas programa) {
		return categoriaProgramaDAO.getByPrograma(programa);
	}

	public List<CategoriaPrograma> getByCategoria(Categoria categoria) {
		return categoriaProgramaDAO.getByCategoria(categoria);
	}

	public List<CategoriaPrograma> getAll() {
		return categoriaProgramaDAO.getAll();
	}

	public CategoriaPrograma getProgramaCategoria(Programas programa, Categoria categoria) {
		return categoriaProgramaDAO.getProgramaCategoria(programa, categoria);
	}
}
