/**
 * 
 */
package printworld.descuentosbanorte.mobile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.test.UtilsRestFul;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;

/**
 * @author Carlos Palalía López
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MobileVM extends MobileMetadatos {

	
	
	private JsonParser parser;
	private JsonElement datosGlobalesJSON;
	private JsonElement categoriasGlobalesJSON;

	private String keyJSon;
	private JsonElement valueJSon;

	private JsonObject obj;
	private JsonArray array;
	private JsonPrimitive valor;

	private boolean isPrograma;
	private boolean isCategoria;
	private boolean isPromocion;
	private boolean buldingPrograma;
	private boolean buldingCategoria;
	private boolean buldingPromo;
	private boolean isUbicacion;
	private boolean buldingUbicacion;

	private String arrayNameJSon;
	
	private DescuentosBanorteUtils utils;

	private long idProgramaGlobal;
	private Programas programaGlobal;

	@Init
	public void init() {
		visibleCategoria = true;
		utils = new DescuentosBanorteUtils();
		categoriaSeleccionada = new Categoria();
		conectarADataBase();
		idProgramaGlobal = getPrograma();
		if(programaGlobal != null){
			if(programaGlobal.getId() == 11 || programaGlobal.getId() == 100){
				visibleCategoria = false;
				visibleSubcategoria = true;
				visiblePromocion = false;
				visibleDetalle = false;
				visibleMenuCategoria = true;
				getSubCategoriasProrgama();
				
			}else{
				visibleCategoria = true;
				visibleSubcategoria = false;
				visiblePromocion = false;
				visibleDetalle = false;
				getCategoriasProrgama();
			}
		}
		
			

	}

	private void conectarADataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(urlConnection, user, pass);
			st = conexion.createStatement();
			rs = null;
		} catch (Exception e) {
			System.err.println("ERROR EN LA CONEXION:\n" + e);
		}
	}

	private long getPrograma() {
		long idPrograma = 0L;
		try {
			// ************* select ***********************
			String programaCookie = "";
			rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 5");
			while (rs.next()) {
				programaCookie = rs.getString("descripcion");
				if (programaCookie != null) {
					String[] output = rs.getString("nombre").split(",");
					longitudLocal = Float.parseFloat(output[0]);
					latitudLocal = Float.parseFloat(output[1]);
					break;
				}
			}
			if (!programaCookie.equals("")){
				idPrograma = Long.parseLong(programaCookie);
				programaGlobal = new Programas();
				programaGlobal.setId(Integer.parseInt(String.valueOf(idPrograma)));
				programaGlobal.setIdPrograma(idPrograma);
			}
				
			rs.close();
			// ************* select ***********************

		} catch (Exception e) {
			System.err.println(e);
		}
		return idPrograma;
	}
	
	private void getSubCategoriasProrgama() {
		try {

			// ************* select ***********************
			rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 3 ");
			while (rs.next()) {
				JsonReader reader = new JsonReader(new StringReader(new String(rs.getBytes("jSonSource"))));
				parser = new JsonParser();
				datosGlobalesJSON = parser.parse(reader);
			}
			rs.close();
			// ************* select ***********************
			
			
			
			categoriasJSon = new ArrayList<Categoria>();
			categoriasJSon.add(crearCategoriaTodasLasPromociones());
			categoriaJSONElement(datosGlobalesJSON);
			
			subCategoriaSelected = new Categoria();
			subCategorias = new ArrayList<Categoria>();
			
				
				for (Categoria categoria : categoriasJSon) {
					if(programaGlobal.getId() == 11){
						if(categoria.getNombreCategoria().equals("Novia Banorte"))
							subCategorias.add(categoria);
					}else{
						
					}
						
				}
				if(programaGlobal.getId() == 11){
					Categoria categoria = new Categoria();
					categoria.setNombreCategoria("Mamá Banorte ");
					categoria.setProgram_id(programaGlobal.getId());
					categoria.setPrograma(programaGlobal);
					categoria.setId(25);
					categoria.setLogo("/images/toolbar/mamaBanorte.png");
					
					subCategorias.add(categoria);
				}else{
					Programas progTemp = new Programas();
					progTemp.setId(3);
					progTemp.setIdPrograma(3L);
					
					Categoria categoria = new Categoria();
					categoria.setNombreCategoria("Hogar Banorte ");
					categoria.setProgram_id(3);
					categoria.setPrograma(progTemp);
					categoria.setId(300);
					categoria.setLogo("http://mibanorte.appspot.com/api/image/?key=agtzfm1pYmFub3J0ZXIkCxIGSW1hZ2VzIgZpbWFnZXMMCxIFSW1hZ2UYgICAkNPmhQoM");
					
					Programas progTemp2 = new Programas();
					progTemp2.setId(2);
					progTemp2.setIdPrograma(2L);
					
					Categoria categoria2 = new Categoria();
					categoria2.setNombreCategoria("Empresario Banorte ");
					categoria2.setProgram_id(2);
					categoria2.setPrograma(progTemp2);
					categoria2.setId(200);
					categoria2.setLogo("http://mibanorte.appspot.com/api/image/?key=agtzfm1pYmFub3J0ZXIkCxIGSW1hZ2VzIgZpbWFnZXMMCxIFSW1hZ2UYgICAkOOHigkM");
					
					subCategorias.add(categoria);
					subCategorias.add(categoria2);
					
					
				}
				categoriasJSon = new ArrayList<Categoria>();
			
				for (Categoria categoria : subCategorias) {
					System.err.println(categoria.getNombreCategoria());
				}
			System.err.println("Subcategorias cargadas.");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void getCategoriasProrgama() {
		
		
		try {

			// ************* select ***********************
			rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 3 ");
			while (rs.next()) {
				JsonReader reader = new JsonReader(new StringReader(new String(rs.getBytes("jSonSource"))));
				parser = new JsonParser();
				datosGlobalesJSON = parser.parse(reader);
			}
			rs.close();
			// ************* select ***********************
			
			
			
			categoriasJSon = new ArrayList<Categoria>();
			categoriasJSon.add(crearCategoriaTodasLasPromociones());
			categoriaJSONElement(datosGlobalesJSON);
			
			System.err.println("Categorias cargadas.");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private Categoria crearCategoriaTodasLasPromociones() {
		Categoria temp = new Categoria();
		temp.setNombreCategoria("Todas las promociones");
		temp.setId(10);
		temp.setProgram_id(Integer.parseInt(String.valueOf(idProgramaGlobal)));
		temp.setLogo("/images/toolbar/lupa.png");
		temp.setTodos(true);
		temp.setPrograma(programaGlobal);
		return temp;
	}
	
	@Command
	@NotifyChange({ "visibleCategoria", "visibleSubcategoria", "visiblePromocion", "visibleDetalle", "promocionsJSon" })
	public void buscarPromocionesCategoria() {
		visibleCategoria = false;
		visibleSubcategoria = false;
		visiblePromocion = true;
		visibleDetalle = false;
		
		try {

			// ************* select ***********************
			rs = st.executeQuery("SELECT * FROM werzeuge WHERE idWerzeuge = 4 ");
			while (rs.next()) {
				JsonReader reader = new JsonReader(new StringReader(new String(rs.getBytes("jSonSource"))));
				parser = new JsonParser();
				datosGlobalesJSON = parser.parse(reader);
			}
			rs.close();
			// ************* select ***********************
			promocionsJSon = new ArrayList<Promocion>();
			promocionSeleccionada = new Promocion();

			if(programaGlobal.getId() == 5)
				dumpPromocionJSONElementMSI(datosGlobalesJSON);
			else
				dumpPromocionJSONElement(datosGlobalesJSON);
			
			List<Promocion> remover = new ArrayList<Promocion>();
			List<Promocion> refactor = new ArrayList<Promocion>();
			
			for (Promocion item : promocionsJSon) {
				float dist = Float.parseFloat(item.getDistancia());
				int i = 0;
				for (Promocion search : promocionsJSon) {
					if(item.getNombre().equals(search.getNombre())
							&& dist == Float.parseFloat(search.getDistancia())){
						i++;
						if(i == 1)
							remover.add(item);	
					}
				}
				if(i > 1)
					item.setVisible(true);
				else
					remover = new ArrayList<Promocion>();
			}
			for (Promocion item : promocionsJSon) {
				if(!item.isVisible())
					refactor.add(item);
			}
			promocionsJSon = new ArrayList<Promocion>();
			promocionsJSon = refactor;
			Collections.sort(promocionsJSon);
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Command
	@NotifyChange({ "visibleCategoria", "visibleSubcategoria", "visiblePromocion", "visibleDetalle", "fileContent"})
	public void mostrarDetallePromocion() {
		visibleCategoria = false;
		visibleSubcategoria = false;
		visiblePromocion = false;
		visibleDetalle = true;
		
		
		updateCoordenadasPromo(promocionSeleccionada.getLat() + "," + promocionSeleccionada.getLon());
		

    	fileContent = "/modulos/mobile/mapaGlobal.html";
    	
    	Clients.evalJavaScript("recargarIFrame()");
        
     
		
	}
	
	
	public void updateCoordenadasPromo(String coordenadas) {
		try {
			String sql = "UPDATE werzeuge SET nombre = ?, ultimaActualizacion = ? WHERE idWerzeuge = 6";
			PreparedStatement myStmt = conexion.prepareStatement(sql);
			myStmt.setString(1, coordenadas);
			Timestamp updatedAt = new Timestamp(Calendar.getInstance().getTimeInMillis());
			myStmt.setTimestamp(2, updatedAt);
			myStmt.executeUpdate();
			rs.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Command
	@NotifyChange({ "visibleCategoria", "visibleSubcategoria", "visiblePromocion", "visibleDetalle", "categoriasJSon" })
	public void backCategoria() {
		visibleCategoria = true;
		visibleSubcategoria = false;
		visiblePromocion = false;
		visibleDetalle = false;
	}
	
	@Command
	@NotifyChange({ "*"})
	public void backSubCategoria() {
		visibleCategoria = false;
		visibleSubcategoria = true;
		visiblePromocion = false;
		visibleDetalle = false;
		subCategoriaSelected = new Categoria();
		categoriasJSon = new ArrayList<Categoria>();
		
		if(idProgramaGlobal == 0 || idProgramaGlobal == 1){
			idProgramaGlobal = 11;
			programaGlobal.setId(11);
			programaGlobal.setIdPrograma(11L);
		}else{
			idProgramaGlobal = 100;
			programaGlobal.setId(100);
			programaGlobal.setIdPrograma(100L);
		}
		
		getSubCategoriasProrgama();
		
	}
	
	@Command
	@NotifyChange({ "visibleCategoria", "visibleSubcategoria", "visiblePromocion", "visibleDetalle", "promocionsJSon" })
	public void backPromociones() {
		visibleCategoria = false;
		visibleSubcategoria = false;
		visiblePromocion = true;
		visibleDetalle = false;
	}
	
	public float distFrom(float lat1, float lng1, float lat2, float lng2) {
		double earthRadius = 6371; // kilometers
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
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
						/*
						 * if (!existeProgramaEnDB(programaJSon)) { programaJSon
						 * = updateProgramaImageBanner(programaJSon);
						 * programaJSon = updateProgramaImageLogo(programaJSon);
						 * programaJSon.setUltimaActualizacion(new Date());
						 * programaJSon.setPrivado(false);
						 * programaJSon.setEditingStatus(false);
						 * programaJSon.setEstatus(true);
						 * programaJSon.setVisible(true);
						 * programasJSon.add(programaJSon); }
						 */
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
	public Programas leerPrograma(String key, JsonElement value, Programas programaBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = utils.suprimirComillas(stringOut);

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

	public void setArrayBanderaNameJSon(String name) {
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

	
	List<Categoria> categoriasGlobal = new ArrayList<Categoria>();
	public void categoriaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();
				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				setArrayNameCategoriaJSon(keyJSon);
				categoriaJSon = leerCategoria(entrada.getKey(), entrada.getValue(), categoriaJSon);
				
				categoriasGlobal.add(categoriaJSon);
				if (!buldingCategoria && (categoriaJSon.getId() != null && !categoriaJSon.getId().equals(""))) {

					if (categoriaJSon.getProgram_id() != null)
						if (idProgramaGlobal == categoriaJSon.getProgram_id()) {
							//categoriaJSon = updateCategoriaImageLogo(categoriaJSon);
							if(categoriaJSon.getAccion() == 0)
								categoriasJSon.add(categoriaJSon);
						}

					categoriaJSon = new Categoria();
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
		if (categoriaBuiding == null)
			categoriaBuiding = new Categoria();
		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = utils.suprimirComillas(stringOut);

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
						Programas program = new Programas();
						program.setId(idInteger);
						program.setIdPrograma(Long.parseLong(String.valueOf(idInteger)));
						categoriaBuiding.setPrograma(program);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}
		if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_LOGO)) {
			if (!stringOut.equals("\"\"")) {
				stringOut = utils.suprimirComillas(stringOut);
				if (stringOut.length() > 44)
					categoriaBuiding.setLogo(stringOut);
			}
		}
		if (key.equals(DescuentosBanorteConstants.CATEGORIA_JASON_VARIABLE_ID)) {
			if (!stringOut.equals("\"\""))
				categoriaBuiding.setId(Integer.parseInt(utils.suprimirComillas(stringOut)));
		}

		if (key.equals(DescuentosBanorteConstants.PROGRAMA_CATEGORIA_JASON_VARIABLE_ACCION)) {
			if (!stringOut.equals("\"\"")) {
				buldingCategoria = false;
				categoriaBuiding.setAccion(Integer.parseInt(utils.suprimirComillas(stringOut)));
			}
		}

		return categoriaBuiding;
	}

	public void setArrayNameCategoriaJSon(String name) {
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

	// **************** GIN CATEGORIAS JSON **********************

	// *************** PROMOCIONES *********************************
	
	public void dumpPromocionJSONElementMSI(JsonElement elemento) {
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
				
				promocionJSon = leerPromocion(entrada.getKey(), entrada.getValue(), promocionJSon);
				if (!buldingPromo && (promocionJSon.getId() != null && !promocionJSon.getId().equals(""))) {
					
					if(categoriaSeleccionada.isTodos()){// Agregar todas las promociones de un programa (categoria todas las promociones)
						if(promocionJSon.getCategoria().getPrograma().getId() == programaGlobal.getId()){
							
							Promocion promoTemp = null;
						
							promoTemp = new Promocion();
							promoTemp.setId(promocionJSon.getId());
							promoTemp.setLogoAImage(promocionJSon.getLogoAImage());
							promoTemp.setLogo(promocionJSon.getLogo());
							promoTemp.setSlogan(promocionJSon.getSlogan());
							promoTemp.setOferta(promocionJSon.getOferta());
							promoTemp.setDescripcion(promocionJSon.getDescripcion());
							promoTemp.setTelefono(promocionJSon.getTelefono());
							promoTemp.setFooter(promocionJSon.getFooter());
							promoTemp.setNombre(promocionJSon.getNombre());
							promoTemp.setName(promocionJSon.getName());
							promoTemp.setCat_id(promocionJSon.getCat_id());
							promoTemp.setCategoria(promocionJSon.getCategoria());
							promocionsJSon.add(promoTemp);
							ubicacionesJSon = null;
						}
					} else if (promocionJSon.getCat_id().equals(categoriaSeleccionada.getId().toString())) {  //Agregar todas las promociones de una categoria seleccionada
						Promocion promoTemp = null;
						promoTemp = new Promocion();
						promoTemp.setId(promocionJSon.getId());
						promoTemp.setLogoAImage(promocionJSon.getLogoAImage());
						promoTemp.setLogo(promocionJSon.getLogo());
						promoTemp.setSlogan(promocionJSon.getSlogan());
						promoTemp.setOferta(promocionJSon.getOferta());
						promoTemp.setDescripcion(promocionJSon.getDescripcion());
						promoTemp.setTelefono(promocionJSon.getTelefono());
						promoTemp.setFooter(promocionJSon.getFooter());
						promoTemp.setNombre(promocionJSon.getNombre());
						promoTemp.setName(promocionJSon.getName());
						promoTemp.setCategoria(promocionJSon.getCategoria());
						promocionsJSon.add(promoTemp);
						ubicacionesJSon = null;
					}
					promocionJSon = new Promocion();
					ubicacionesJSon = new ArrayList<Ubicacion>();
				}

				if (isUbicacion) {

					if (ubicacionJSon == null)
						ubicacionJSon = new Ubicacion();
					ubicacionJSon = construirUbicacion(keyJSon, valueJSon, ubicacionJSon);
					if (!buldingUbicacion && ubicacionJSon.getLon() != null) {
						if (ubicacionesJSon == null)
							ubicacionesJSon = new ArrayList<Ubicacion>();
						
						
						float distancia = 
								distFrom(Float.parseFloat(String.valueOf(latitudLocal)),
										Float.parseFloat(String.valueOf(longitudLocal)),
										Float.parseFloat(String.valueOf(ubicacionJSon.getLat())),
										Float.parseFloat(String.valueOf(ubicacionJSon.getLon())));
						distancia = Float.parseFloat(String.format("%.2f", distancia));
						if(distancia < radioBusqueda){
							ubicacionJSon.setDescripcion(String.valueOf(distancia));
							ubicacionJSon.setUnidad("km.");
							ubicacionesJSon.add(ubicacionJSon);
						}
							
						ubicacionJSon = new Ubicacion();
					}
				}
				// ******************************************************

				// System.out.println("Clave: " + entrada.getKey());
				// System.out.println("Valor:");
				dumpPromocionJSONElementMSI(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpPromocionJSONElementMSI(entrada);
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
				
				promocionJSon = leerPromocion(entrada.getKey(), entrada.getValue(), promocionJSon);
				if (!buldingPromo && (promocionJSon.getId() != null && !promocionJSon.getId().equals(""))) {
					
					if(categoriaSeleccionada.isTodos()){// Agregar todas las promociones de un programa (categoria todas las promociones)
						if(promocionJSon.getCategoria().getPrograma().getId() == programaGlobal.getId()){
							promocionJSon.setUbicacion(actualizarUbicaciones(ubicacionesJSon, promocionJSon));
							Promocion promoTemp = null;
							
							if (ubicacionesJSon != null && ubicacionesJSon.size() > 0) {
								for (Ubicacion item : ubicacionesJSon) {
									promoTemp = new Promocion();
									promoTemp.setId(promocionJSon.getId());
									promoTemp.setLogoAImage(promocionJSon.getLogoAImage());
									promoTemp.setLogo(promocionJSon.getLogo());
									promoTemp.setSlogan(promocionJSon.getSlogan());
									promoTemp.setOferta(promocionJSon.getOferta());
									promoTemp.setDescripcion(promocionJSon.getDescripcion());
									promoTemp.setTelefono(promocionJSon.getTelefono());
									promoTemp.setFooter(promocionJSon.getFooter());
									promoTemp.setNombre(promocionJSon.getNombre());
									promoTemp.setName(promocionJSon.getName());
									promoTemp.setCat_id(promocionJSon.getCat_id());
									promoTemp.setLat(Float.parseFloat(String.valueOf(item.getLat())));
									promoTemp.setLon(Float.parseFloat(String.valueOf(item.getLon())));
									promoTemp.setDistancia(item.getDescripcion());
									promoTemp.setUnidad(item.getUnidad());
									//promoTemp.setCategoria(categoriaSeleccionada);
									promoTemp.setCategoria(promocionJSon.getCategoria());
									promocionsJSon.add(promoTemp);
								}
							}
							ubicacionesJSon = null;
						}
					} else if (promocionJSon.getCat_id().equals(categoriaSeleccionada.getId().toString())) {  //Agregar todas las promociones de una categoria seleccionada
						promocionJSon.setUbicacion(actualizarUbicaciones(ubicacionesJSon, promocionJSon));
						
						Promocion promoTemp = null;
						
						if (ubicacionesJSon != null && ubicacionesJSon.size() > 0) {
							for (Ubicacion item : ubicacionesJSon) {
								promoTemp = new Promocion();
								promoTemp.setId(promocionJSon.getId());
								promoTemp.setLogoAImage(promocionJSon.getLogoAImage());
								promoTemp.setLogo(promocionJSon.getLogo());
								promoTemp.setSlogan(promocionJSon.getSlogan());
								promoTemp.setOferta(promocionJSon.getOferta());
								promoTemp.setDescripcion(promocionJSon.getDescripcion());
								promoTemp.setTelefono(promocionJSon.getTelefono());
								promoTemp.setFooter(promocionJSon.getFooter());
								promoTemp.setNombre(promocionJSon.getNombre());
								promoTemp.setName(promocionJSon.getName());
								
								promoTemp.setLat(Float.parseFloat(String.valueOf(item.getLat())));
								promoTemp.setLon(Float.parseFloat(String.valueOf(item.getLon())));
								promoTemp.setDistancia(item.getDescripcion());
								promoTemp.setUnidad(item.getUnidad());
								//promoTemp.setCategoria(categoriaSeleccionada);
								promoTemp.setCategoria(promocionJSon.getCategoria());
								
								promocionsJSon.add(promoTemp);
							}
						}
						ubicacionesJSon = null;
					}

					promocionJSon = new Promocion();
					ubicacionesJSon = new ArrayList<Ubicacion>();
					// ubicacionesJSon = new ArrayList<Ubicacion>();
				}

				if (isUbicacion) {

					if (ubicacionJSon == null)
						ubicacionJSon = new Ubicacion();
					ubicacionJSon = construirUbicacion(keyJSon, valueJSon, ubicacionJSon);
					if (!buldingUbicacion && ubicacionJSon.getLon() != null) {
						if (ubicacionesJSon == null)
							ubicacionesJSon = new ArrayList<Ubicacion>();
						
						
						float distancia = 
								distFrom(Float.parseFloat(String.valueOf(latitudLocal)),
										Float.parseFloat(String.valueOf(longitudLocal)),
										Float.parseFloat(String.valueOf(ubicacionJSon.getLat())),
										Float.parseFloat(String.valueOf(ubicacionJSon.getLon())));
						distancia = Float.parseFloat(String.format("%.2f", distancia));
						if(distancia < radioBusqueda){
							ubicacionJSon.setDescripcion(String.valueOf(distancia));
							ubicacionJSon.setUnidad("km.");
							ubicacionesJSon.add(ubicacionJSon);
						}
							
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
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpPromocionJSONElement(entrada);
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
	public Promocion leerPromocion(String key, JsonElement value, Promocion promoBuiding) {
		if (promoBuiding == null)
			promoBuiding = new Promocion();
		String stringOut = String.valueOf(value);
		stringOut = utils.suprimirComillas(stringOut);

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
				Categoria cat = new Categoria();
				cat.setId(Integer.parseInt(stringOut));
				cat.setIdCategoria(Long.parseLong(stringOut));
				cat.setPrograma(getProgramaDeCategoria(stringOut));
				promoBuiding.setCategoria(cat);
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

		return promoBuiding;
	}

	private Programas getProgramaDeCategoria(String catId){
		Programas returnProgram = new Programas();
		for (Categoria categoria : categoriasGlobal) {
			if(categoria.getId() == Integer.parseInt(catId)){
				returnProgram = categoria.getPrograma();
				break;
			}
		}
		return returnProgram;
	}
	public Ubicacion construirUbicacion(String key, JsonElement value, Ubicacion ubicacion) {
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
			for (Ubicacion item : ubicacionItem) {
				item.setPromocion(promo);
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

	public void setArrayNamePromocionJSon(String name) {
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

	// *************** FIN PROMOCIONES *********************************
	
	
	
	
	@Command
	@NotifyChange({ "visibleCategoria", "visibleSubcategoria", "visiblePromocion", "visibleDetalle", "categoriasJSon", "titleSubCategoria" })
	public void buscarSubcategorias(){
		
		visibleCategoria = true;
		visibleSubcategoria = false;
		visiblePromocion = false;
		visibleDetalle = false;
		categoriaJSon = subCategoriaSelected;
		titleSubCategoria = subCategoriaSelected.getNombreCategoria();
		if(subCategoriaSelected.getId() == 133){//Novia banorte
			idProgramaGlobal = 1;
			programaGlobal.setId(1);
			programaGlobal.setIdPrograma(1L);
		}
		
		
		if(subCategoriaSelected.getId() == 25){// Mama banorte
			idProgramaGlobal = 0;
			programaGlobal.setId(0);
			programaGlobal.setIdPrograma(0L);
		}
		
		if(subCategoriaSelected.getId() == 300){// Hogar banorte
			idProgramaGlobal = 3;
			programaGlobal.setId(3);
			programaGlobal.setIdPrograma(3L);
		}
		
		if(subCategoriaSelected.getId() == 200){// Empresario banorte
			idProgramaGlobal = 2;
			programaGlobal.setId(2);
			programaGlobal.setIdPrograma(2L);
		}
			
		
		getCategoriasProrgama();
		
	}
}