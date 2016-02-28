/**
 * 
 */
package printworld.descuentosbanorte.utils;

/**
 * @author Carlos Palalía López
 */
public class DescuentosBanorteConstants {

	public final static String MODAL_VIEW_PRODUCTOS = "/modulos/productos/utils/buscarProducto.zul";
	public final static String MODAL_VIEW_DETALLES_REQUISICION = "/modulos/requisicion/requisicionDetalles.zul";
	public final static String CURRENCY_FORMAT = "###,###,###.00";

	public final static class GLOBAL_PAGES {
		public static final String HOME_URL = "/home.zul";
		public static final String HOME_URL_CLIENT = "/cliente.zul";
		public static final String LOGIN_URL = "/login.zul";
		public static final String PRODUCTOS = "/modulos/productos/productos.zul";
		public static final String PRODUCTOS_BUSCADOR = "/modulos/productos/productosBuscador.zul";
		
		public static final String REQUISICION = "/modulos/requisicion/requisicion.zul";
		public static final String REQUISICION_BUSCADOR = "/modulos/requisicion/requisicionBuscador.zul";
		public static final String CONCENTRADO = "/modulos/requisicion/concentrado.zul";
		public static final String COTIZACION = "/modulos/requisicion/cotizacion.zul";
		public static final String COTIZACION_BUSCADOR = "/modulos/requisicion/cotizacionBuscador.zul";
		public static final String USUARIOS = "/modulos/usuarios/usuario.zul";
		public static final String CONTROL_PANEL = "/modulos/controlPanel/controlPanel.zul";
		public static final String REPORTS = "/modulos/reportes/reportes.zul";
		public static final String ORDERS = "/modulos/ordenCompra/ordenCompra.zul";
		public static final String CONTROL_PANEL_COFIGURACION_USUARIO = "/modulos/controlPanel/usuario.zul";
		public static final String MODAL_VIEW_COMPANIA = "/modulos/controlPanel/utils/buscarOrganizaciones.zul";
		public static final String CONTROL_PANEL_USUARIOS_NEGOCIO = "/modulos/controlPanel/usuariosCliente.zul";
	}

	public static final String TOOL_TIP_SAVE_AREA = "Actualizar/Guardar área";
	public static final String TOOL_TIP_SAVE_BANCO = "Actualizar/Guardar banco";
	public static final String TOOL_TIP_SAVE_CONFFYA = "Actualizar/Guardar catalogo conffya";
	public static final String TOOL_TIP_SAVE_PUESTO = "Actualizar o salvar puesto";

	public static final String TOOL_TIP_SAVE_MONEDA = "Actualizar o salvar moneda";
	public static final String TOOL_TIP_SAVE_PRODUCTO = "Actualizar o salvar tipo de productos";

	public static final String TOOL_TIP_DELETE_AREA = "Eliminar área";
	public static final String TOOL_TIP_DELETE_PUESTO = "Eliminar puesto";
	public static final String TOOL_TIP_DELETE_BANCO = "Eliminar banco";
	public static final String TOOL_TIP_DELETE_MONEDA = "Eliminar moneda";
	public static final String TOOL_TIP_DELETE_PRODUCTO = "Eliminar productos";

	public static final String TOOL_TIP_ROW_SELECTED_AREA = "Seleccionar un área";
	public static final String TOOL_TIP_ROW_SELECTED_PUESTO = "Seleccionar un puesto";
	public static final String TOOL_TIP_ROW_SELECTED_BANCO = "Seleccionar un banco";
	public static final String TOOL_TIP_ROW_SELECTED_MONEDA = "Seleccionar una moneda";
	public static final String TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO = "Seleccionar un tipo de producto";
	public static final String TOOL_TIP_ROW_EDICION_NOMBRE = "Clic sobre esta columna para editar nombre";

	public static final String ESTADO_REQUISICION_NUEVA = "RQN";
	public static final String ESTADO_REQUISICION_PENDIENTE = "RQP";
	public static final String ESTADO_REQUISICION_TERMINADA = "RQT";
	public static final String ESTADO_REQUISICION_CANCELADA = "RQC";
	public static final String ESTADO_ORDEN_COMPRA_TERMINADA = "OCT";
	public static final String ESTADO_ORDEN_COMPRA_NUEVA = "OCN";
	public static final String ESTADO_ORDEN_COMPRA_CANCELADA = "OCC";
	public static final String ESTADO_COTIZACION_NUEVA = "CON";
	public static final String ESTADO_COTIZACION_ENVIADA = "COE";
	public static final String ESTADO_COTIZACION_CANCELADA = "COC";
	public static final String ESTADO_COTIZACION_ACEPTADA = "COA";

	public static final String BUSCAR_TODO = "*";
	
	public static final String CLAVE_FOLIO_REQUISICION = "FRQ";
	public static final String CLAVE_FOLIO_COTIZACION = "FCO";
	public static final String CLAVE_FOLIO_ORDEN_COMPRA = "FOC";

