/**
 * 
 */
package printworld.descuentosbanorte.VM;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import printworld.descuentosbanorte.domain.Organizacion;
import printworld.descuentosbanorte.utils.SessionUtils;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;

/**
 * @author Carlos Palalía López
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class HeaderVM extends BasicStructure {

	private static final long serialVersionUID = -1635442587326363484L;
	private String compania;
	private String usuario;
	private int totalTransacciones;
	private Organizacion organizacion;

	@Init
	public void init() {
		organizacion = (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
		if (organizacion != null) {
			compania = organizacion.getNombre();
		}
	}

	@Command
	public void logOut() {
		sessionUtils.logOut();
		descuentosBanorteUtils.redirect(DescuentosBanorteConstants.GLOBAL_PAGES.LOGIN_URL);
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getTotalTransacciones() {
		return totalTransacciones;
	}

	public void setTotalTransacciones(int totalTransacciones) {
		this.totalTransacciones = totalTransacciones;
	}

}
