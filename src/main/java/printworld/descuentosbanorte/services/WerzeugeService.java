/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.WerzeugeDAO;
import printworld.descuentosbanorte.domain.Werzeuge;

/**
 * @author Carlos Palalía López
 */

@Service
public class WerzeugeService {

	@Autowired
	private WerzeugeDAO werzeugeDAO;

	public void save(Werzeuge werzeuge){
		werzeugeDAO.save(werzeuge);
	}
	public void delete(Werzeuge werzeuge){
		werzeugeDAO.delete(werzeuge);
	}
	public Werzeuge getById(Long idWerzeuge){
		return werzeugeDAO.getById(idWerzeuge);
	}
	public List<Werzeuge> getAll(){
		return werzeugeDAO.getAll();
	}
}
