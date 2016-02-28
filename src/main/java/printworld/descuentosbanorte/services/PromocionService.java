/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.PromocionDAO;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */

@Service
public class PromocionService {

	@Autowired
	private PromocionDAO promocionDAO;

	public void save(Promocion promocion) {
		promocionDAO.save(promocion);
	}

	public void delete(Promocion promocion) {
		promocionDAO.delete(promocion);
	}

	public Promocion getById(Long idPromocion) {
		return promocionDAO.getById(idPromocion);
	}
	
	public Promocion getByCatId(String cat_id){
		return promocionDAO.getByCatId(cat_id);
	}

	public List<Promocion> getByCategoria(Categoria categoria) {
		return promocionDAO.getByCategoria(categoria);
	}

	public List<Promocion> getByPrograma(Programas programa) {
		return promocionDAO.getByPrograma(programa);
	}

	public List<Promocion> getByCategoriaPrograma(Programas programa, Categoria categoria) {
		return promocionDAO.getByCategoriaPrograma(programa, categoria);
	}

	public List<Promocion> getByEstado(Estado estado) {
		return promocionDAO.getByEstado(estado);
	}

	public List<Promocion> getAll() {
		return promocionDAO.getAll();
	}
	public List<Promocion> getAllSqlNative(){
		return promocionDAO.getAllSqlNative();
	}
	public Promocion getByIdSqlNative(Long idPromocion) {
		return promocionDAO.getByIdSqlNative(idPromocion);
	}
}
