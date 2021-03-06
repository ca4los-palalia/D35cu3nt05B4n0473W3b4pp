/**
 * 
 */
package printworld.descuentosbanorte.app.cliente;

import java.util.ArrayList;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

import printworld.descuentosbanorte.VM.BasicStructure;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;

/**
 * @author Carlos Palalía López
 */
public abstract class ClienteMetadatosVM extends BasicStructure {

	private static final long serialVersionUID = -7051200210193140194L;

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasPromocionesEspeciales() {
		realizarConsulta(6);
		iniciarImagenes(1);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasMesesSinIntereses() {
		realizarConsulta(5);
		iniciarImagenes(2);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasRestaurantes() {
		realizarConsulta(4);
		iniciarImagenes(3);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasViajes() {
		realizarConsulta(7);
		iniciarImagenes(4);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasSaludBienestar() {
		realizarConsulta(8);
		iniciarImagenes(5);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasTecnologia() {
		realizarConsulta(9);
		iniciarImagenes(6);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasEstiloVida() {
		realizarConsulta(10);
		iniciarImagenes(7);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasMujer() {
		realizarConsulta(11);
		iniciarImagenes(8);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	@NotifyChange({ "programa", "categorias", "contadorCategorias", "imagen1", "imagen2", "imagen3", "imagen4",
			"imagen5", "imagen6", "imagen7", "imagen8", "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria",
			"promocion" })
	@Command
	public void getCategoriasEspecialidades() {
		realizarConsulta(12);
		iniciarImagenes(9);
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocion = null;
		promocionProgramaCategoria = null;
	}

	public void realizarConsulta(Integer id) {
		programa = programasService.getByIdJSon(id);
		categorias = categoriaService.getByProgramaVisibles(programa);
		if (categorias != null)
			contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
					+ ")";
	}

	public void iniciarImagenes(Integer menu) {
		switch (menu) {
		case 1:
			imagen1 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen2 = "/images/menuVertical/restaurantes.png";
			imagen3 = "/images/menuVertical/viajes.jpg";
			imagen4 = "/images/menuVertical/salud.jpg";
			imagen5 = "/images/menuVertical/tecnologia.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";

			break;
		case 2:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/restaurantes.png";
			imagen3 = "/images/menuVertical/viajes.jpg";
			imagen4 = "/images/menuVertical/salud.jpg";
			imagen5 = "/images/menuVertical/tecnologia.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 3:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/viajes.jpg";
			imagen4 = "/images/menuVertical/salud.jpg";
			imagen5 = "/images/menuVertical/tecnologia.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 4:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/salud.jpg";
			imagen5 = "/images/menuVertical/tecnologia.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 5:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/viajes.jpg";
			imagen5 = "/images/menuVertical/tecnologia.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 6:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/viajes.jpg";
			imagen5 = "/images/menuVertical/salud.jpg";
			imagen6 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 7:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/viajes.jpg";
			imagen5 = "/images/menuVertical/salud.jpg";
			imagen6 = "/images/menuVertical/tecnologia.jpg";
			imagen7 = "/images/menuVertical/mujer.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 8:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/viajes.jpg";
			imagen5 = "/images/menuVertical/salud.jpg";
			imagen6 = "/images/menuVertical/tecnologia.jpg";
			imagen7 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen8 = "/images/menuVertical/especialidades.jpg";
			break;
		case 9:
			imagen1 = "/images/menuVertical/promociones_especiales.jpg";
			imagen2 = "/images/menuVertical/meses_sin_intereses.jpg";
			imagen3 = "/images/menuVertical/restaurantes.png";
			imagen4 = "/images/menuVertical/viajes.jpg";
			imagen5 = "/images/menuVertical/salud.jpg";
			imagen6 = "/images/menuVertical/tecnologia.jpg";
			imagen7 = "/images/menuVertical/estilo_de_vida.jpg";
			imagen8 = "/images/menuVertical/mujer.jpg";
			break;
		}

	}

	

	@NotifyChange({ "hidde1", "hidde2", "hidde3", "promocionProgramaCategorias", "categoria" })
	@Command
	public void seleccionaCategoria() {
		hidde1 = false;
		hidde2 = true;
		hidde3 = false;
		promocionProgramaCategorias = promocionProgramaCategoriaService.getByCategoriaPrograma(programa, categoria);
	}

	@NotifyChange({ "hidde1", "hidde2", "hidde3", "promocion" })
	@Command
	public void seleccionaPromocion() {
		hidde1 = false;
		hidde2 = false;
		hidde3 = true;

		promocion = promocionProgramaCategoria.getPromocion();
		/*
		 * promocion = updatePromocionImageBanner(promocion); promocion =
		 * updatePromocionImageLogo(promocion);
		 */

	}

	@NotifyChange({ "hidde1", "hidde2", "hidde3", "promocionProgramaCategorias" })
	@Command
	public void returnCategorias() {
		hidde1 = true;
		hidde2 = false;
		hidde3 = false;
		promocionProgramaCategorias = new ArrayList<PromocionProgramaCategoria>();
	}

	@NotifyChange({ "hidde1", "hidde2", "hidde3", "promocionProgramaCategoria", "promocion" })
	@Command
	public void returnPromociones() {
		hidde1 = false;
		hidde2 = true;
		hidde3 = false;
		/*
		 * promocion = null; promocionProgramaCategoria = null;
		 */
	}

	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral1() {
		if (imagen1.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen1.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral2() {
		if (imagen2.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen2.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral3() {
		if (imagen3.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen3.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral4() {
		if (imagen4.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen4.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral5() {
		if (imagen5.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen5.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral6() {
		if (imagen6.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen6.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral7() {
		if (imagen7.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen7.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
	
	@NotifyChange({ "*" })
	@Command
	public void categoriasProgramaLateral8() {
		if (imagen8.equals("/images/menuVertical/meses_sin_intereses.jpg")) {
			programa = programasService.getByIdJSon(5);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/promociones_especiales.jpg")) {
			programa = programasService.getByIdJSon(6);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/restaurantes.png")) {
			programa = programasService.getByIdJSon(4);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/viajes.jpg")) {
			programa = programasService.getByIdJSon(7);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/salud.jpg")) {
			programa = programasService.getByIdJSon(8);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/tecnologia.jpg")) {
			programa = programasService.getByIdJSon(9);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/estilo_de_vida.jpg")) {
			programa = programasService.getByIdJSon(10);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/mujer.jpg")) {
			programa = programasService.getByIdJSon(11);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		} else if (imagen8.equals("/images/menuVertical/especialidades.jpg")) {
			programa = programasService.getByIdJSon(12);
			categorias = categoriaService.getByProgramaVisibles(programa);
			if (categorias != null)
				contadorCategorias = "Categorias de " + programa.getNombre() + " (" + String.valueOf(categorias.size())
						+ ")";
		}
	}
}
