/**
 * 
 */
package printworld.descuentosbanorte.VM;

import java.io.File;
import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Municipio;
import printworld.descuentosbanorte.domain.Pais;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Usuarios;
import printworld.descuentosbanorte.domain.Werzeuge;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.SessionUtils;
import printworld.descuentosbanorte.utils.SistemaOperativo;

/**
 * @author Carlos Palalía López
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVM extends LoginMetadatosVM {

	private static final long serialVersionUID = -2184499179368861931L;
	private String user;
	private String password;
	private SistemaOperativo so = new SistemaOperativo();

	public LoginVM() {
		hidde1 = true;
		iniciarImagenes(1);
		
		//System.err.println(werzeugeService.getById(5L));
		
		Executions.getCurrent().sendRedirect("/modulos/mobile/mobile.zul");
		
	}
	@SuppressWarnings("unused")
	@NotifyChange({ "user", "password" })
	@Command
	public void authenticateUser() {
		try {
			Usuarios usuario = usuarioService.getUsuarioByCredentials(user, password);
			
			PERFORM_AUTENTICATION: {
				if (usuario == null) {
					DescuentosBanorteUtils.showSuccessmessage(
							"Las credenciales de acceso son incorrectas. " + "Por favor intente nuevamente",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
					user = "";
					password = "";
					return;
				} else {
					// ----------------------------------
					obtenerDatosJSonFromURL();
					insertProgramas();
					// ----------------------------------
					sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
					sessionUtils.addToSession(SessionUtils.FIRMA, usuario.getOrganizacion());
					descuentosBanorteUtils.redirect(DescuentosBanorteConstants.GLOBAL_PAGES.HOME_URL);
					//descuentosBanorteUtils.redirect(DescuentosBanorteConstants.GLOBAL_PAGES.HOME_URL_CLIENT);

					/*
					 * fechaEstablecida =
					 * stockUtils.convertirStringToCalendar(5, 9, 2014); int
					 * comparacion =
					 * Calendar.getInstance().compareTo(fechaEstablecida);
					 * if(comparacion < 1){
					 * sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
					 * sessionUtils.addToSession(SessionUtils.FIRMA,
					 * usuario.getOrganizacion());
					 * stockUtils.redirect(StockConstants.GLOBAL_PAGES.HOME_URL)
					 * ; }else StockUtils.showSuccessmessage(
					 * "El sistema ha sido bloqueado. " +
					 * "Consulte al administrador",
					 * Clients.NOTIFICATION_TYPE_ERROR, 0, null);
					 */
					System.err.println(so.getDirectorioDeInicioDelUsuario() + "Slash: " + so.getSeparadorDeArchivos());
					File folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_COTIZACIONES);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_LOGOTIPOS);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_PRODUCTOS);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_PROVEEDORES);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_REQUISICIONES);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.CARPETA_ARCHIVOS_USUARIOS);
					if (!folder.exists())
						folder.mkdirs();
					folder = new File(DescuentosBanorteConstants.LAYOUT);
					if (!folder.exists())
						folder.mkdirs();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private void insertProgramas() {

		programa = programasService.getByNombre(DescuentosBanorteConstants.PANEL_CONTROL);
		if (programa == null) {
			Programas panelControl = new Programas();
			panelControl.setNombre(DescuentosBanorteConstants.PANEL_CONTROL);
			panelControl.setLogo(DescuentosBanorteConstants.PANEL_CONTROL_ICON);
			panelControl.setUltimaActualizacion(new Date());
			panelControl.setPrivado(true);
			programasService.save(panelControl);

			Categoria categoriaProgramas = new Categoria();
			categoriaProgramas.setNombreCategoria(DescuentosBanorteConstants.PANEL_CONTROL_PROGRAMAS);
			categoriaProgramas.setZul(DescuentosBanorteConstants.PANEL_CONTROL_PROGRAMAS_ZUL);
			categoriaProgramas.setPrograma(panelControl);
			categoriaProgramas.setUltimaActualizacion(new Date());
			categoriaProgramas.setPrivado(true);
			categoriaProgramas.setOrden(1);
			categoriaService.save(categoriaProgramas);

			Categoria categoriaCategorias = new Categoria();
			categoriaCategorias.setNombreCategoria(DescuentosBanorteConstants.PANEL_CONTROL_CATEGORIAS);
			categoriaCategorias.setZul(DescuentosBanorteConstants.PANEL_CONTROL_CATEGORIAS_ZUL);
			categoriaCategorias.setPrograma(panelControl);
			categoriaCategorias.setUltimaActualizacion(new Date());
			categoriaCategorias.setPrivado(true);
			categoriaCategorias.setOrden(2);
			categoriaService.save(categoriaCategorias);

			Categoria categoriaPromociones = new Categoria();
			categoriaPromociones.setNombreCategoria(DescuentosBanorteConstants.PANEL_CONTROL_PROMOCIONES);
			categoriaPromociones.setZul(DescuentosBanorteConstants.PANEL_CONTROL_PROMOCIONES_ZUL);
			categoriaPromociones.setPrograma(panelControl);
			categoriaPromociones.setUltimaActualizacion(new Date());
			categoriaPromociones.setPrivado(true);
			categoriaPromociones.setOrden(3);
			categoriaService.save(categoriaPromociones);
		}

		if (paisService.getAll() == null) {
			Pais pais = new Pais();
			pais.setNombre("México");
			pais.setAbreviatura("MX");
			paisService.save(pais);

			pais = new Pais();
			pais.setNombre("EUUA");
			pais.setAbreviatura("USA");
			paisService.save(pais);

			pais = new Pais();
			pais.setNombre("Canada");
			pais.setAbreviatura("CA");
			paisService.save(pais);
		}

		if (estadoService.getAll() == null) {
			Estado estado = new Estado();
			estado.setNombre("Puebla");
			estadoService.save(estado);

			Municipio municipio = new Municipio();
			municipio.setEstado(estado);
			municipio.setNombre("Puebla");
			municipioService.save(municipio);

			municipio = new Municipio();
			municipio.setEstado(estado);
			municipio.setNombre("Cholula");
			municipioService.save(municipio);

			estado = new Estado();
			estado.setNombre("México");
			estadoService.save(estado);

			estado = new Estado();
			estado.setNombre("Veracruz");
			estadoService.save(estado);
		}
		// -------------------------------------

	}
	
	private void obtenerDatosJSonFromURL(){
		if(werzeugeService.getAll() == null){
			datosGlobalesJSON = inicializarConexionJsonUrl();
			Werzeuge util = new Werzeuge();
			util.setNombre("Data source");
			util.setUltimaActualizacion(new Date());
			util.setjSonSource(datosGlobalesJSON.toString().getBytes());
			werzeugeService.save(util);
		}
	}

	
}
