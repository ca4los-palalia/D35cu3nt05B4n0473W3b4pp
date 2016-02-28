/**
 * 
 */
package printworld.descuentosbanorte.VM;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.google.gson.JsonElement;

import printworld.descuentosbanorte.services.CategoriaProgramaService;
import printworld.descuentosbanorte.services.CategoriaService;
import printworld.descuentosbanorte.services.ContactoService;
import printworld.descuentosbanorte.services.DireccionService;
import printworld.descuentosbanorte.services.EmailService;
import printworld.descuentosbanorte.services.EstadoService;
import printworld.descuentosbanorte.services.MunicipioService;
import printworld.descuentosbanorte.services.PaisService;
import printworld.descuentosbanorte.services.PersonaService;
import printworld.descuentosbanorte.services.PrivilegioService;
import printworld.descuentosbanorte.services.ProgramasService;
import printworld.descuentosbanorte.services.PromocionProgramaCategoriaService;
import printworld.descuentosbanorte.services.PromocionService;
import printworld.descuentosbanorte.services.TelefonoService;
import printworld.descuentosbanorte.services.UbicacionService;
import printworld.descuentosbanorte.services.UsuarioService;
import printworld.descuentosbanorte.services.WerzeugeService;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.SessionUtils;

/**
 * @author Carlos Palalía López
 */
public abstract class ServiceLayer extends DataLayer {

	private static final long serialVersionUID = 2608753945339387415L;

	@WireVariable
	protected DescuentosBanorteUtils descuentosBanorteUtils;
	@WireVariable
	protected SessionUtils sessionUtils;
	@WireVariable
	protected PersonaService personaService;
	@WireVariable
	protected UsuarioService usuarioService;
	@WireVariable
	protected PaisService paisService;
	@WireVariable
	protected DireccionService direccionService;
	@WireVariable
	protected MunicipioService municipioService;
	@WireVariable
	protected EmailService emailService;
	@WireVariable
	protected ContactoService contactoService;
	@WireVariable
	protected TelefonoService telefonoService;
	@WireVariable
	protected PrivilegioService privilegioService;
	@WireVariable
	protected ProgramasService programasService;
	@WireVariable
	protected CategoriaService categoriaService;
	@WireVariable
	protected PromocionService comercioService;
	@WireVariable
	protected EstadoService estadoService;
	@WireVariable
	protected CategoriaProgramaService categoriaProgramaService;
	@WireVariable
	protected PromocionService promocionService;
	@WireVariable
	protected PromocionProgramaCategoriaService promocionProgramaCategoriaService;
	@WireVariable
	protected UbicacionService ubicacionService;
	@WireVariable
	protected WerzeugeService werzeugeService;
	@WireVariable
	protected JsonElement datosGlobalesJSON;;

	/**
	 * REQUERIDO PARA OBTENER EL SERVICIO DESDE UNA CLASE QUE NO PUEDE SER
	 * CABLEADA COMO REPOSITORIO Y NO DEBE EXTENDER DE LA CAPA BÁSICA
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	

}
