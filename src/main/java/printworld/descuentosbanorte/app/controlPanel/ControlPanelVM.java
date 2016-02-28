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
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Programas;

/**
 * @author Carlos Palalía López
 * 
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControlPanelVM extends ControlPanelMetadatos {

	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		programas = programasService.getPrivados(false);
		categorias = categoriaService.getPrivados(false);

		if (programas != null)
			contadorProgramas = "(" + programas.size() + ")";
		else
			contadorProgramas = "(0)";

		if (categorias != null)
			contadorCategorias = "(" + categorias.size() + ")";
		else
			contadorCategorias = "(0)";

		displayEdit = true;
		programa = new Programas();
	}

	@Command
	@NotifyChange({ "categoriaProgramas", "categorias", "titleCatalogosPrograma", "subTitleCatalogosPrograma",
			"contadorCategorias", "contadorCategroriasProgramas" })
	public void seleccionarPrograma() {
		categoriaProgramas = categoriaProgramaService.getByPrograma(programaBuscarCategorias);
		categorias = categoriaService.getCategoriasNoPertenecenAPrograma(categoriaProgramas);
		if (categoriaProgramas == null) {
			categoriaProgramas = new ArrayList<CategoriaPrograma>();
			categorias = categoriaService.getPrivados(false);

			if (categoriaProgramas != null)
				contadorCategroriasProgramas = "(" + categoriaProgramas.size() + ")";
			else
				contadorCategroriasProgramas = "(0)";

			if (categorias != null)
				contadorCategorias = "(" + categorias.size() + ")";
			else
				contadorCategorias = "(0)";
		}

		subTitleCatalogosPrograma = "No hay categorias asignadas al programa " + programaBuscarCategorias.getNombre();
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "categoriaProgramas", "categorias", "contadorCategorias", "contadorCategroriasProgramas" })
	public void seleccionarCategoria(@BindingParam("catalogoSel") Categoria cat) {
		if (programaBuscarCategorias != null) {
			CategoriaPrograma catProg = categoriaProgramaService.getProgramaCategoria(programaBuscarCategorias, cat);
			if (catProg == null) {
				catProg = new CategoriaPrograma();
				catProg.setCategoria(cat);
				catProg.setPrograma(programaBuscarCategorias);
				catProg.setUltimaActualizacion(new Date());
				categoriaProgramaService.save(catProg);
				categoriaProgramas.add(catProg);

				// categorias =
				// categoriaService.getCategoriasNoPertenecenAPrograma(categoriaProgramas);

				if (categorias != null || categorias.size() > 0)
					contadorCategorias = "(" + categorias.size() + ")";
				else
					contadorCategorias = "(0)";

				if (categoriaProgramas != null || categoriaProgramas.size() > 0)
					contadorCategroriasProgramas = "Categorias del programa " + programaBuscarCategorias.getNombre()
							+ " (" + categoriaProgramas.size() + ")";
				else
					contadorCategroriasProgramas = "Categorias del programa " + programaBuscarCategorias.getNombre()
							+ "(0)";

			} else {
				descuentosBanorteUtils
						.showSuccessmessage(
								"la categoria " + cat.getNombreCategoria() + " ya se encuentra en el programa "
										+ programaBuscarCategorias.getNombre(),
								Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			descuentosBanorteUtils.showSuccessmessage("debe seleccionar un programa para asignar categorias",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}

	}

	@Command
	@NotifyChange({ "categoriaProgramas", "categorias", "contadorCategorias", "contadorCategroriasProgramas" })
	public void desSeleccionarCategoria(@BindingParam("catalogoSel") CategoriaPrograma catProg) {
		categoriaProgramaService.delete(catProg);
		categoriaProgramas.remove(catProg);
		if (categoriaProgramas != null && categoriaProgramas.size() > 0)
			categorias = categoriaService.getCategoriasNoPertenecenAPrograma(categoriaProgramas);
		else
			categorias = categoriaService.getPrivados(false);

		if (categorias != null || categorias.size() > 0)
			contadorCategorias = "(" + categorias.size() + ")";
		else
			contadorCategorias = "(0)";

		if (categoriaProgramas != null || categoriaProgramas.size() > 0)
			contadorCategroriasProgramas = "(" + categoriaProgramas.size() + ")";
		else
			contadorCategroriasProgramas = "(0)";

	}

	@Command
	@NotifyChange("*")
	public void nuevoPrograma() {
		if (programas == null)
			programas = new ArrayList<Programas>();

		programa.setUltimaActualizacion(new Date());
		programa.setEditingStatus(false);
		programa.setPrivado(false);

		if (logoProgramas != null)
			programa.setLogoByte(logoProgramas.getByteData());
		if (bannerProgramas != null)
			programa.setBannerByte(bannerProgramas.getByteData());
		programas.add(programa);
		programasService.save(programa);

		programa = new Programas();
		bannerProgramas = null;
		logoProgramas = null;

	}

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Command
	@NotifyChange("*")
	public void eliminarPrograma(@BindingParam("programaStatus") final Programas programaEditado) {

		if (programaEditado != null) {

			Messagebox.show("¿Desea eliminar el programa " + programaEditado.getNombre() + "?", "Elimnar programa",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event e) {
							if (Messagebox.ON_OK.equals(e.getName())) {

								programasService.delete(programaEditado);
								programas = programasService.getPrivados(false);
								descuentosBanorteUtils.showSuccessmessage(
										"El Programa " + programaEditado.getNombre() + " ha sido eliminado",
										Clients.NOTIFICATION_TYPE_INFO, 0, null);
							} else if (Messagebox.ON_CANCEL.equals(e.getName())) {
								System.err.println("eliminacion cancelada");
							}
						}
					});
		} else {
			descuentosBanorteUtils.showSuccessmessage("Seleccione un programa para llevar acabo la elminiacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}

	}

	@SuppressWarnings("static-access")
	@NotifyChange({ "programas", "programa", "bannerProgramas", "logoProgramas" })
	@Command
	public void confirm(@BindingParam("programaStatus") Programas programaEditado) {
		programaEditado.setPrivado(false);
		programaEditado.setUltimaActualizacion(new Date());

		if (programa.getLogoAImage() != null)
			programaEditado.setLogoByte(programa.getLogoAImage().getByteData());
		if (programa.getBannerAImage() != null)
			programaEditado.setBannerByte(programa.getBannerAImage().getByteData());

		programasService.save(programaEditado);
		changeEditableStatus(programaEditado);
		refreshRowTemplate(programaEditado);
		descuentosBanorteUtils.showSuccessmessage("Programa -" + programaEditado.getNombre() + "- actualizado",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
		programaEditado.setEditingStatus(false);
		programasService.save(programaEditado);

		programa = new Programas();
		bannerProgramas = null;
		logoProgramas = null;
	}

	@Command
	@NotifyChange({ "bannerProgramas", "logoProgramas" })
	public void changeEditableStatus(@BindingParam("programaStatus") Programas programaEditado) {
		programaEditado.setEditingStatus(!programaEditado.getEditingStatus());
		refreshRowTemplate(programaEditado);
	}

	public void refreshRowTemplate(Programas programaEditado) {
		resetVariablesEdicion(programaEditado);
		BindUtils.postNotifyChange(null, null, programaEditado, "editingStatus");
	}

	private void resetVariablesEdicion(Programas programaEditado) {
		programa = programaEditado;
		bannerProgramas = null;
		logoProgramas = null;
	}

	@Command
	@NotifyChange("bannerProgramas")
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
				/*
				 * if (lengthofImage > 500 * 1024) { Messagebox.show(
				 * "Please Select a Image of size less than 500Kb."); return; }
				 * else {
				 */
				bannerProgramas = (AImage) media;
				programa.setBannerAImage((AImage) media);
				// }
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange("logoProgramas")
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
				logoProgramas = (AImage) media;
				programa.setLogoAImage((AImage) media);
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange("*")
	public void cargarProgramas() {
		programas = programasService.getPrivados(false);
		categorias = categoriaService.getPrivados(false);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("categorias")
	public void filtrarBusqueda(@ContextParam(ContextType.TRIGGER_EVENT) InputEvent event) {
		if (programaBuscarCategorias != null) {
			if (String.valueOf(event.getValue()).equals("*")) {
				categoriaProgramas = categoriaProgramaService.getByPrograma(programaBuscarCategorias);
				if (categoriaProgramas != null)
					categorias = categoriaService.getCategoriasNoPertenecenAPrograma(categoriaProgramas);
				else
					categorias = categoriaService.getPrivados(false);
			} else {
				categorias = categoriaService.getFiltering(String.valueOf(event.getValue()));
				if (categorias == null) {
					categorias = categoriaService.getPrivados(false);
					descuentosBanorteUtils.showSuccessmessage(
							"No se enceontraron coincidencias con " + String.valueOf(event.getValue()),
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				}
			}
		}

		else
			descuentosBanorteUtils.showSuccessmessage("debe seleccionar un programa para asignar categorias",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@NotifyChange("programas")
	@Command
	public void insertProgramasFromJSON() {
		try {
			programaJSon = new Programas();
			programasJSon = new ArrayList<Programas>();

			// dumpProgramaJSONElement(inicializarConexionJsonUrl());

			// **********************************************************************
			String recuperar = "";
			recuperar = new String(werzeugeService.getById(1L).getjSonSource());
			JsonReader reader = new JsonReader(new StringReader(recuperar));
			parser = new JsonParser();
			datosGlobalesJSON = parser.parse(reader);
			dumpProgramaJSONElement(datosGlobalesJSON);
			// **********************************************************************

			salvarProgramasJSONToDB();
			if (programasJSon.size() > 0)
				programas = programasService.getPrivados(false);
			Messagebox.show("Se han importado " + programasJSon.size() + " Programas desde JSON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void salvarVisibilidadProg(@BindingParam("progSel)") Programas progVar) {
		programasService.save(progVar);
	}

}
