/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */
public interface PromocionDAO {

	public void save(Promocion promocion);
	public void delete(Promocion promocion);
	public Promocion getById(Long idPromocion);
	public Promocion getByCatId(String cat_id);
	public List<Promocion> getByCategoria(Categoria categoria);
	public List<Promocion> getByPrograma(Programas programa);
	public List<Promocion> getByCategoriaPrograma(Programas programa, Categoria categoria);
	public List<Promocion> getByEstado(Estado estado);
	public List<Promocion> getAll();
	public List<Promocion> getAllSqlNative();
	public Promocion getByIdSqlNative(Long idPromocion);
	

}
