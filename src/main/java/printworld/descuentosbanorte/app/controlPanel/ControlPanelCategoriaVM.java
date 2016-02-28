/**
 * 
 */
package printworld.descuentosbanorte.app.controlPanel;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
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
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Ubicacion;

/**
 * @author Carlos Palalía López
 * 
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControlPanelCategoriaVM extends ControlPanelMetadatos {

	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {

		categorias = categoriaService.getPrivados(false);
		categoriaProgramas = categoriaProgramaService.getAll();
		if (categoriaProgramas != null && categoriaProgramas.size() > 0)
			contadorCategoriaPrograma = String.valueOf(categoriaProgramas.size());
		else
			contadorCategoriaPrograma = "0";
		categoria = new Categoria();
		// programas = programasService.getPrivados(false);
		displayEdit = true;
		// programa = new Programas();

		parser = new JsonParser();
		promocionsJSon = new ArrayList<Promocion>();
		promocionJSon = new Promocion();
		ubicacionJSon = new Ubicacion();
		ubicacionesJSon = new ArrayList<Ubicacion>();
		categoriaJSon = new Categoria();
		categoriasJSon = new ArrayList<Categoria>();
	}

	@Command
	public void seleccionarPrograma() {

	}

	@Command
	@NotifyChange({ "categorias", "categoria", "bannerCategoria", "logoCategoria" })
	public void nuevoPrograma() {
		if (categorias == null)
			categorias = new ArrayList<Categoria>();

		categoria.setUltimaActualizacion(new Date());
		categoria.setEditingStatus(false);
		categoria.setPrivado(false);
		categoria.setVisible(true);

		if (logoCategoria != null)
			categoria.setLogoByte(logoCategoria.getByteData());
		if (bannerCategoria != null)
			categoria.setBannerByte(bannerCategoria.getByteData());
		categorias.add(categoria);
		categoriaService.save(categoria);

		categoria = new Categoria();
		bannerCategoria = null;
		logoCategoria = null;

	}

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "categorias", "categoria" })
	public void eliminarPrograma(@BindingParam("categoriaStatus") final Categoria categoriaEditada) {
		if (categoriaEditada != null) {
			Messagebox.show("¿Desea eliminar la Categoria " + categoriaEditada.getNombreCategoria() + "?",
					"Elimnar Categoria", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event e) {
							if (Messagebox.ON_OK.equals(e.getName())) {
								try {
									categoriaService.delete(categoriaEditada);
									categorias.remove(categoriaEditada);
									descuentosBanorteUtils
											.showSuccessmessage(
													"La categoria " + categoriaEditada.getNombreCategoria()
															+ " ha sido eliminada",
													Clients.NOTIFICATION_TYPE_INFO, 0, null);
								} catch (Exception e2) {
									descuentosBanorteUtils.showSuccessmessage(
											"La categoria " + categoriaEditada.getNombreCategoria()
													+ " se encuentra en uso. No puede ser eliminada",
											Clients.NOTIFICATION_TYPE_ERROR, 0, null);
								}

							} else if (Messagebox.ON_CANCEL.equals(e.getName())) {
								System.err.println("eliminacion cancelada");
							}
						}
					});
		} else {
			descuentosBanorteUtils.showSuccessmessage("Seleccione una categoria para llevar acabo la elminiacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}

	}

	@SuppressWarnings("static-access")
	@NotifyChange({ "categorias", "categoria", "bannerCategoria", "logoCategoria" })
	@Command
	public void confirm(@BindingParam("categoriaStatus") Categoria categoriaEditada) {
		categoriaEditada.setPrivado(false);
		categoriaEditada.setUltimaActualizacion(new Date());

		if (categoria.getLogoAImage() != null)
			categoriaEditada.setLogoByte(categoria.getLogoAImage().getByteData());
		if (categoria.getBannerAImage() != null)
			categoriaEditada.setBannerByte(categoria.getBannerAImage().getByteData());

		categoriaService.save(categoriaEditada);
		changeEditableStatus(categoriaEditada);
		refreshRowTemplate(categoriaEditada);
		descuentosBanorteUtils.showSuccessmessage(
				"Categoria -" + categoriaEditada.getNombreCategoria() + "- actualizada", Clients.NOTIFICATION_TYPE_INFO,
				0, null);
		categoriaEditada.setEditingStatus(false);
		categoriaService.save(categoriaEditada);

		categoria = new Categoria();
		bannerCategoria = null;
		logoCategoria = null;
	}

	@Command
	@NotifyChange({ "bannerCategoria", "logoCategoria" })
	public void changeEditableStatus(@BindingParam("categoriaStatus") Categoria categoriaEditada) {
		categoriaEditada.setEditingStatus(!categoriaEditada.getEditingStatus());
		refreshRowTemplate(categoriaEditada);
	}

	public void refreshRowTemplate(Categoria categoriaEditada) {
		resetVariablesEdicion(categoriaEditada);
		BindUtils.postNotifyChange(null, null, categoriaEditada, "editingStatus");
	}

	private void resetVariablesEdicion(Categoria categoriaEditada) {
		categoria = categoriaEditada;
		bannerCategoria = null;
		logoCategoria = null;
	}

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
					categoria.setBannerAImage((AImage) media);
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
					categoria.setLogoAImage((AImage) media);
				}
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange({ "promocionProgramaCategorias", "titlePromoAsignaWin2" })
	public void buscarPromociones() {
		buscarPromocionesMetaData();
		/*if (categoriaProgramaSelected != null) {
			promocionProgramaCategorias = promocionProgramaCategoriaService.getByCategoriaPrograma(
					categoriaProgramaSelected.getPrograma(), categoriaProgramaSelected.getCategoria());
			titlePromoAsignaWin2 = "Promociones de " + 
					categoriaProgramaSelected.getPrograma().getNombre() + 
					" : "+ categoriaProgramaSelected.getCategoria().getNombreCategoria();
			
			if(promocionProgramaCategorias != null)
				titlePromoAsignaWin2 += " (" + promocionProgramaCategorias.size() + ")";
		}*/
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void dumpJSONElement(JsonElement elemento) {/*
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
				
				if (String.valueOf(entrada.getKey()).equals("ubicacion"))
					isUbicacion = true;
				if (String.valueOf(entrada.getKey()).equals("descripcion")) {
					isUbicacion = false;
				}

				if (isPromocion) {
					promocionJSon = leerPromocion(entrada.getKey(), entrada.getValue(), promocionJSon);
					if (!buldingPromo && (promocionJSon.getId() != null && !promocionJSon.getId().equals(""))) {
						promocionJSon.setUbicacion(actualizarUbicaciones(ubicacionesJSon, promocionJSon));
						promocionJSon.setUltimaActualizacion(new Date());
						promocionJSon.setEditingStatus(false);
						promocionJSon.setPrivado(false);
						promocionsJSon.add(promocionJSon);
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
				// ******************************************************

				// System.out.println("Clave: " + entrada.getKey());
				// System.out.println("Valor:");
				dumpJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			// System.out.println("Es array. Numero de elementos: " +
			// array.size());
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpJSONElement(entrada);
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
		}*/
	}

	/*private void salvarJSonToDataBase() {
		int i = 0;
		if (promocionsJSon != null && promocionsJSon.size() > 0) {
			for (Promocion itemPromo : promocionsJSon) {
				System.err.println();

				if (itemPromo.getContacto() != null) {
					emailService.save(itemPromo.getContacto().getEmail());
					contactoService.save(itemPromo.getContacto());
				}
				promocionService.save(itemPromo);
				if (itemPromo.getUbicacion() != null && itemPromo.getUbicacion().size() > 0) {
					for (Ubicacion ubicacionItem : itemPromo.getUbicacion()) {
						ubicacionService.save(ubicacionItem);
					}
				}

			}
		}
	}*/

	

	public void ejecutarWs() {
		/*
		 * String uri =
		 * "http://localhost:8080/CustomerService/rest/customers/1"; URL url =
		 * new URL(uri); HttpURLConnection connection = (HttpURLConnection)
		 * url.openConnection(); connection.setRequestMethod("GET");
		 * connection.setRequestProperty("Accept", "application/xml");
		 * 
		 * JAXBContext jc = JAXBContext.newInstance(""); InputStream xml =
		 * connection.getInputStream(); Customer customer = (Customer)
		 * jc.createUnmarshaller().unmarshal(xml);
		 * 
		 * connection.disconnect();
		 */
	}

	

	@NotifyChange("categorias")
	@Command
	public void insertCategoriasFromJSON() {
		try {
			programas = programasService.getPrivados(false);
			categorias = categoriaService.getPrivados(false);
			categoriaJSon = new Categoria();
			categoriasJSon = new ArrayList<Categoria>();
			
			// **********************************************************************
			String recuperar = "";
			recuperar = new String(werzeugeService.getById(1L).getjSonSource());
			JsonReader reader = new JsonReader(new StringReader(recuperar));
			parser = new JsonParser();
			datosGlobalesJSON = parser.parse(reader);
			categoriaJSONElement(datosGlobalesJSON);
			// **********************************************************************
			
			//categoriaJSONElement(inicializarConexionJsonUrl());
			salvarCategoriasJSONToDB();
			
			if(categoriasJSon.size() > 0)
				categorias = categoriaService.getPrivados(false);
			Messagebox.show("Se han importado " + categoriasJSon.size() +" Categorias desde JSON");
			salvarProgramasJSONToDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Command
	@NotifyChange("*")
	public void salvarVisibilidadCategoria(@BindingParam("progSel)") Categoria catVar){
		if(catVar != null)
			categoriaService.save(catVar);
	}
	
}
