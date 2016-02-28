/**
 * 
 */
package printworld.descuentosbanorte.VM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import printworld.descuentosbanorte.domain.Privilegios;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Usuarios;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.SessionUtils;

/**
 * @author Carlos Palalía López
 * 
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MenuVM extends BasicStructure {
	public static final String PAGE_TO_RENDER = "pageToRender";
	private Map<String, Object> args;
	private Usuarios usuario;

	private boolean mostrarRequisiones;
	private boolean mostrarConcentrados;
	private boolean mostrarCotizacionesAutorizaciones;
	private boolean mostrarOrdenesCompra;

	private boolean mostrarPanelControl;
	private boolean ownerOptions;

	@Init
	public void init() {
		args = new HashMap<String, Object>();
		loadPrivileges();
		programas = programasService.getAll();
	}

	@NotifyChange({ "mostrarPanelControl", "mostrarConcentrados", "mostrarCotizacionesAutorizaciones",
			"mostrarOrdenesCompra", "mostrarRequisiones" })
	public void loadPrivileges() {
		usuario = (Usuarios) sessionUtils.getFromSession(SessionUtils.USUARIO);
		if (usuario != null) {
			if (usuario.getClient() != null && usuario.getClient()) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = false;
				return;
			}
			if (usuario.getOwner() != null && usuario.getOwner()) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = true;
				return;
			}
			List<Privilegios> privilegios = privilegioService.getPrivilegiosByUsuario(usuario);
			if (privilegios != null) {
				for (Privilegios privilegio : privilegios) {
					switch (privilegio.getUserPrivileges()) {
					case CONCENTRAR:
						mostrarConcentrados = true;
						break;
					case COTIZAR_AUTORIZAR:
						mostrarCotizacionesAutorizaciones = true;
						break;
					case ORDEN_COMPRA:
						mostrarOrdenesCompra = true;
						break;
					case REQUISION:
						mostrarRequisiones = true;
						break;
					case PANEL_CONTROL:
						mostrarPanelControl = true;
						break;
					}
				}
			}
		}
	}

	@Command
	public void showRequsicionBuscador() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.REQUISICION_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showConcentrado() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.CONCENTRADO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacion() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.COTIZACION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacionBuscador() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.COTIZACION_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showOrders() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.ORDERS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showReports() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.REPORTS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void mostrarConfiguracionUsuario() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.CONTROL_PANEL_COFIGURACION_USUARIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void configurarUsuariosNegocio() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.CONTROL_PANEL_USUARIOS_NEGOCIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	public boolean isMostrarRequisiones() {
		return mostrarRequisiones;
	}

	public void setMostrarRequisiones(boolean mostrarRequisiones) {
		this.mostrarRequisiones = mostrarRequisiones;
	}

	public boolean isMostrarConcentrados() {
		return mostrarConcentrados;
	}

	public void setMostrarConcentrados(boolean mostrarConcentrados) {
		this.mostrarConcentrados = mostrarConcentrados;
	}

	public boolean isMostrarCotizacionesAutorizaciones() {
		return mostrarCotizacionesAutorizaciones;
	}

	public void setMostrarCotizacionesAutorizaciones(boolean mostrarCotizacionesAutorizaciones) {
		this.mostrarCotizacionesAutorizaciones = mostrarCotizacionesAutorizaciones;
	}

	public boolean isMostrarOrdenesCompra() {
		return mostrarOrdenesCompra;
	}

	public void setMostrarOrdenesCompra(boolean mostrarOrdenesCompra) {
		this.mostrarOrdenesCompra = mostrarOrdenesCompra;
	}

	public boolean isMostrarPanelControl() {
		return mostrarPanelControl;
	}

	public void setMostrarPanelControl(boolean mostrarPanelControl) {
		this.mostrarPanelControl = mostrarPanelControl;
	}

	public boolean isOwnerOptions() {
		return ownerOptions;
	}

	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}

	/**********************************/

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("categorias")
	public void onMenuClick(@BindingParam("item") Programas item) {
		
		setPrograma(item);
		descuentosBanorteUtils.showSuccessmessage("Programa "
				+ item.getNombre() + " Seleccionado",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
		
		categorias = categoriaService.getByPrograma(item);
	}

	@Command
	public void seleccionarCategoria() {
		args.put(PAGE_TO_RENDER, categoria.getZul());
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControlPanel() {
		args.put(PAGE_TO_RENDER, DescuentosBanorteConstants.GLOBAL_PAGES.CONTROL_PANEL);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
}
