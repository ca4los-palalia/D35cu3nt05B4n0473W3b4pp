package printworld.descuentosbanorte.app.controlPanel;

import java.io.FileWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;
import printworld.descuentosbanorte.domain.ReporteTemplate;
import printworld.descuentosbanorte.domain.Telefono;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.SistemaOperativo;

/**
 * @author Carlos Palalía López
 * 
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControlPanelPromocionVM extends ControlPanelMetadatos {

	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		programas = programasService.getPrivados(false);
		// categorias = categoriaService.getPrivados(false);
		promociones = promocionService.getAll();
		promocion = new Promocion();
		displayEdit = true;

		direccion = new Direccion();
		contacto = new Contacto();
		telefono = new Telefono();
		email = new Email();
		promocionProgramaCategoria = new PromocionProgramaCategoria();
		estados = estadoService.getAll();

		if (promociones != null)
			contadorPromociones = String.valueOf(promociones.size());

		// ----------------------------------------------
		categorias = categoriaService.getPrivados(false);
		categoriaProgramas = categoriaProgramaService.getAll();
		if (categoriaProgramas != null && categoriaProgramas.size() > 0)
			contadorCategoriaPrograma = String.valueOf(categoriaProgramas.size());
		else
			contadorCategoriaPrograma = "0";
		categoria = new Categoria();
		// ----------------------------------------------

	}

	@Command
	@NotifyChange({ "programas", "programa", "bannerCategoria", "logoCategoria", "promocion", "promociones", "estado",
			"estados", "direccion", "telefono", "email", "contacto", "promocionProgramaCategoria",
			"categoriaProgramaSelected", "categoriaProgramas" })
	public void nuevoPrograma() {
		promocion = new Promocion();
		bannerCategoria = null;
		logoCategoria = null;
		estado = new Estado();
		estados = estadoService.getAll();
		direccion = new Direccion();
		telefono = new Telefono();
		email = new Email();
		contacto = new Contacto();
		promocionProgramaCategoria = new PromocionProgramaCategoria();
		categoriaProgramaSelected = new CategoriaPrograma();

		programas = programasService.getPrivados(false);
		categoriaProgramas = new ArrayList<CategoriaPrograma>();
		programas = programasService.getPrivados(false);
		programa = new Programas();
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "programas", "programa", "bannerCategoria", "logoCategoria", "promocion", "promociones", "estado",
			"direccion", "telefono", "email", "contacto", "promocionProgramaCategoria", "", "", "", "" })
	public void guardarPromocion() {
		if (validarPromocion()) {
			if (promociones == null)
				promociones = new ArrayList<Promocion>();

			/* if(promocion.getIdPromocion() == null){ */
			promocion.setUltimaActualizacion(new Date());
			promocion.setEditingStatus(false);
			promocion.setPrivado(false);

			if (logoCategoria != null)
				promocion.setLogoByte(logoCategoria.getByteData());
			if (bannerCategoria != null)
				promocion.setBannerByte(bannerCategoria.getByteData());
			if (!promociones.contains(promocion))
				promociones.add(promocion);

			// ----------------------
			direccion.setEstado(estado);
			direccionService.save(direccion);
			promocion.setDireccion(direccion);

			telefonoService.save(telefono);
			emailService.save(email);
			contacto.setTelefono(telefono);
			contacto.setEmail(email);

			contactoService.save(contacto);
			promocion.setContacto(contacto);

			// ------------------------------

			promocionService.save(promocion);
			promocionProgramaCategoria.setProgramas(programa);
			promocionProgramaCategoria.setCategoria(categoriaProgramaSelected.getCategoria());
			promocionProgramaCategoria.setPromocion(promocion);
			promocionProgramaCategoriaService.save(promocionProgramaCategoria);

			descuentosBanorteUtils.showSuccessmessage("La promoción " + promocion.getNombre() + " ha sido creada",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);

			promocion = new Promocion();
			bannerCategoria = null;
			logoCategoria = null;
			estado = new Estado();
			estados = estadoService.getAll();

			direccion = new Direccion();
			telefono = new Telefono();
			email = new Email();
			contacto = new Contacto();
			promocionProgramaCategoria = new PromocionProgramaCategoria();
			/*
			 * }else descuentosBanorteUtils.showSuccessmessage(
			 * "Utilice el boton de ''actualizar'' para guardar los cambios",
			 * Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			 */
		} else
			descuentosBanorteUtils.showSuccessmessage("Llene los campos marcados con *",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "promociones", "promocion" })
	public void actualizarPrograma() {
		if (promocion != null && promocion.getIdPromocion() != null) {

			promocion.setUltimaActualizacion(new Date());
			promocion.setEditingStatus(false);

			if (logoCategoria != null)
				promocion.setLogoByte(logoCategoria.getByteData());
			if (bannerCategoria != null)
				promocion.setBannerByte(bannerCategoria.getByteData());
			// promociones.add(promocion);

			// ----------------------
			direccion.setEstado(estado);
			direccionService.save(direccion);
			promocion.setDireccion(direccion);

			telefonoService.save(telefono);
			emailService.save(email);
			contacto.setTelefono(telefono);
			contacto.setEmail(email);

			contactoService.save(contacto);
			promocion.setContacto(contacto);

			// ------------------------------

			promocionService.save(promocion);
			promocionProgramaCategoria.setProgramas(programa);
			promocionProgramaCategoria.setCategoria(categoriaProgramaSelected.getCategoria());
			promocionProgramaCategoria.setPromocion(promocion);
			promocionProgramaCategoriaService.save(promocionProgramaCategoria);

			descuentosBanorteUtils.showSuccessmessage(
					"La informacion del programa " + promocion.getNombre() + " ha sido actualizada",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
			/*
			 * promocion = new Promocion(); bannerCategoria= null; logoCategoria
			 * = null; estado = new Estado(); estados = estadoService.getAll();
			 * 
			 * direccion = new Direccion(); telefono = new Telefono(); email =
			 * new Email(); contacto = new Contacto();
			 * promocionProgramaCategoria = new PromocionProgramaCategoria();
			 */
		} else
			descuentosBanorteUtils.showSuccessmessage("Utilice el boton de ''Guardar'' para guardar un nuevo programa",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "promociones", "promocion" })
	public void eliminarPrograma(@BindingParam("promocionStatus") final Promocion promocionEditada) {
		if (promocionEditada != null) {
			Messagebox.show("¿Desea eliminar la Promocion " + promocionEditada.getNombre() + "?", "Elimnar Promocion",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event e) {
							if (Messagebox.ON_OK.equals(e.getName())) {
								try {
									promocionService.delete(promocionEditada);
									promociones.remove(promocionEditada);
									descuentosBanorteUtils.showSuccessmessage(
											"La promocion " + promocionEditada.getNombre() + " ha sido eliminada",
											Clients.NOTIFICATION_TYPE_INFO, 0, null);
								} catch (Exception e2) {
									descuentosBanorteUtils.showSuccessmessage(
											"La promocion " + promocionEditada.getNombre()
													+ " se encuentra en uso. No puede ser eliminada",
											Clients.NOTIFICATION_TYPE_ERROR, 0, null);
								}
							} else if (Messagebox.ON_CANCEL.equals(e.getName())) {
								System.err.println("eliminacion cancelada");
							}
						}
					});
		} else
			descuentosBanorteUtils.showSuccessmessage("Seleccione una promocion para llevar acabo la elminiacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

	}
	/*
	 * @SuppressWarnings("static-access")
	 * 
	 * @NotifyChange({ "promociones", "promocion", "bannerCategoria",
	 * "logoCategoria" })
	 * 
	 * @Command public void confirm(@BindingParam("promocionStatus") Promocion
	 * promocionEditada) { promocionEditada.setPrivado(false);
	 * promocionEditada.setUltimaActualizacion(new Date());
	 * 
	 * if (promocion.getLogoAImage() != null)
	 * promocionEditada.setLogoByte(promocion.getLogoAImage().getByteData()); if
	 * (promocion.getBannerAImage() != null)
	 * promocionEditada.setBannerByte(promocion.getBannerAImage().getByteData())
	 * ;
	 * 
	 * promocionService.save(promocionEditada);
	 * changeEditableStatus(promocionEditada);
	 * refreshRowTemplate(promocionEditada);
	 * descuentosBanorteUtils.showSuccessmessage("Promoción -" +
	 * promocionEditada.getNombre() + "- actualizada",
	 * Clients.NOTIFICATION_TYPE_INFO, 0, null);
	 * promocionEditada.setEditingStatus(false);
	 * promocionService.save(promocionEditada);
	 * 
	 * promocion = new Promocion(); bannerCategoria = null; logoCategoria= null;
	 * }
	 * 
	 * @Command
	 * 
	 * @NotifyChange({ "bannerCategoria", "logoCategoria" }) public void
	 * changeEditableStatus(@BindingParam("promocionStatus") Promocion
	 * promocionEditada) {
	 * promocionEditada.setEditingStatus(!promocionEditada.getEditingStatus());
	 * refreshRowTemplate(promocionEditada); }
	 * 
	 * public void refreshRowTemplate(Promocion promocionEditada) {
	 * resetVariablesEdicion(promocionEditada); BindUtils.postNotifyChange(null,
	 * null, promocionEditada, "editingStatus"); }
	 * 
	 * private void resetVariablesEdicion(Promocion promocionEditada) {
	 * promocion = promocionEditada; bannerCategoria= null; logoCategoria =
	 * null; }
	 */

	@Command
	@NotifyChange("bannerCategoria")
	public void uploadBanner(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				if (lengthofImage > 500 * 1024) {
					Messagebox.show("Please Select a Image of size less than 500Kb.");
					return;
				} else {
					bannerCategoria = (AImage) media;
					promocion.setBannerAImage((AImage) media);
				}
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange("logoCategoria")
	public void uploadLogo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				if (lengthofImage > 500 * 1024) {
					Messagebox.show("Please Select a Image of size less than 500Kb.");
					return;
				} else {
					logoCategoria = (AImage) media;
					promocion.setLogoAImage((AImage) media);
				}
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange({ "contacto", "email", "telefono", "direccion", "estado", "estados", "logoCategoria",
			"bannerCategoria", "promocion", "categoriaProgramaSelected", "categoriaProgramas", "programa", "programas",
			"activarMapa", "numeroUbicaciones" })
	public void seleccionarPromocion() {
		contacto = promocion.getContacto();
		if (contacto != null) {
			email = contacto.getEmail();
			telefono = contacto.getTelefono();
		}

		direccion = promocion.getDireccion();

		estados = new ArrayList<Estado>();
		if (direccion != null) {
			estado = estadoService.getById(direccion.getEstado().getIdEstado());
			estados.add(estado);
		}

		logoCategoria = promocion.getLogoAImage();
		bannerCategoria = promocion.getBannerAImage();

		categoriaProgramaSelected = new CategoriaPrograma();
		PromocionProgramaCategoria promoProgCatTemp = promocionProgramaCategoriaService.getByPromocion(promocion);
		if (promoProgCatTemp != null)
			categoriaProgramaSelected.setCategoria(promoProgCatTemp.getCategoria());
		categoriaProgramas = new ArrayList<CategoriaPrograma>();
		categoriaProgramas.add(categoriaProgramaSelected);

		promoProgCatTemp = promocionProgramaCategoriaService.getByPromocion(promocion);
		if (promoProgCatTemp != null)
			programa = promoProgCatTemp.getProgramas();
		programas = new ArrayList<Programas>();
		programas.add(programa);

		ubicaciones = ubicacionService.getByPromocion(promocion);
		if (ubicaciones != null) {
			activarMapa = true;
			numeroUbicaciones = ubicaciones.size();
			crearAtributosUbicacion(ubicaciones);
		} else {
			activarMapa = false;
			numeroUbicaciones = 0;
		}
	}

	@Command
	@NotifyChange({ "categoriaProgramas" })
	public void cargarCategoriasDePrograma() {
		categoriaProgramas = categoriaProgramaService.getByPrograma(programa);
	}

	private boolean validarPromocion() {
		boolean centinela = true;
		if (promocion.getNombre() == null || promocion.getNombre().equals(""))
			centinela = false;
		else if (programa == null)
			centinela = false;
		else if (categoriaProgramaSelected == null)
			centinela = false;
		return centinela;
	}

	@NotifyChange("promociones")
	@Command
	public void readPromocionFromJSON() {
		try {
			
			fichero = new FileWriter("c:/Development/Extraccion");
			promociones = promocionService.getAll();
			categorias = categoriaService.getPrivados(false);
			programas = programasService.getPrivados(false);
			promocionJSon = new Promocion();
			promocionsJSon = new ArrayList<Promocion>();
			promocionProgramaCategorias = new ArrayList<PromocionProgramaCategoria>();
			ubicaciones = ubicacionService.getAll();
			
			
			// **********************************************************************
			String recuperar = "";
			recuperar = new String(werzeugeService.getById(1L).getjSonSource());
			JsonReader reader = new JsonReader(new StringReader(recuperar));
			parser = new JsonParser();
			datosGlobalesJSON = parser.parse(reader);
			dumpPromocionJSONElement(datosGlobalesJSON);
			// **********************************************************************
			//dumpPromocionJSONElement(inicializarConexionJsonUrl());
			salvarPRomocionJSonToDataBase();

			try {
				if (promocionsJSon.size() > 0)
					promociones = promocionService.getAll();
				Messagebox.show("Se han importado Promociones desde JSON");
				fichero.close();
				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("shutdown -s -t 0");
			} catch (Exception e) {
				Messagebox.show("Se han importado Promociones desde JSON");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void crearAtributosUbicacion(List<Ubicacion> ubic) {

		JSONObject obj = new JSONObject();
		JSONObject atributosPromo = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONArray promoAtributes = new JSONArray();

		for (Ubicacion item : ubic) {

			obj.put("idUbicacion", item.getIdUbicacion());
			obj.put("lat", item.getLat());
			obj.put("lon", item.getLon());

			atributosPromo = setAtributosPromo(atributosPromo, item.getPromocion());
			promoAtributes.put(atributosPromo);

			obj.put("promocion", promoAtributes);

			arr.put(obj);

			obj = new JSONObject();

		}
		sessionUtils.addToSession("nuevasUbicaciones", null);
		sessionUtils.addToSession("coordenadas", arr);

	}

	private JSONObject setAtributosPromo(JSONObject atributosPromo, Promocion promo) {
		atributosPromo.put("idPromocion", promo.getIdPromocion());
		atributosPromo.put("nombre", promo.getNombre());
		atributosPromo.put("banner", promo.getBanner());
		atributosPromo.put("slogan", promo.getSlogan());
		atributosPromo.put("descripcion", promo.getDescripcion());
		atributosPromo.put("direccion", promo.getDireccion());
		atributosPromo.put("contacto", promo.getContacto());
		atributosPromo.put("categoria", promo.getCategoria());
		atributosPromo.put("editingStatus", promo.getEditingStatus());
		atributosPromo.put("privado", promo.getPrivado());
		atributosPromo.put("ultimaActualizacion", promo.getUltimaActualizacion());
		atributosPromo.put("ultimaActualizacionString", promo.getUltimaActualizacionString());
		atributosPromo.put("descripcionPromocion", promo.getDescripcionPromocion());
		atributosPromo.put("logoByte", promo.getLogoByte());
		atributosPromo.put("bannerByte", promo.getBannerByte());
		atributosPromo.put("logoAImage", promo.getLogoAImage());
		atributosPromo.put("bannerAImage", promo.getBannerAImage());
		atributosPromo.put("accion", promo.getAccion());
		atributosPromo.put("notificar", promo.isNotificar());
		atributosPromo.put("footer", promo.getFooter());
		atributosPromo.put("url", promo.getOferta());
		atributosPromo.put("cat_id", promo.getCat_id());
		atributosPromo.put("oferta", promo.getOferta());
		atributosPromo.put("ubicacion", promo.getUbicacion());
		atributosPromo.put("logo", promo.getLogo());
		atributosPromo.put("telefono", promo.getTelefono());
		atributosPromo.put("id", promo.getId());
		atributosPromo.put("name", promo.getName());
		return atributosPromo;
	}

	@Command
	public void generarReporteTodasLasPromociones() {
		ubicaciones = ubicacionService.getAll();
		generarArchivoExcelTodasLasPromociones();
		System.err.println(sessionUtils.getFromSession("user"));
	}
	
	@Command
	public void actualizarEstadosUbicacion(){
		actualizarEstadosDeCoordenadas();
	}
	

	@Command
	@NotifyChange({ "promocionProgramaCategorias", "titlePromoAsignaWin2" })
	public void buscarPromociones() {
		buscarPromocionesMetaData();
	}

	@Command
	@NotifyChange({ "promocion" })
	public void salvarCambioVIsibilidadPromo(@BindingParam("promSel") Promocion promVar) {
		promocion = promVar;
		promocionService.save(promVar);
	}

	@Command
	@NotifyChange({ "promocion" })
	public void salvarNotificacionPromo(@BindingParam("promNotSel") Promocion promVar) {
		promocion = promVar;
		promocionService.save(promVar);
	}

	@Command
	public void generarReportePorEstados() {
		libroExcel = new HSSFWorkbook();
		hojaExcel = libroExcel.createSheet("desglosado");
		filaExcel = null;
		celdaExcel = null;
		textoExcel = null;
		rowExcelDatos = 1;
		generarCabeceraExcelreporteByEstados();
		
		if(programas == null)
			programas = programasService.getPrivados(false);
		if(categorias == null)
			categorias = categoriaService.getPrivados(false);
		if(promociones == null)
			promociones = promocionService.getAllSqlNative();
		if(ubicaciones == null)
			ubicaciones = ubicacionService.getAll();
		List<ReporteTemplate> reporteList = new ArrayList<ReporteTemplate>();
		
		for (Promocion itemPromo : promociones) {
			Categoria catTmp = getCategoriaByIdNoSQL(itemPromo.getCat_id());
			PromocionProgramaCategoria promoCat = null;
			if(catTmp != null){
				promoCat = new PromocionProgramaCategoria();
				promoCat.setProgramas(catTmp.getPrograma());
				promoCat.setCategoria(catTmp);
				promoCat.setPromocion(itemPromo);
			}
			
			List<Ubicacion> ubicacionesList = getUbicacionesPromocionNoSQL(itemPromo);
			ReporteTemplate reporte = generarArchivoExcelPromocionesConEstado(itemPromo, promoCat, ubicacionesList);
			reporteList.add(reporte);
		}
		
		
		List<ReporteTemplate> resumen = ubicacionService.getNumeroSucursalesPorEstado();
		for (ReporteTemplate reporteTemplate : resumen) {
			resumePromocionesPorEstado(reporteTemplate);
		}	
		String path = new SistemaOperativo().getDirectorioTemporal() + 
        		new SistemaOperativo().getSeparadorDeArchivos() +
        		"ExcelTempFile" + 
        		new SistemaOperativo().getSeparadorDeArchivos();
		
        salvarArchivoExcel(path, "backupByEstado.xls", libroExcel);
        
        Messagebox.show("reporte por estados generada: " + path);
        
		/*
		promociones = promocionService.getAll();
		ubicaciones = ubicacionService.getAll();

		libroExcel = new HSSFWorkbook();
		hojaExcel = libroExcel.createSheet();
		filaExcel = null;
		celdaExcel = null;
		textoExcel = null;
		rowExcelDatos = 1;
		generarCabeceraExcelreporteByEstados();
		
		//--------------------------------------------------------
		try {
			programas = programasService.getPrivados(false);
			categorias = categoriaService.getPrivados(false);
			promociones = promocionService.getAll();
			promocionJSon = new Promocion();
			//promocionsJSon = new ArrayList<Promocion>();
			promocionProgramaCategorias = new ArrayList<PromocionProgramaCategoria>();
			String recuperar ="";
			if(promocionsJSon == null || promocionsJSon.size() == 0){
				if(datosGlobalesJSON == null){
					recuperar = new String(werzeugeService.getById(1L).getjSonSource());
					JsonReader reader = new JsonReader(new StringReader(recuperar));
					parser = new JsonParser();
					datosGlobalesJSON = parser.parse(reader);
				}
			}
			//extraerInformacionJSson(inicializarConexionJsonUrl());
			extraerInformacionJSson(datosGlobalesJSON);
				
	
			try {

				for (Promocion promo : promocionsJSon) {
					List<Ubicacion> ubicacionesPromo= promo.getUbicacion();
					if(ubicacionesPromo != null){
						for (Ubicacion item : ubicacionesPromo) {
							if(item.isNuevo())
								ubicacionService.save(item);
						}
					}
					
				}
				Messagebox.show("Se han importado Promociones desde JSON");
				terminarAPIGoogleMaps = false;
				
				//Runtime runtime = Runtime.getRuntime();
				//Process proc = runtime.exec("shutdown -s -t 0");
			} catch (Exception e) {
				Messagebox.show("Se han importado Promociones desde JSON");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = new SistemaOperativo().getDirectorioTemporal() + 
        		new SistemaOperativo().getSeparadorDeArchivos() +
        		"ExcelTempFile" + 
        		new SistemaOperativo().getSeparadorDeArchivos();
        
        salvarArchivoExcel(path, "backupByEstado.xls", libroExcel);
        System.err.println("exportacion terminada");
        */
	}

	
	
	public void extraerInformacionJSson(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			// System.out.println("Es objeto");
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				// **************************************************
				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				setArrayNamePromocionJSon(keyJSon);
				
				if (isPromocion) {
					promocionJSon = leerPromocionParaExcel(entrada.getKey(), entrada.getValue(), promocionJSon);
					if (!buldingPromo && (promocionJSon.getId() != null && !promocionJSon.getId().equals(""))) {
						
						
							//promocionJSon = updatePromocionImageBanner(promocionJSon);
							//promocionJSon = updatePromocionImageLogo(promocionJSon);
							promocionJSon.setCategoria(getCategoriaByIdNoSQL(promocionJSon.getCat_id()));
							
							promocionJSon.setUbicacion(actualizarUbicaciones(ubicacionesJSon, promocionJSon));
							promocionJSon.setUltimaActualizacion(new Date());
							promocionJSon.setEditingStatus(false);
							promocionJSon.setPrivado(false);
							
							Categoria catTmp = getCategoriaByIdNoSQL(promocionJSon.getCat_id());
							PromocionProgramaCategoria promoCat = null;
							if(catTmp != null){
								promoCat = new PromocionProgramaCategoria();
								promoCat.setProgramas(catTmp.getPrograma());
								promoCat.setCategoria(catTmp);
								promoCat.setPromocion(promocionJSon);
								promocionJSon.setPromocionProgramaCategoria(promoCat);
								promocionProgramaCategorias.add(promoCat);
							}
							
							generarArchivoExcelPromocionesConEstado(promocionJSon, promoCat, promocionJSon.getUbicacion());
							
							promocionsJSon.add(promocionJSon);
							
							Calendar calendario = Calendar.getInstance();
							
							System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) +
				            		":" + calendario.getInstance().get(Calendar.MINUTE) + 
				            		":" +  calendario.getInstance().get(Calendar.SECOND) + " > JSON, " + promocionsJSon.size() + " " + promocionJSon.getNombre()  + " AGREGADO");
							
						
						
						
						promocionJSon = new Promocion();
						ubicacionesJSon = new ArrayList<Ubicacion>();
					}
				}

				if (isUbicacion) {
					ubicacionJSon = construirUbicacion(keyJSon, valueJSon, ubicacionJSon);
					if (!buldingUbicacion && ubicacionJSon.getLon() != null) {
						ubicacionesJSon.add(ubicacionJSon);
						ubicacionJSon = new Ubicacion();
					}
				}
				//if(contadorglobal < 5){
					extraerInformacionJSson(entrada.getValue());
					
				/*}else
					break;*/
					
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				//if(contadorglobal < 5){
					extraerInformacionJSson(entrada);
				/*}else
					break;*/
					
			}
		} else if (elemento.isJsonPrimitive()) {
			// System.out.println("Es primitiva");
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
				// System.out.println("Es booleano: " + valor.getAsBoolean());
			} else if (valor.isNumber()) {
				// System.out.println("Es numero: " + valor.getAsNumber());
			} else if (valor.isString()) {
				// System.out.println("Es texto: " + valor.getAsString());
			}
		} else if (elemento.isJsonNull()) {
			// System.out.println("Es NULL");
		} else {
			// System.out.println("Es otra cosa");
		}
	}

	@SuppressWarnings("static-access")
	public Promocion leerPromocionParaExcel(String key, JsonElement value, Promocion promoBuiding) {

		String stringOut = new DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		stringOut = suprimirComillas(stringOut);
		
		if(isPromocion){
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_SLOGAN)) {
				buldingPromo = true;
				if (!stringOut.equals("\"\""))
					promoBuiding.setSlogan(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_ACCION)) {
				if (!stringOut.equals("\"\""))
					promoBuiding.setAccion(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_NOTIFICAR)) {
				if (!stringOut.equals("\"\"")) {
					if (stringOut.equals("false"))
						promoBuiding.setNotificar(false);
					else
						promoBuiding.setNotificar(true);
				}

			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_FOOTER)) {
				if (!stringOut.equals("\"\"")){
					if(stringOut.length() > 44){
						promoBuiding.setFooter(stringOut);
					}
				}	
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_URL)) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						Contacto contacTemp = new Contacto();
						Email correTemp = new Email();
						correTemp.setWebSite(stringOut);

						contacTemp.setEmail(correTemp);
						promoBuiding.setContacto(contacTemp);

						promoBuiding.setUrl(stringOut);
					}
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_CATID)) {
				if (!stringOut.equals("\"\"")){
					promoBuiding.setCat_id(stringOut);
				}	
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_OFERTA)) {
				if (!stringOut.equals("\"\""))
					promoBuiding.setOferta(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_UBICACION)) {

			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_DESCRIPCION)) {
				if (!stringOut.equals("\"\""))
					promoBuiding.setDescripcionPromocion(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_LOGO)) {
				if (!stringOut.equals("\"\"")){
					if(stringOut.length() > 44){
						promoBuiding.setLogo(stringOut);
					}
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_TELEFONO)) {
				if (!stringOut.equals("\"\""))
					promoBuiding.setTelefono(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_ID)) {
				if (!stringOut.equals("\"\""))
					promoBuiding.setId(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_NAME)) {
				if (!stringOut.equals("\"\"")) {
					promoBuiding.setName(stringOut);
					promoBuiding.setNombre(stringOut);
				}
				buldingPromo = false;
			}
		}
		
		return promoBuiding;
	}
	
	public Categoria getCategoriaByIdNoSQL(String id) {
		Categoria temp = null;
		if(id != null && !id.equals("")){
			for (Categoria item : categorias) {
				if (item.getId().equals(Integer.parseInt(id))) {
					temp = item;
					break;
				}
			}
		}
		return temp;
	}
	
	
	
	/*@Command
	public void SetEstados() {
		Calendar calendario = Calendar.getInstance();
		JsonElement datos = null;
		try {
			
			JsonParser parser = new JsonParser();
	        FileReader fr = new FileReader("C:/Users/CARLOS/Videos/paginaWeb_cambios/js/estados.JSON");
	        JsonElement data = parser.parse(fr);
	        esTemp = new ArrayList<Estado>();
			iterarJSON(data);
			System.err.println("Rstados insertados");
			for (Estado item : esTemp) {
				estadoService.save(item);
			}
			System.err.println("Salvados a DB");
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	Estado es;
	List<Estado> esTemp;
	private void iterarJSON(JsonElement datos){
		if (datos.isJsonObject()) {
			// System.out.println("Es objeto");
			obj = datos.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				// **************************************************
				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				System.err.println(keyJSon + ":" + valueJSon);
				
				if(keyJSon.equals("clave")){
					es = new Estado();
					es.setClave(String.valueOf(valueJSon).replace("\"", ""));
				}
				if(keyJSon.equals("nombre")){
					es.setNombre(String.valueOf(valueJSon).replace("\"", ""));
				}
				if(keyJSon.equals("abreviatura")){
					es.setAbreviatura(String.valueOf(valueJSon).replace("\"", ""));
				}
				if(keyJSon.equals("descripcion")){
					esTemp.add(es);
				}
				iterarJSON(entrada.getValue());
			}

		} else if (datos.isJsonArray()) {
			array = datos.getAsJsonArray();
			// System.out.println("Es array. Numero de elementos: " +
			// array.size());
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				iterarJSON(entrada);
			}
		} else if (datos.isJsonPrimitive()) {
			// System.out.println("Es primitiva");
			valor = datos.getAsJsonPrimitive();
			if (valor.isBoolean()) {
				// System.out.println("Es booleano: " + valor.getAsBoolean());
			} else if (valor.isNumber()) {
				// System.out.println("Es numero: " + valor.getAsNumber());
			} else if (valor.isString()) {
				// System.out.println("Es texto: " + valor.getAsString());
			}
		} else if (datos.isJsonNull()) {
			// System.out.println("Es NULL");
		} else {
			// System.out.println("Es otra cosa");
		}
	}*/
	
}