	public static final String ARCHIVO_JASPER_REQUISICION_FORMATO = "jasperTemplates/requisicionFormato.jasper";
	public static final String ARCHIVO_JASPER_COTIZACION_FORMATO = "jasperTemplates/cotizacionFormato.jasper";
	public static final String ARCHIVO_JASPER_ORDEN_COMPRA_FORMATO = "jasperTemplates/ordenCompraFormato.jasper";
	public static final String REPORT_PROVEEDOR_NAME_FILE = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock"  + new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores" + new SistemaOperativo().getSeparadorDeArchivos() + "reportProveedores.pdf";
	public static final String REPORT_PROVEEDOR_PARAM1 = "parameter1";
	public static final String REPORT_PROVEEDOR_NOMBRE_EMPRESA = "empresaTitle";
	public static final String REPORT_VARIABLE_PRODUCTO_NAME_FILE = new SistemaOperativo().getDirectorioDeInicioDelUsuario()  + new SistemaOperativo().getSeparadorDeArchivos() + "Stock"  + new SistemaOperativo().getSeparadorDeArchivos() + "Productos" + new SistemaOperativo().getSeparadorDeArchivos() + "reportProductos.pdf";

	public static final String NOMBRE_TEMPORAL_USUARIO_SYSTEMA = "NOMBRE TEMPORAL DE LA EMPRESA";
	public static final Object BENUTZER = "BENUTZER";
	public static final String FIRMA = "FIRMA";
	
	public static final String CARPETA_ARCHIVOS_REQUISICIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Requisiciones"  + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_COTIZACIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Cotizaciones" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_ORDEN_COMPRA = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "OrdenCompra" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_LOGOTIPOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Logotipos" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PRODUCTOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Productos" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PROVEEDORES = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_USUARIOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Users" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String LAYOUT =  new SistemaOperativo().getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock" + new SistemaOperativo().getSeparadorDeArchivos() + "Layout" + new SistemaOperativo().getSeparadorDeArchivos();
	public static final String EXTENCION_EXCEL = ".xls";
	public static final String EXTENCION_PDF = ".pdf";
	public static final String OS_WIN = "WIN";
	public static final String OS_LIN = "*";
	public static final String OS_MAC = "MAC";
	
	//******************//
	
	public static final String PANEL_CONTROL = "Panel de Control";
	public static final String PANEL_CONTROL_ICON = "/images/toolbar/controlPanel18.png";
	
	public static final String PANEL_CONTROL_PROGRAMAS = "Programas";
	public static final String PANEL_CONTROL_PROGRAMAS_ZUL = "/modulos/controlPanel/controlPanelProgramaMain.zul";
	
	public static final String PANEL_CONTROL_CATEGORIAS = "Categorias";
	public static final String PANEL_CONTROL_CATEGORIAS_ZUL = "/modulos/controlPanel/controlPanelCategoria.zul";
	
	public static final String PANEL_CONTROL_PROMOCIONES = "Promociones";
	public static final String PANEL_CONTROL_PROMOCIONES_ZUL = "/modulos/controlPanel/controlPanelPromocion.zul";
	
	public static final String PROGRAMA_JASON_VARIABLE_COVER = "cover";
	
	public static final String CATEGORIA_JASON_VARIABLE_TOP_AD = "top_ad";
	public static final String PROGRAMA_CATEGORIA_JASON_VARIABLE_TITLE = "title";
	public static final String CATEGORIA_JASON_VARIABLE_PROGRAM_ID = "program_id";
	public static final String CATEGORIA_JASON_VARIABLE_LOGO = "logo";
	public static final String CATEGORIA_JASON_VARIABLE_ID = "id";
	public static final String PROGRAMA_CATEGORIA_JASON_VARIABLE_ACCION = "accion";
	
	public static final String PROMOCION_JASON_VARIABLE_SLOGAN = "slogan";
	public static final String PROMOCION_JASON_VARIABLE_ACCION = "accion";
	public static final String PROMOCION_JASON_VARIABLE_NOTIFICAR = "notificar";
	public static final String PROMOCION_JASON_VARIABLE_FOOTER = "footer";
	public static final String PROMOCION_JASON_VARIABLE_URL = "url";
	public static final String PROMOCION_JASON_VARIABLE_CATID = "cat_id";
	public static final String PROMOCION_JASON_VARIABLE_OFERTA = "oferta";
	public static final String PROMOCION_JASON_VARIABLE_UBICACION = "ubicacion";
	public static final String PROMOCION_JASON_VARIABLE_DESCRIPCION = "descripcion";
	public static final String PROMOCION_JASON_VARIABLE_LOGO = "logo";
	public static final String PROMOCION_JASON_VARIABLE_TELEFONO = "telefono";
	public static final String PROMOCION_JASON_VARIABLE_ID = "id";
	public static final String PROMOCION_JASON_VARIABLE_NAME = "name";
	

}
