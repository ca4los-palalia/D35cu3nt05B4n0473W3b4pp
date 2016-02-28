/**
 * 
 */
package printworld.descuentosbanorte.VM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.json.Cookie;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;
import printworld.descuentosbanorte.domain.ReporteTemplate;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;
import printworld.descuentosbanorte.utils.SistemaOperativo;

/**
 * @author Carlos Palalía López
 */
public abstract class BasicStructure extends ServiceLayer {

	private static final long serialVersionUID = 3686010678115196973L;

	@Init
	public void init() {
		/*
		 * areas = new ArrayList<Area>(); posiciones = new
		 * ArrayList<Posicion>(); requisicion = new Requisicion(); libro = new
		 * HSSFWorkbook(); cotizacionesList = new ArrayList<Cotizacion>();
		 * sistemaOperativo = new SistemaOperativo();
		 */

	}

	public void newRecord() {

	}

	public void deleteRecord() {

	}

	public void saveChanges() {

	}

	public void performSerch() {

	}

	public static String suprimirComillas(String input) {
		String salida = input;
		if (input.contains("\""))
			salida = input.substring(1, (input.length() - 1));
		return salida;
	}

	public void ejecutarJavaScript() {
		Clients.evalJavaScript("localizar()");

	}

	public String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}

	public void openPdf(String url) {
		try {
			if ((new File(url)).exists()) {

				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
				p.waitFor();

			} else {
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			closePdf(url);
		}
	}

	public boolean closePdf(String nombreAplicacion) {
		boolean eliminado = false;
		String osName = System.getProperty("os.name");
		String cmd = "";

		if (osName.toUpperCase().contains("WIN")) {// S.O. Windows
			cmd += "tskill " + nombreAplicacion;
		} else {// Solo ha sido probado en win y linux
			cmd += "killall " + nombreAplicacion;
		}
		Process hijo;
		try {
			hijo = Runtime.getRuntime().exec(cmd);
			hijo.waitFor();
			if (hijo.exitValue() == 0) {
				eliminado = true;
			}
		} catch (IOException e) {
			System.out.println("Incapaz de matar: Excepcion IOE");
		} catch (InterruptedException e) {
			System.out.println("Incapaz de matar: Excepcion InterruptedException");
		}
		return eliminado;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap construirHashMapParametros(List<HashMap> parametros) {
		HashMap parametrosGenerados = new HashMap();

		for (HashMap hashMap : parametros) {
			Iterator iterador = hashMap.keySet().iterator();
			while (iterador.hasNext()) {
				Object parametro = iterador.next();
				parametrosGenerados.put(parametro, hashMap.get(parametro));
			}
		}

		return parametrosGenerados;
	}

	public String obtenerVAlorDeCeldaDeExcel(Cell cell) {
		String valor = "";

		switch (cell.getCellType()) { // Identify CELL type
		case Cell.CELL_TYPE_NUMERIC:
			Integer entero = (int) cell.getNumericCellValue();
			valor = String.valueOf(entero);
			break;
		case Cell.CELL_TYPE_STRING:
			valor = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			valor = String.valueOf(cell.getCachedFormulaResultType());
			break;
		}
		return valor;
	}

	public void generarArbolDirectorios() {

	}

	// ************ PROGRAMAS FROM JSON ***************************
	public void dumpProgramaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				setArrayBanderaNameJSon(keyJSon);

				if (isPrograma) {

					programaJSon = leerPrograma(entrada.getKey(), entrada.getValue(), programaJSon);
					if (!buldingPrograma && (programaJSon.getId() != null && !programaJSon.getId().equals(""))) {

						if (!existeProgramaEnDB(programaJSon)) {
							programaJSon = updateProgramaImageBanner(programaJSon);
							programaJSon = updateProgramaImageLogo(programaJSon);
							programaJSon.setUltimaActualizacion(new Date());
							programaJSon.setPrivado(false);
							programaJSon.setEditingStatus(false);
							programaJSon.setEstatus(true);
							programaJSon.setVisible(true);
							programasJSon.add(programaJSon);
						}
						programaJSon = new Programas();
					}
				}
				dumpProgramaJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpProgramaJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	@SuppressWarnings("static-access")
	public static Programas leerPrograma(String key, JsonElement value, Programas programaBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);

		if (isPrograma) {
			if (key.equals(DescuentosBanorteConstants.PROGRAMA_CATEGORIA_JASON_VARIABLE_ACCION)) {
				if (!stringOut.equals("\"\"")) {
					buldingPrograma = true;
					programaBuiding.setAccion(Integer.parseInt(stringOut));
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROGRAMA_CATEGORIA_JASON_VARIABLE_TITLE)) {
				if (!stringOut.equals("\"\"")) {
					programaBuiding.setTitle(stringOut);
					programaBuiding.setNombre(stringOut);
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROGRAMA_JASON_VARIABLE_COVER)) {
				if (!stringOut.equals("\"\"")) {
					if (stringOut.length() > 44) {
						/*
						 * AImage aImage = new
						 * DescuentosBanorteUtils().convertirImagenJSonToAIMage(
						 * stringOut); programaBuiding.setBannerAImage(aImage);
						 * programaBuiding.setBannerByte(programaBuiding.
						 * getBannerAImage().getByteData());
						 */
						programaBuiding.setCover(stringOut);
					}
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROMOCION_JASON_VARIABLE_DESCRIPCION)) {
				if (!stringOut.equals("\"\""))
					programaBuiding.setDescripcion(stringOut);
			}
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_LOGO)) {
				if (!stringOut.equals("\"\"")) {
					if (stringOut.length() > 44) {
						/*
						 * AImage aImage = new
						 * DescuentosBanorteUtils().convertirImagenJSonToAIMage(
						 * stringOut); programaBuiding.setLogoAImage(aImage);
						 * programaBuiding.setLogoByte(programaBuiding.
						 * getLogoAImage().getByteData());
						 */
						programaBuiding.setLogo(stringOut);
					}
				}
			}
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_ID)) {
				if (!stringOut.equals("\"\"")) {
					buldingPrograma = false;
					try {
						programaBuiding.setId(Integer.parseInt(stringOut));
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}
		return programaBuiding;
	}

	private Programas updateProgramaImageBanner(Programas update) {
		AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getCover());
		update.setBannerAImage(aImage);
		update.setBannerByte(update.getBannerAImage().getByteData());
		return update;
	}

	private Programas updateProgramaImageLogo(Programas update) {
		AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getLogo());
		update.setLogoAImage(aImage);
		update.setLogoByte(update.getLogoAImage().getByteData());
		return update;
	}

	public boolean existeProgramaEnDB(Programas prog) {
		if (programasService.getByIdJSon(prog.getId()) != null)
			return true;
		else
			return false;
	}

	public void salvarProgramasJSONToDB() {

		Calendar calendario = Calendar.getInstance();
		System.err.println(
				calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + calendario.getInstance().get(Calendar.MINUTE)
						+ ":" + calendario.getInstance().get(Calendar.SECOND) + " > Salvando datos a DB");

		if (programasJSon != null && programasJSon.size() > 0) {
			for (Programas itemProgramas : programasJSon) {
				programasService.save(itemProgramas);
			}
		}

		System.err.println(
				calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + calendario.getInstance().get(Calendar.MINUTE)
						+ ":" + calendario.getInstance().get(Calendar.SECOND) + " > Importacion a DB terminada");
	}

	private Programas getProgramaByIdNoSQL(Integer id) {
		Programas temp = null;
		for (Programas item : programas) {
			if (item.getId().equals(id)) {
				temp = item;
				break;
			}
		}
		return temp;
	}

	public static void setArrayBanderaNameJSon(String name) {
		if (name.equals("programas")) {
			arrayNameJSon = "programas";
			isPrograma = true;
		} else if (name.equals("categorias")) {
			arrayNameJSon = "categorias";
			isPrograma = false;
		} else if (name.equals("misPromociones")) {
			arrayNameJSon = "misPromociones";
			isPrograma = false;
		} else if (name.equals("grupos")) {
			arrayNameJSon = "grupos";
			isPrograma = false;
		} else if (name.equals("anuncios")) {
			arrayNameJSon = "anuncios";
			isPrograma = false;
		} else if (name.equals("notificaciones")) {
			arrayNameJSon = "notificaciones";
			isPrograma = false;
		} else if (name.equals("promociones")) {
			arrayNameJSon = "promociones";
			isPrograma = false;
		}
	}

	// ****************** FIN PROGRAMAS ****************************

	// *************** CATEGORIAS *********************************

	public void categoriaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();
				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				setArrayNameCategoriaJSon(keyJSon);
				if (isCategoria) {
					categoriaJSon = leerCategoria(entrada.getKey(), entrada.getValue(), categoriaJSon);
					if (!buldingCategoria && (categoriaJSon.getId() != null && !categoriaJSon.getId().equals(""))) {
						if (!existeCategoriaEnDB(categoriaJSon)) {
							categoriaJSon = updateCategoriaImageBanner(categoriaJSon);
							categoriaJSon = updateCategoriaImageLogo(categoriaJSon);
							categoriaJSon.setUltimaActualizacion(new Date());
							categoriaJSon.setEditingStatus(false);
							categoriaJSon.setPrivado(false);
							categoriaJSon.setVisible(true);
							categoriasJSon.add(categoriaJSon);
							System.out.println("JSON ADDED " + categoriasJSon.size());
						}

						categoriaJSon = new Categoria();
					}
				}
				categoriaJSONElement(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				categoriaJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public Categoria leerCategoria(String key, JsonElement value, Categoria categoriaBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (isCategoria) {
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_TOP_AD)) {
				buldingCategoria = true;
				if (!stringOut.equals("\"\"")) {
					if (stringOut.length() > 44)
						categoriaBuiding.setTop_ad(stringOut);
				}
			}
			if (key.equals(DescuentosBanorteConstants.PROGRAMA_CATEGORIA_JASON_VARIABLE_TITLE)) {
				if (!stringOut.equals("\"\"")) {
					categoriaBuiding.setTitle(stringOut);
					categoriaBuiding.setNombreCategoria(stringOut);
				}
			}
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_PROGRAM_ID)) {
				if (!stringOut.equals("\"\"")) {
					if (stringOut != null) {
						try {
							int idInteger = Integer.parseInt(stringOut);

							categoriaBuiding.setProgram_id(idInteger);
							categoriaBuiding.setPrograma(getProgramaByIdNoSQL(idInteger));
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
				}
			}
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_LOGO)) {
				if (!stringOut.equals("\"\"")) {
					stringOut = suprimirComillas(stringOut);
					if (stringOut.length() > 44)
						categoriaBuiding.setLogo(stringOut);
				}
			}
			if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_ID)) {
				if (!stringOut.equals("\"\""))
					categoriaBuiding.setId(Integer.parseInt(suprimirComillas(stringOut)));
			}

			if (key.equals(DescuentosBanorteConstants.PROGRAMA_CATEGORIA_JASON_VARIABLE_ACCION)) {
				if (!stringOut.equals("\"\"")) {
					buldingCategoria = false;
					categoriaBuiding.setAccion(Integer.parseInt(suprimirComillas(stringOut)));
				}
			}
		}

		return categoriaBuiding;
	}

	public static void setArrayNameCategoriaJSon(String name) {
		if (name.equals("programas")) {
			arrayNameJSon = "programas";
			isCategoria = false;
		} else if (name.equals("categorias")) {
			arrayNameJSon = "categorias";
			isCategoria = true;
		} else if (name.equals("misPromociones")) {
			arrayNameJSon = "misPromociones";
			isCategoria = false;
		} else if (name.equals("grupos")) {
			arrayNameJSon = "grupos";
			isCategoria = false;
		} else if (name.equals("anuncios")) {
			arrayNameJSon = "anuncios";
			isCategoria = false;
		} else if (name.equals("notificaciones")) {
			arrayNameJSon = "notificaciones";
			isCategoria = false;
		} else if (name.equals("promociones")) {
			arrayNameJSon = "promociones";
			isCategoria = false;
		}
	}

	public boolean existeCategoriaEnDB(Categoria cat) {
		boolean centinela = false;
		if (categorias != null) {
			for (Categoria item : categorias) {
				if (item.getId().equals(cat.getId())) {
					centinela = true;
					break;
				}
			}
		}
		return centinela;
	}

	public void salvarCategoriasJSONToDB() {
		if (categoriasJSon != null && categoriasJSon.size() > 0) {
			CategoriaPrograma catProg = null;
			for (Categoria item : categoriasJSon) {
				categoriaService.save(item);

				catProg = new CategoriaPrograma();
				catProg.setUltimaActualizacion(new Date());
				catProg.setEditingStatus(false);
				catProg.setCategoria(item);
				catProg.setPrograma(item.getPrograma());
				categoriaProgramaService.save(catProg);
				System.err.println("SAVED");
			}
		}
	}

	private Categoria updateCategoriaImageBanner(Categoria update) {
		AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getTop_ad());
		update.setBannerAImage(aImage);
		update.setBannerByte(update.getBannerAImage().getByteData());
		return update;
	}

	private Categoria updateCategoriaImageLogo(Categoria update) {
		AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getLogo());
		update.setLogoAImage(aImage);
		update.setLogoByte(update.getLogoAImage().getByteData());
		return update;
	}

	// **************** GIN CATEGORIAS JSON **********************

	// *************** PROMOCIONES *********************************
	public void dumpPromocionJSONElement(JsonElement elemento) {
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
					promocionJSon = leerPromocion(entrada.getKey(), entrada.getValue(), promocionJSon);
					if (!buldingPromo && (promocionJSon.getId() != null && !promocionJSon.getId().equals(""))) {

						if (!existePromocionEnDB(promocionJSon)) {
							promocionJSon = updatePromocionImageBanner(promocionJSon);
							promocionJSon = updatePromocionImageLogo(promocionJSon);
							promocionJSon.setCategoria(getCategoriaByIdNoSQL(promocionJSon.getCat_id()));

							promocionJSon.setUbicacion(actualizarUbicaciones(ubicacionesJSon, promocionJSon));
							promocionJSon.setUltimaActualizacion(new Date());
							promocionJSon.setEditingStatus(false);
							promocionJSon.setPrivado(false);

							Categoria catTmp = getCategoriaByIdNoSQL(promocionJSon.getCat_id());
							if (catTmp != null) {
								PromocionProgramaCategoria promoCat = new PromocionProgramaCategoria();
								promoCat.setProgramas(catTmp.getPrograma());
								promoCat.setCategoria(catTmp);
								promoCat.setPromocion(promocionJSon);
								promocionJSon.setPromocionProgramaCategoria(promoCat);
								promocionProgramaCategorias.add(promoCat);

								// **********************************************
								try {
									fichero.write(
											promocionJSon.getName() + " | " + promocionJSon.getDescripcionPromocion()
													+ " | " + promocionJSon.getOferta() + "\n");
								} catch (Exception e) {
									e.printStackTrace();
								}
								// **********************************************
							}

							// promocionProgramaCategoriaService.save(promocionProgramaCategoria);

							promocionsJSon.add(promocionJSon);

							Calendar calendario = Calendar.getInstance();

							System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
									+ calendario.getInstance().get(Calendar.MINUTE) + ":"
									+ calendario.getInstance().get(Calendar.SECOND) + " > JSON, "
									+ promocionsJSon.size() + " " + promocionJSon.getNombre() + " AGREGADO");

						}

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
				dumpPromocionJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			// System.out.println("Es array. Numero de elementos: " +
			// array.size());
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpPromocionJSONElement(entrada);
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
	public Promocion leerPromocion(String key, JsonElement value, Promocion promoBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);

		if (isPromocion) {
			/*
			 * try { System.err.println(new
			 * String(String.valueOf(value).getBytes("UTF-16"))); } catch
			 * (UnsupportedEncodingException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

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
				if (!stringOut.equals("\"\"")) {
					if (stringOut.length() > 44) {
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
				if (!stringOut.equals("\"\"")) {
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
				if (!stringOut.equals("\"\"")) {
					if (stringOut.length() > 44) {
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

	public void salvarPRomocionJSonToDataBase() {
		int i = 0;
		if (promocionsJSon != null && promocionsJSon.size() > 0) {
			System.out.println("Saving Promociones...");
			for (Promocion itemPromo : promocionsJSon) {
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
			System.out.print(" Completed.");
		}

		if (promocionProgramaCategorias != null && promocionProgramaCategorias.size() > 0) {
			System.out.println("Saving PromocionProgramaCategorias...");
			for (PromocionProgramaCategoria item : promocionProgramaCategorias) {
				promocionProgramaCategoriaService.save(item);
			}
			System.out.print(" Completed.");
		}
	}

	public static Ubicacion construirUbicacion(String key, JsonElement value, Ubicacion ubicacion) {
		if (key.equals("lat")) {
			buldingUbicacion = true;
			ubicacion.setLat(Double.parseDouble(String.valueOf(value)));
		}
		if (key.equals("lon")) {
			ubicacion.setLon(Double.parseDouble(String.valueOf(value)));
			buldingUbicacion = false;
		}
		return ubicacion;
	}

	public List<Ubicacion> actualizarUbicaciones(List<Ubicacion> ubicacionItem, Promocion promo) {
		// if(!terminarAPIGoogleMaps){
		if (ubicacionItem != null) {
			if (ubicaciones != null)
				ubicacionItem = getUbicacionesPromocionNoSQL(promo);
			for (Ubicacion item : ubicacionItem) {
				item.setPromocion(promo);
				if (item.getEstado() == null) {
					Estado edoTemp = getAddressByGpsCoordinates(String.valueOf(item.getLon()),
							String.valueOf(item.getLat()));
					if (edoTemp != null) {
						item.setEstado(edoTemp);
						item.setNuevo(true);
						item.setDescripcion(edoTemp.getDescripcion());
					} else {
						System.err.println("NO HAY ESTADO");
						/*
						 * edoTemp = new Estado(); edoTemp.setNombre(
						 * "API Agotada"); item.setEstado(edoTemp);
						 */
					}
				}
			}
		}
		// }
		return ubicacionItem;
	}

	public List<Ubicacion> getUbicacionesPromocionNoSQL(Promocion promo) {
		List<Ubicacion> ubicacionesList = new ArrayList<Ubicacion>();
		for (Ubicacion item : ubicaciones) {
			if (item.getPromocion().getId().equals(promo.getId())) {
				ubicacionesList.add(item);
			}
		}
		return ubicacionesList;
	}

	public static void setArrayNamePromocionJSon(String name) {
		if (name.equals("programas")) {
			arrayNameJSon = "programas";
			isPromocion = false;
		} else if (name.equals("categorias")) {
			arrayNameJSon = "categorias";
			isPromocion = false;
		} else if (name.equals("misPromociones")) {
			arrayNameJSon = "misPromociones";
			isPromocion = false;
		} else if (name.equals("grupos")) {
			arrayNameJSon = "grupos";
			isPromocion = false;
		} else if (name.equals("anuncios")) {
			arrayNameJSon = "anuncios";
			isPromocion = false;
		} else if (name.equals("notificaciones")) {
			arrayNameJSon = "notificaciones";
			isPromocion = false;
		} else if (name.equals("promociones")) {
			arrayNameJSon = "promociones";
			isPromocion = true;
		}
		if (name.equals("ubicacion"))
			isUbicacion = true;
		if (name.equals("descripcion")) {
			isUbicacion = false;
		}
	}

	public boolean existePromocionEnDB(Promocion promo) {
		boolean centinela = false;
		if (promociones != null) {
			for (Promocion item : promociones) {
				if (item.getId().equals(promo.getId())) {
					centinela = true;
					break;
				}
			}
		}
		return centinela;
	}

	public Promocion updatePromocionImageBanner(Promocion update) {
		if (update != null) {
			if (update.getFooter() != null) {
				AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getFooter());
				update.setBannerAImage(aImage);
				if (update.getBannerAImage() != null)
					update.setBannerByte(update.getBannerAImage().getByteData());
			}
		}
		return update;
	}

	public Promocion updatePromocionImageLogo(Promocion update) {
		if (update != null) {
			if (update.getLogo() != null) {
				AImage aImage = new DescuentosBanorteUtils().convertirImagenJSonToAIMage(update.getLogo());
				update.setLogoAImage(aImage);
				if (update.getLogoAImage() != null)
					update.setLogoByte(update.getLogoAImage().getByteData());
			}
		}
		return update;
	}

	private Categoria getCategoriaByIdNoSQL(String id) {
		Categoria temp = null;
		if (id != null && !id.equals("")) {
			for (Categoria item : categorias) {
				if (item.getId().equals(Integer.parseInt(id))) {
					temp = item;
					break;
				}
			}
		}
		return temp;
	}

	public void actualizarEstadosDeCoordenadas() {
		estados = estadoService.getAll();
		ubicaciones = ubicacionService.getAll();
		promociones = promocionService.getAllSqlNative();
		terminarAPIGoogleMaps = false;
		for (Promocion itemPromocion : promociones) {
			List<Ubicacion> ubicacionItem = new ArrayList<Ubicacion>();
			if (!terminarAPIGoogleMaps) {
				ubicacionItem = actualizarUbicaciones(ubicacionItem, itemPromocion);
				for (Ubicacion item : ubicacionItem) {
					try {
						if (item.isNuevo())
							ubicacionService.save(item);
					} catch (Exception e) {
						System.err.println("Un error al salvar la ubicacion: " + e);
					}

				}
			} else
				break;

		}
		System.err.println("Actualizacion terminada");
	}
	// *************** FIN PROMOCIONES *********************************

	@SuppressWarnings("deprecation")
	public void generarArchivoExcelTodasLasPromociones() {
		HSSFWorkbook libro = new HSSFWorkbook();
		HSSFSheet hoja = libro.createSheet();
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;

		fila = hoja.createRow(0);

		celda = fila.createCell((short) 0);
		texto = new HSSFRichTextString("ID");
		celda.setCellValue(texto);

		int columHeader = 1;
		if (checkPrograma) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("PROGRAMA");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkCategoria) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("CATEGORIA");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkNombrePromocion) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("PROMOCION");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkDescripcionPromocion) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("DESCRIPCION DE PROMOCION");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkOferta) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("OFERTA");
			celda.setCellValue(texto);
			columHeader++;
		}

		if (checkSlogan) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("Slogan");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkTelefono) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("TELEFONO");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkURL) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("url");
			celda.setCellValue(texto);
			columHeader++;
		}
		if (checkNumeroSucursales) {
			celda = fila.createCell((short) columHeader);
			texto = new HSSFRichTextString("Numero de sucursales");
			celda.setCellValue(texto);
			ubicaciones = ubicacionService.getAll();
		}

		// **************************************************************

		// **************************************************************
		List<PromocionProgramaCategoria> list;
		int i = 1;
		int row = 1;

		List<Promocion> promoTemp = new ArrayList<Promocion>();

		promociones = promocionService.getAllSqlNative();
		for (Promocion itemPromocion : promociones) {
			/*
			 * boolean agregar = true; if(row == 1){ agregar = false; }else{
			 * agregar = verificarExisteEnReporteExcel(itemPromocion,
			 * promoTemp);
			 * 
			 * }
			 * 
			 * if(agregar){
			 */
			promoTemp.add(itemPromocion);
			fila = hoja.createRow(row);
			int columLoopHeader = 1;

			celda = fila.createCell((short) 0);
			celda.setCellValue(itemPromocion.getIdPromocion());

			if (checkPrograma) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getProgramaNombre());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkCategoria) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getCategoriaNombre());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkNombrePromocion) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getNombre());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkDescripcionPromocion) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getDescripcionPromocion());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkOferta) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getOferta());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkSlogan) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getSlogan());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkTelefono) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getTelefono());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkURL) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(itemPromocion.getUrl());
				celda.setCellValue(texto);
				columLoopHeader++;
			}
			if (checkNumeroSucursales) {
				celda = fila.createCell((short) columLoopHeader);
				texto = new HSSFRichTextString(
						String.valueOf(getNumeroSucursalesDePromocion(itemPromocion.getIdPromocion())));
				celda.setCellValue(texto);
			}

			row++;
			// }

		}
		String path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos()
				+ "ExcelTempFile" + new SistemaOperativo().getSeparadorDeArchivos();

		salvarArchivoExcel(path, "backup.xls", libro);
		System.err.println("Exportado");
	}

	private Integer getNumeroSucursalesDePromocion(Long idPromocion) {
		int count = 0;
		for (Ubicacion item : ubicaciones) {
			if (item.getPromocion().getIdPromocion().equals(idPromocion)) {
				count++;
			}
		}
		return count;
	}

	private boolean verificarExisteEnReporteExcel(Promocion search, List<Promocion> list) {
		boolean ok = false;
		/*
		 * for (Promocion item : list) { if() }
		 */
		return ok;
	}

	public boolean salvarArchivoExcel(String path, String fileName, HSSFWorkbook libro) {
		boolean centinela = false;
		try {

			File folder = new File(path);
			if (!folder.exists())
				folder.mkdirs();

			FileOutputStream elFichero = new FileOutputStream(path + fileName);

			libro.write(elFichero);
			elFichero.close();
			centinela = true;
		} catch (Exception e) {
			Messagebox.show("Error: " + e.getMessage());
			centinela = false;
		}
		return centinela;
	}

	public void generarCabeceraExcelreporteByEstados() {
		// **************************************************************
		filaExcel = hojaExcel.createRow(0);
		celdaExcel = filaExcel.createCell((short) 0);
		textoExcel = new HSSFRichTextString("PROGRAMA");
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 1);
		textoExcel = new HSSFRichTextString("CATEGORIA");
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 2);
		textoExcel = new HSSFRichTextString("PROMOCION");
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 3);
		textoExcel = new HSSFRichTextString("OFERTA");
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 4);
		textoExcel = new HSSFRichTextString("DESCRIPCION DE LA PROMOCION");
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 5);
		textoExcel = new HSSFRichTextString("ESTADO");
		celdaExcel.setCellValue(textoExcel);
		// **************************************************************
	}

	@SuppressWarnings("deprecation")
	public ReporteTemplate generarArchivoExcelPromocionesConEstado(Promocion promo, PromocionProgramaCategoria promoCat,
			List<Ubicacion> coordenadas) {
		ReporteTemplate reporte = new ReporteTemplate();
		for (Ubicacion item : coordenadas) {
			reporte.setPrograma(promoCat.getProgramas());
			reporte.setCategoria(promoCat.getCategoria());
			reporte.setPromocion(promo);
			reporte.setUbicacion(item);
			reporte.setEstado(item.getEstado());

			HSSFCellStyle style = libroExcel.createCellStyle();
			style.setFillForegroundColor(HSSFColor.LIME.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			filaExcel = hojaExcel.createRow(rowExcelDatos);

			celdaExcel = filaExcel.createCell((short) 0);
			textoExcel = new HSSFRichTextString(promoCat.getProgramas().getNombre());
			celdaExcel.setCellValue(textoExcel);
			celdaExcel.setCellStyle(style);

			celdaExcel = filaExcel.createCell((short) 1);
			textoExcel = new HSSFRichTextString(promoCat.getCategoria().getNombreCategoria());
			celdaExcel.setCellValue(textoExcel);

			celdaExcel = filaExcel.createCell((short) 2);
			textoExcel = new HSSFRichTextString(promoCat.getPromocion().getNombre());
			celdaExcel.setCellValue(textoExcel);

			celdaExcel = filaExcel.createCell((short) 3);
			textoExcel = new HSSFRichTextString(promoCat.getPromocion().getOferta());
			celdaExcel.setCellValue(textoExcel);

			celdaExcel = filaExcel.createCell((short) 4);
			textoExcel = new HSSFRichTextString(promoCat.getPromocion().getDescripcionPromocion());
			celdaExcel.setCellValue(textoExcel);

			celdaExcel = filaExcel.createCell((short) 5);
			if (item.getEstado() != null) {
				item.setEstado(getEstadoByIdNoSQL(item.getEstado().getIdEstado()));
				textoExcel = new HSSFRichTextString(item.getEstado().getNombre());
			}

			else
				textoExcel = new HSSFRichTextString("SIN UBICACION");
			celdaExcel.setCellValue(textoExcel);

			rowExcelDatos++;
		}
		return reporte;
	}

	@SuppressWarnings("deprecation")
	public void resumePromocionesPorEstado(ReporteTemplate reporte) {
		filaExcel = hojaExcel.createRow(rowExcelDatos);

		celdaExcel = filaExcel.createCell((short) 0);
		textoExcel = new HSSFRichTextString(reporte.getNombre());
		celdaExcel.setCellValue(textoExcel);

		celdaExcel = filaExcel.createCell((short) 1);
		celdaExcel.setCellValue(reporte.getTotal());
		rowExcelDatos++;
	}

	@SuppressWarnings("static-access")
	public JsonElement inicializarConexionJsonUrl() {
		Calendar calendario = Calendar.getInstance();
		boolean continuar = false;
		int count = 0;
		if (datosGlobalesJSON != null) {

		} else {
			do {
				try {
					String uri = "https://mibanorte.appspot.com/api/data/";
					URL url = new URL(uri);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
							+ calendario.getInstance().get(Calendar.MINUTE) + ":"
							+ calendario.getInstance().get(Calendar.SECOND) + " > Conectando a URL");
					JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
					System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
							+ calendario.getInstance().get(Calendar.MINUTE) + ":"
							+ calendario.getInstance().get(Calendar.SECOND) + " > Haciendo Parseo");
					parser = new JsonParser();
					datosGlobalesJSON = parser.parse(reader);
					System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
							+ calendario.getInstance().get(Calendar.MINUTE) + ":"
							+ calendario.getInstance().get(Calendar.SECOND) + " > Extrayendo informacion");
					continuar = true;
				} catch (Exception e) {
					count++;
					System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
							+ calendario.getInstance().get(Calendar.MINUTE) + ":"
							+ calendario.getInstance().get(Calendar.SECOND) + " > " + e.getMessage()
							+ ": Retrying connection " + count);
				}
			} while (!continuar);

		}
		// datosGlobalesJSON = null;

		return datosGlobalesJSON;
	}

	public Estado getAddressByGpsCoordinates(String lng, String lat) {
		String formattedAddress = "";
		Estado est = null;
		try {
			urlGoogleApi = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng
					+ "&sensor=true&key=" + keyApi);
			HttpURLConnection urlConnection = (HttpURLConnection) urlGoogleApi.openConnection();
			try {
				InputStream in = urlGoogleApi.openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String result, line = reader.readLine();
				result = line;
				String verificarResult = "";
				while ((line = reader.readLine()) != null) {
					result += line;
					verificarResult += line;
				}

				verificarResult = verificarResult.replace("[", "");
				verificarResult = verificarResult.replace("{", "");
				verificarResult = verificarResult.replace("\"", "");
				verificarResult = verificarResult.replace("[]", "");
				verificarResult = verificarResult.replace(":", "");

				if (!verificarResult.contains("error_message")) {
					if (!verificarResult.contains("ZERO_RESULTS")) {
						org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
						org.json.simple.JSONObject rsp = (org.json.simple.JSONObject) parser.parse(result);

						if (rsp.containsKey("results")) {
							org.json.simple.JSONArray matches = (org.json.simple.JSONArray) rsp.get("results");
							org.json.simple.JSONObject data = (org.json.simple.JSONObject) matches.get(0);

							formattedAddress = (String) data.get("formatted_address");
							est = buscarCoincidencias(formattedAddress);
							if (est != null)
								est.setDescripcion(new DescuentosBanorteUtils().convertFromUTF8(formattedAddress));

						}
					}
				} else
					terminarAPIGoogleMaps = true;
			} finally {
				urlConnection.disconnect();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return est;

	}

	@SuppressWarnings("static-access")
	public Estado buscarCoincidencias(String word) {
		System.err.print("ORIGINAL: " + word);
		Estado tempEstado = null;
		String[] informacionAddress = word.split(",");
		if (informacionAddress.length > 0) {

			for (int i = 0; i < informacionAddress.length; i++) {
				word = new DescuentosBanorteUtils().convertFromUTF8(informacionAddress[i]);
				if (word.indexOf("Ags") > -1 || word.indexOf("Ags.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("AGUASCALIENTES");
					break;
				} else if (word.indexOf("Baja California") > -1 || word.indexOf("B.C.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("BAJA CALIFORNIA");
					break;
				} else if (word.indexOf("Baja California Sur") > -1 || word.indexOf("B.C.S.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("BAJA CALIFORNIA SUR");
					break;
				} else if (word.indexOf("Camp") > -1 || word.indexOf("Camp.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("CAMPECHE");
					break;
				} else if (word.indexOf("Coahuila de Zaragoza") > -1 || word.indexOf("Coah.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("COAHUILA DE ZARAGOZA");
					break;
				} else if (word.indexOf("Col.") > -1 || word.indexOf("Colima") > -1) {
					tempEstado = getEstadoByNombreNoSQL("COLIMA");
					break;
				} else if (word.indexOf("Chiapas") > -1 || word.indexOf("Chis.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("CHIAPAS");
					break;
				} else if (word.indexOf("Chih.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("CHIHUAHUA");
					break;
				} else if (word.indexOf("D.F.") > -1 || word.indexOf("Federal District") > -1
						|| word.indexOf("Mexico") > -1) {
					tempEstado = getEstadoByNombreNoSQL("DISTRITO FEDERAL");
					break;
				} else if (word.indexOf("Dgo.") > -1 || word.indexOf("Durango") > -1) {
					tempEstado = getEstadoByNombreNoSQL("DURANGO");
					break;
				} else if (word.indexOf("Guanajuato") > -1 || word.indexOf("Gto.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("GUANAJUATO");
					break;
				} else if (word.indexOf("Guerrero") > -1 || word.indexOf("Gro.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("GUERRERO");
					break;
				} else if (word.indexOf("Hgo.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("HIDALGO");
					break;
				} else if (word.indexOf("Jalisco") > -1 || word.indexOf("Jal.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("JALISCO");
					break;
				} else if (word.indexOf("Estado de México") > -1 || word.indexOf("Méx.") > -1
						|| word.indexOf("State of Mexico") > -1) {
					tempEstado = getEstadoByNombreNoSQL("MEXICO");
					break;
				} else if (word.indexOf("Michoacán") > -1 || word.indexOf("Mich.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("MICHOACAN DE OCAMPO");
					break;
				} else if (word.indexOf("Morelos") > -1 || word.indexOf("Mor.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("MORELOS");
					break;
				} else if (word.indexOf("Nayarit") > -1 || word.indexOf("Nay.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("NAYARIT");
					break;
				} else if (word.indexOf("N.L.") > -1 || word.indexOf("San Nicolás de los Garza") > -1) {
					tempEstado = getEstadoByNombreNoSQL("NUEVO LEON");
					break;
				} else if (word.indexOf("Oax.") > -1 || word.indexOf("Oaxaca") > -1) {
					tempEstado = getEstadoByNombreNoSQL("OAXACA");
					break;
				} else if (word.indexOf("Pue.") > -1 || word.indexOf("Puebla") > -1) {
					tempEstado = getEstadoByNombreNoSQL("PUEBLA");
					break;
				} else if (word.indexOf("Qro.") > -1 || word.indexOf("Querétaro") > -1) {
					tempEstado = getEstadoByNombreNoSQL("QUERETARO");
					break;
				} else if (word.indexOf("Quintana Roo") > -1 || word.indexOf("Q.R.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("QUINTANA ROO");
					break;
				} else if (word.indexOf("S.L.P.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("SAN LUIS POTOSI");
					break;
				} else if (word.indexOf("Sin.") > -1 || word.indexOf("Sinaloa") > -1) {
					tempEstado = getEstadoByNombreNoSQL("SINALOA");
					break;
				} else if (word.indexOf("Son.") > -1 || word.indexOf("Sonora") > -1) {
					tempEstado = getEstadoByNombreNoSQL("SONORA");
					break;
				} else if (word.indexOf("Tabasco") > -1 || word.indexOf("Tab.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("TABASCO");
					break;
				} else if (word.indexOf("Tamaulipas") > -1 || word.indexOf("Tamps.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("TAMAULIPAS");
					break;
				} else if (word.indexOf("Tlax.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("TLAXCALA");
					break;
				} else if (word.indexOf("Veracruz") > -1 || word.indexOf("Ver.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("VERACRUZ DE IGNACIO DE LA LLAVE");
					break;
				} else if (word.indexOf("Yucatán") > -1 || word.indexOf("Yuc.") > -1) {
					tempEstado = getEstadoByNombreNoSQL("YUCATAN");
					break;
				} else if (word.indexOf("Zac.") > -1 || word.indexOf("Zacatecas") > -1) {
					tempEstado = getEstadoByNombreNoSQL("ZACATECAS");
					break;
				} else if (word.indexOf("Madrid") > -1) {
					tempEstado = getEstadoByNombreNoSQL("Madrid");
					break;
				} else if (word.indexOf("Argentina") > -1) {
					tempEstado = getEstadoByNombreNoSQL("Argentina");
					break;
				} else if (word.indexOf("USA") > -1) {
					tempEstado = getEstadoByNombreNoSQL("USA");
					break;
				} else if (word.indexOf("Sevilla") > -1) {
					tempEstado = getEstadoByNombreNoSQL("Sevilla");
					break;
				}

			}
			// System.err.println(" SUBSTRING: " + word);

		} else {
			// System.err.println();
		}
		return tempEstado;
	}

	private Estado getEstadoByNombreNoSQL(String nombre) {
		Estado edo = null;
		for (Estado item : estados) {
			if (item.getNombre().equals(nombre)) {
				edo = item;
				break;
			}
		}
		return edo;
	}

	private Estado getEstadoByIdNoSQL(Long id) {
		Estado edo = null;
		for (Estado item : estados) {
			if (item.getIdEstado() == id) {
				edo = item;
				break;
			}
		}
		return edo;
	}
}
