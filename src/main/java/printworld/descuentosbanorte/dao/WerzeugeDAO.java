/**
 * 
 */
package printworld.descuentosbanorte.dao;

import java.util.List;

import printworld.descuentosbanorte.domain.Werzeuge;

/**
 * @author Carlos Palalía López
 */
public interface WerzeugeDAO {

	public void save(Werzeuge werzeuge);
	public void delete(Werzeuge werzeuge);
	public Werzeuge getById(Long idWerzeuge);
	public List<Werzeuge> getAll();
	

}
