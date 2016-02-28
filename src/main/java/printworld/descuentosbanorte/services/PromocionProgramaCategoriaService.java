/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.PromocionProgramaCategoriaDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;

/**
 * @author Carlos Palalía López
 */

@Service
public class PromocionProgramaCategoriaService {

	@Autowired
	private PromocionProgramaCategoriaDAO promocionProgramaCategoriaDAO;

	public void save(PromocionProgramaCategoria promocionProgramaCategoria) {
		promocionProgramaCategoriaDAO.save(promocionProgramaCategoria);
	}

	public void delete(PromocionProgramaCategoria promocionProgramaCategoria) {
		promocionProgramaCategoriaDAO.delete(promocionProgramaCategoria);
	}

	public PromocionProgramaCategoria getById(Long idPromocionProgramaCategoria) {
		return promocionProgramaCategoriaDAO.getById(idPromocionProgramaCategoria);
	}

	public List<PromocionProgramaCategoria> getByCategoria(Categoria categoria) {
		return promocionProgramaCategoriaDAO.getByCategoria(categoria);
	}

	public List<PromocionProgramaCategoria> getByPrograma(Programas programa) {
		return promocionProgramaCategoriaDAO.getByPrograma(programa);
	}

	public List<PromocionProgramaCategoria> getByCategoriaPrograma(Programas programa, Categoria categoria) {
		return promocionProgramaCategoriaDAO.getByCategoriaPrograma(programa, categoria);
	}

	public List<PromocionProgramaCategoria> getByEstado(Estado estado) {
		return promocionProgramaCategoriaDAO.getByEstado(estado);
	}

	public List<PromocionProgramaCategoria> getAll() {
		return promocionProgramaCategoriaDAO.getAll();
	}
	
	public PromocionProgramaCategoria getByPromocion(Promocion promocion){
		return promocionProgramaCategoriaDAO.getByPromocion(promocion);
	}
	public PromocionProgramaCategoria getByPromocionSqlNative(long idPromocion){
		return promocionProgramaCategoriaDAO.getByPromocionSqlNative(idPromocion);
	}
}
