package printworld.descuentosbanorte.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zkoss.bind.annotation.Init;

import printworld.descuentosbanorte.VM.BasicStructure;
import printworld.descuentosbanorte.services.OrganizacionService;
import printworld.descuentosbanorte.services.PersonaService;
import printworld.descuentosbanorte.services.UsuarioService;



/**
 * @author Carlos Palalía López
 */
@Repository
public class DatosIniciales extends BasicStructure {

	private static final long serialVersionUID = 3609891275507972007L;

	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private UsuarioService usuariosService;
	
	@Init
	public void init() {
		System.err.println("inicializar");
	}

}
