/**
 * 
 */
package printworld.descuentosbanorte.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import printworld.descuentosbanorte.dao.ProgramasDAO;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProgramasService {

	@Autowired
	private ProgramasDAO programasDAO;

	public void save(final Programas programas) throws DataAccessException{
		programasDAO.save(programas);
	}
	public void delete(final Programas programas) throws DataAccessException{
		programasDAO.delete(programas);
	}
	public Programas getById(final Long idPrograma){
		return programasDAO.getById(idPrograma);
	}
	public List<Programas> getAll(){
		return programasDAO.getAll();
	}
	
	public Programas getByNombre(String nombre){
		return programasDAO.getByNombre(nombre);
	}
	
	public List<Programas> getPrivados(boolean privado){
		return programasDAO.getPrivados(privado);
	}
	
	public Programas getByIdJSon(Integer id){
		return programasDAO.getByIdJSon(id);
	}
	
	public Programas getByIdSqlNative(Long idPrograma){
		return programasDAO.getByIdSqlNative(idPrograma);
	}

}
