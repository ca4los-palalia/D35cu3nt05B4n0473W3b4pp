/**
 * 
 */
package printworld.descuentosbanorte.app.cliente;

import org.zkoss.zk.ui.select.annotation.VariableResolver;

/**
 * @author Carlos Palalía López
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ClienteVM extends ClienteMetadatosVM {

	private static final long serialVersionUID = -5397620859125560628L;
	
	public ClienteVM() {
		//programas = programasService.getAll();
		hidde1 = true;
		iniciarImagenes(1);
		// TODO Auto-generated constructor stub
	}
}
