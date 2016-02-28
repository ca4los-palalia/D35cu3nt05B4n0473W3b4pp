//Agregar el document ready- llamar a los metodos

zk.afterMount(function()
{
	mostrarTextoEnCampos();
	//jq(".contentDireccion").hover(mostrarTextoEnCampos);
});		

function mostrarTextoEnCampos()
{
	//#Home
	//-Sección Registro y Login
	jq("$usuarioLogin").Watermark("Usuario");
	jq("$passwordLogin").Watermark("Contrase\xF1a");
	jq("$emailRegistro").Watermark("Correo electr\xF3nico");
	jq("$passwordRegistro").Watermark("Contrase\xF1a");
	jq(".txtActivar").Watermark("Activa tu cuenta ya");
	
	//-Sección Búsqueda
	jq(".txtBuscar").Watermark("Buscar...");
	//#Workspace
	//-agregarNuevoNegocio.zul -configurarCuentaNegocio.zul
	jq(".txtNombreNegocio").Watermark("Nombre del negocio");
	jq(".fileCer").Watermark(".cer");
	jq(".fileKey").Watermark(".key");
	jq(".filePass").Watermark("Contrase\xF1a");
	//
	jq(".txtRFC").Watermark("RFC");
	jq(".txtRazonSocial").Watermark("Raz\xF3n social");
	jq(".txtNombreRazonSocial").Watermark("Nombre / Raz\xF3n social");
	//	
	jq(".txtNombreSucursal").Watermark("Nombre de sucursal");
	//$NOTA:Nombre de clase usado  para los campos que se repiten en multiples secciones
	jq(".txtCalle").Watermark("Calle");
	jq(".txtNoExterior").Watermark("No. Ext.");
	jq(".txtNoInterior").Watermark("No. Int.");
	jq(".txtColonia").Watermark("Colonia");
	jq(".txtCP").Watermark("C.P.");
	jq(".txtReferencia").Watermark("Referencia");
	jq(".txtLocalidad").Watermark("Localidad");
	jq(".txtCorreoElectronico").Watermark("Correo electr\xF3nico");
	jq(".txtMunicipio").Watermark("Municipio");
	jq(".txtCiudad").Watermark("Ciudad");
	//-datosPersonalesPerfil.zul -configurarCuentaNegocio.zul
	jq(".txtContraseniaAnt").Watermark("Contrase\xF1a anterior");
	jq(".txtPassword").Watermark("Contrase\xF1a");
	jq(".txtRepPassword").Watermark("Repetir contrase\xF1a");
	jq(".txtNombre").Watermark("Nombre");
	jq(".txtApPaterno").Watermark("Apellido paterno");
	jq(".txtApMaterno").Watermark("Apellido materno");	
	jq(".txtEmailCompany").Watermark("email@company.com");
	//-
	jq(".cmbTipoFact .z-combobox-inp").Watermark("Tipo de facturaci\xF3n");
	jq(".cmbSucursal .z-combobox-inp").Watermark("Sucursal");
	jq(".cmbPais .z-combobox-inp").Watermark("Pais");
	jq(".cmbEstado .z-combobox-inp").Watermark("Estado");
	jq(".cmbCiudad .z-combobox-inp").Watermark("Ciudad");
	jq(".cmbMunicipio .z-combobox-inp").Watermark("Municipio");
	//- configurarCuentaNegocio.zul
	jq(".txtEmail").Watermark("Email");
	//- reportes.zul
	jq(".txtSerie").Watermark("Serie");	
	jq(".txtMetodoPago .z-combobox-inp").Watermark("M\xE9todo de pago");
	jq(".txtFolio").Watermark("Folio");
	jq(".txtMontoTotal").Watermark("Monto total");
	//-wFactura.zul
	jq(".txtCantidad").Watermark("Cantidad");
	jq(".txtUnidad").Watermark("Unidad");
	jq(".txtDescripcion").Watermark("Descripci\xF3n");
	jq(".txtValorUnitario").Watermark("Valor Unitario");
	jq(".txtImporte").Watermark("Importe");
	jq(".txtCorreoElectronico").Watermark("Correo Electr\xD3nico");
	//-wFactura.zul
	jq(".txtAbreviatura").Watermark("Abreviatura");
	jq(".txtPorcentaje").Watermark("Porcentaje %"); 
	jq(".txtTipoCambio").Watermark("Tipo de Cambio");
	jq(".txtNombreDescuento").Watermark("Nombre nuevo descuento");
	jq(".txtTituloCampo").Watermark("T\xEDtulo nuevo campo");	
	jq(".txtNombreEtiqueta").Watermark("Nombre nueva etiqueta");
	jq(".txtNombreLeyenda").Watermark("Nombre nueva leyenda");
	jq(".txtNombreFirma").Watermark("Nombre nueva firma");	

	//--Página Donataria
	jq(".numeroAutorizacion").Watermark("N\xFAmero de autorizaci\xF3n");
	jq(".fechaAutorizacion").Watermark("Fecha de autorizaci\xF3n");	
	
	//--Página Carta Porte
	jq(".txtOrigen").Watermark("Origen");
	jq(".txtChofer").Watermark("Chofer");
	jq(".txtMercancia").Watermark("Mercancia");
	jq(".txtFechaTransporte").Watermark("Fecha del Transporte");
	jq(".txtDestino").Watermark("Destino");
	jq(".txtMatriculas").Watermark("Matriculas");
	jq(".txtPeso").Watermark("Peso");
	
	//--Pagina Configuracion
	jq(".servidorSMTP").Watermark("Servidor SMTP");
	jq(".puertoSalida").Watermark("Puerto de S\xE1lida");	
	jq(".emailSalida").Watermark("E-mail de salida");
	jq(".contenidoMail").Watermark("Contenido Mail");
	jq(".txtClave").Watermark("Clave");
	jq(".txtCambio").Watermark("Cambio");
	jq(".tipoMoneda").Watermark("Tipo Moneda (ej. pesos)");

	//--Pagina Productos / Servicios 
	jq(".txtPrecioCompra").Watermark("Precio de Compra");
	jq(".txtPrecioVenta").Watermark("Precio de Venta");
	//jq(".txtUnidad").Watermark("Unidad");
	jq(".txtNumIdentificacion").Watermark("N\xFAm Identificaci\xF3n");
	jq(".txtCodigoBarras").Watermark("C\xF3digo de Barras");		
}

function showNameWatermark()
{	
	jq(".txtNumTel").Watermark("N\xFAm.");
	jq(".txtExtTel").Watermark("Ext.");	
}

function setSelectedButton(btn, seccion)
{
	//alert("."+seccion + " .btnPeriodos");
	jq("."+seccion + " .btnPeriodos").attr("class","btnPeriodos");
	//jq("." + seccion + " input[type='button']").attr("class","btnPeriodos");
	jq(btn).attr("class","btnPeriodos btnPeriodoSelected");
}