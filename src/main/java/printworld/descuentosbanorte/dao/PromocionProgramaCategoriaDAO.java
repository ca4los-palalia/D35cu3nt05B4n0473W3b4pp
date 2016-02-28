/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;

/**
 * @author Carlos Palalía López
 */
public interface PromocionProgramaCategoriaDAO {

	public void save(PromocionProgramaCategoria promocionProgramaCategoria);
	public void delete(PromocionProgramaCategoria promocionProgramaCategoria);
	public PromocionProgramaCategoria getById(Long idPromocionProgramaCategoria);
	public PromocionProgramaCategoria getByPromocion(Promocion promocion);
	public PromocionProgramaCategoria getByPromocionSqlNative(long idPromocion);
	public List<PromocionProgramaCategoria> getByCategoria(Categoria categoria);
	public List<PromocionProgramaCategoria> getByPrograma(Programas programa);
	public List<PromocionProgramaCategoria> getByCategoriaPrograma(Programas programa, Categoria categoria);
	public List<PromocionProgramaCategoria> getByEstado(Estado estado);
	public List<PromocionProgramaCategoria> getAll();

}
