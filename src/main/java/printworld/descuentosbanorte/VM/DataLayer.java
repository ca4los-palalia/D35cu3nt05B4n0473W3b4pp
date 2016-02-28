/**
 * 
 */
package printworld.descuentosbanorte.VM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.image.AImage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Direccion;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Estado;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.PromocionProgramaCategoria;
import printworld.descuentosbanorte.domain.Telefono;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;

/**
 * @author Carlos Palalía López
 */

public class DataLayer implements Serializable {

	private static final long serialVersionUID = -828756372536148348L;
	
	protected List<Programas> programas;
	protected Programas programa;
	protected Programas programaLateral1;
	protected Programas programaLateral2;
	protected Programas programaLateral3;
	protected Programas programaLateral4;
	protected Programas programaLateral5;
	protected Programas programaLateral6;
	protected Programas programaLateral7;
	protected Programas programaLateral8;
	protected Programas programaLateral9;
	
	protected Programas programaBuscarCategorias;
	
	protected List<Categoria> categorias;
	protected List<Categoria> categoriasSelected;
	
	protected Categoria categoria;
	
	protected List<CategoriaPrograma> categoriaProgramas;
	protected CategoriaPrograma categoriaProgramaSelected;
 	
	
	protected List<Promocion> comercios;
	protected Promocion comercio;
	
	protected List<Estado> estados;
	protected Estado estado;
	
	protected Promocion promocion ;
	protected List<Promocion> promociones;
	
	protected List<PromocionProgramaCategoria> promocionProgramaCategorias; 
	protected PromocionProgramaCategoria promocionProgramaCategoria;
	
	protected Direccion direccion;
	protected Contacto contacto;
	protected Telefono telefono;
	protected Email email;
	protected Ubicacion ubicacion;
	protected List<Ubicacion> ubicaciones;
	
	protected AImage bannerProgramas;
	protected AImage bannerCategoria;
	protected AImage logoProgramas;
	protected AImage logoCategoria;
	protected Boolean displayEdit;
	
	protected String subTitleCatalogosPrograma;
	protected String contadorProgramas;
	protected String contadorPromociones;
	protected String contadorCategorias;
	protected String contadorCategroriasProgramas;
	protected String contadorCategoriaPrograma;
	protected String filtrarBusqueda;
	protected static String arrayNameJSon;
	
	protected static boolean isPrograma;
	protected static boolean isCategoria;
	protected static boolean isPromocion;
	protected static boolean buldingPrograma;
	protected static boolean buldingCategoria;
	protected static boolean buldingPromo;
	protected static boolean isUbicacion;
	protected static boolean buldingUbicacion;
	protected static String keyJSon;
	protected static JsonElement valueJSon;
	protected JsonParser parser;
	protected static JsonObject obj;
	protected static JsonArray array;
	protected static JsonPrimitive valor;
	
	protected static Programas programaJSon;
	protected static List<Programas> programasJSon;
	protected Promocion promocionJSon;
	protected static List<Promocion> promocionsJSon;
	protected static Categoria categoriaJSon;
	protected static List<Categoria> categoriasJSon;
	protected static Ubicacion ubicacionJSon;
	protected static List<Ubicacion> ubicacionesJSon;
	protected  Integer progressValue;
	protected DescuentosBanorteUtils descuentosBanorteUtils;
	
	protected boolean activarMapa;
	protected Integer numeroUbicaciones;
	protected String titlePromoAsignaWin1;
	protected String titlePromoAsignaWin2;
	
	protected String imagen1;
	protected String imagen2;
	protected String imagen3;
	protected String imagen4;
	protected String imagen5;
	protected String imagen6;
	protected String imagen7;
	protected String imagen8;
	protected boolean hidde1;
	protected boolean hidde2;
	protected boolean hidde3;
	
	protected URL urlGoogleApi;
	protected String keyApi = "AIzaSyBbuWLoYkuEBh5h-5MoaVuEGrSMQdSCD9I";
	//carlos.palalia: AIzaSyBbuWLoYkuEBh5h-5MoaVuEGrSMQdSCD9I
	//1nn3rgy: AIzaSyA2-hq3DDonVkkef7K_krkn9hXc5hp5CXI
	//developerWerzeug: AIzaSyAOdeAm5qNev03DR65GhwE-1MlGbqi5kZM
	//testAppPw: AIzaSyARmJtUGoyV0e4ip2ll5lAtP7FsL4lwbS0
	//mandrake.dev.fictivition: AIzaSyCXAFRgO9Q0NMKE3Eq6BwrEPXPvTPZ1Vxc
	//request.loop: AIzaSyCECuutvyo0-VGlwi_wdgjjNLBcLF8LGqA
	
	protected int rowExcelDatos;
	protected HSSFWorkbook libroExcel;
	protected HSSFSheet hojaExcel;
	protected HSSFRow filaExcel;
	protected HSSFCell celdaExcel;
	protected HSSFRichTextString textoExcel;

	protected FileWriter fichero;
	
	protected boolean terminarAPIGoogleMaps;
	protected boolean checkPrograma;
	protected boolean checkCategoria;
	protected boolean checkDescripcionPromocion;
	protected boolean checkOferta;
	protected boolean checkNombrePromocion;
	protected boolean checkSlogan;
	protected boolean checkTelefono;
	protected boolean checkURL;
	protected boolean checkNumeroSucursales;
	
	protected String urlConnection = "jdbc:mysql://localhost:3306/banorte";
	protected String user = "root";
	protected String pass = "1234";
	
	/*
	protected String urlConnection = "jdbc:mysql://127.11.189.2:3306/app";
	protected String user = "adminskU9EfJ";
	protected String pass = "f3Ex7_ml-Zv2";
	*/
	
	public List<Programas> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programas> programas) {
		this.programas = programas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Promocion> getComercios() {
		return comercios;
	}

	public void setComercios(List<Promocion> comercios) {
		this.comercios = comercios;
	}

	public Promocion getComercio() {
		return comercio;
	}

	public void setComercio(Promocion comercio) {
		this.comercio = comercio;
	}

	public Programas getPrograma() {
		return programa;
	}

	public void setPrograma(Programas programa) {
		this.programa = programa;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public AImage getBannerProgramas() {
		return bannerProgramas;
	}

	public void setBannerProgramas(AImage bannerProgramas) {
		this.bannerProgramas = bannerProgramas;
	}

	public Boolean getDisplayEdit() {
		return displayEdit;
	}

	public void setDisplayEdit(Boolean displayEdit) {
		this.displayEdit = displayEdit;
	}

	public AImage getLogoProgramas() {
		return logoProgramas;
	}

	public void setLogoProgramas(AImage logoProgramas) {
		this.logoProgramas = logoProgramas;
	}

	public AImage getBannerCategoria() {
		return bannerCategoria;
	}

	public void setBannerCategoria(AImage bannerCategoria) {
		this.bannerCategoria = bannerCategoria;
	}

	public AImage getLogoCategoria() {
		return logoCategoria;
	}

	public void setLogoCategoria(AImage logoCategoria) {
		this.logoCategoria = logoCategoria;
	}

	public List<Categoria> getCategoriasSelected() {
		return categoriasSelected;
	}

	public void setCategoriasSelected(List<Categoria> categoriasSelected) {
		this.categoriasSelected = categoriasSelected;
	}
	
	public Programas getProgramaBuscarCategorias() {
		return programaBuscarCategorias;
	}

	public void setProgramaBuscarCategorias(Programas programaBuscarCategorias) {
		this.programaBuscarCategorias = programaBuscarCategorias;
	}


	public String getSubTitleCatalogosPrograma() {
		return subTitleCatalogosPrograma;
	}

	public void setSubTitleCatalogosPrograma(String subTitleCatalogosPrograma) {
		this.subTitleCatalogosPrograma = subTitleCatalogosPrograma;
	}

	public List<CategoriaPrograma> getCategoriaProgramas() {
		return categoriaProgramas;
	}

	public void setCategoriaProgramas(List<CategoriaPrograma> categoriaProgramas) {
		this.categoriaProgramas = categoriaProgramas;
	}

	public CategoriaPrograma getCategoriaProgramaSelected() {
		return categoriaProgramaSelected;
	}

	public void setCategoriaProgramaSelected(CategoriaPrograma categoriaProgramaSelected) {
		this.categoriaProgramaSelected = categoriaProgramaSelected;
	}

	public String getContadorProgramas() {
		return contadorProgramas;
	}

	public void setContadorProgramas(String contadorProgramas) {
		this.contadorProgramas = contadorProgramas;
	}

	public String getContadorCategorias() {
		return contadorCategorias;
	}

	public void setContadorCategorias(String contadorCategorias) {
		this.contadorCategorias = contadorCategorias;
	}

	public String getContadorCategroriasProgramas() {
		return contadorCategroriasProgramas;
	}

	public void setContadorCategroriasProgramas(String contadorCategroriasProgramas) {
		this.contadorCategroriasProgramas = contadorCategroriasProgramas;
	}

	public String getContadorCategoriaPrograma() {
		return contadorCategoriaPrograma;
	}

	public void setContadorCategoriaPrograma(String contadorCategoriaPrograma) {
		this.contadorCategoriaPrograma = contadorCategoriaPrograma;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public List<PromocionProgramaCategoria> getPromocionProgramaCategorias() {
		return promocionProgramaCategorias;
	}

	public void setPromocionProgramaCategorias(List<PromocionProgramaCategoria> promocionProgramaCategorias) {
		this.promocionProgramaCategorias = promocionProgramaCategorias;
	}

	public PromocionProgramaCategoria getPromocionProgramaCategoria() {
		return promocionProgramaCategoria;
	}

	public void setPromocionProgramaCategoria(PromocionProgramaCategoria promocionProgramaCategoria) {
		this.promocionProgramaCategoria = promocionProgramaCategoria;
	}

	public String getFiltrarBusqueda() {
		return filtrarBusqueda;
	}

	public void setFiltrarBusqueda(String filtrarBusqueda) {
		this.filtrarBusqueda = filtrarBusqueda;
	}

	public static boolean isPromocion() {
		return isPromocion;
	}

	public static void setPromocion(boolean isPromocion) {
		DataLayer.isPromocion = isPromocion;
	}

	public static boolean isBuldingPromo() {
		return buldingPromo;
	}

	public static void setBuldingPromo(boolean buldingPromo) {
		DataLayer.buldingPromo = buldingPromo;
	}

	public static boolean isUbicacion() {
		return isUbicacion;
	}

	public static void setUbicacion(boolean isUbicacion) {
		DataLayer.isUbicacion = isUbicacion;
	}

	public static boolean isBuldingUbicacion() {
		return buldingUbicacion;
	}

	public static void setBuldingUbicacion(boolean buldingUbicacion) {
		DataLayer.buldingUbicacion = buldingUbicacion;
	}

	public static String getKeyJSon() {
		return keyJSon;
	}

	public static void setKeyJSon(String keyJSon) {
		DataLayer.keyJSon = keyJSon;
	}

	public static JsonElement getValueJSon() {
		return valueJSon;
	}

	public static void setValueJSon(JsonElement valueJSon) {
		DataLayer.valueJSon = valueJSon;
	}

	public JsonParser getParser() {
		return parser;
	}

	public void setParser(JsonParser parser) {
		this.parser = parser;
	}

	public static JsonObject getObj() {
		return obj;
	}

	public static void setObj(JsonObject obj) {
		DataLayer.obj = obj;
	}

	public static JsonArray getArray() {
		return array;
	}

	public static void setArray(JsonArray array) {
		DataLayer.array = array;
	}

	public static JsonPrimitive getValor() {
		return valor;
	}

	public static void setValor(JsonPrimitive valor) {
		DataLayer.valor = valor;
	}

	public Promocion getPromocionJSon() {
		return promocionJSon;
	}

	public void setPromocionJSon(Promocion promocionJSon) {
		this.promocionJSon = promocionJSon;
	}

	public static List<Promocion> getPromocionsJSon() {
		return promocionsJSon;
	}

	public static void setPromocionsJSon(List<Promocion> promocionsJSon) {
		DataLayer.promocionsJSon = promocionsJSon;
	}

	public static Ubicacion getUbicacionJSon() {
		return ubicacionJSon;
	}

	public static void setUbicacionJSon(Ubicacion ubicacionJSon) {
		DataLayer.ubicacionJSon = ubicacionJSon;
	}

	public static List<Ubicacion> getUbicacionesJSon() {
		return ubicacionesJSon;
	}

	public static void setUbicacionesJSon(List<Ubicacion> ubicacionesJSon) {
		DataLayer.ubicacionesJSon = ubicacionesJSon;
	}

	public Integer getProgressValue() {
		return progressValue;
	}

	public void setProgressValue(Integer progressValue) {
		this.progressValue = progressValue;
	}

	public DescuentosBanorteUtils getDescuentosBanorteUtils() {
		return descuentosBanorteUtils;
	}

	public void setDescuentosBanorteUtils(DescuentosBanorteUtils descuentosBanorteUtils) {
		this.descuentosBanorteUtils = descuentosBanorteUtils;
	}

	public static boolean isCategoria() {
		return isCategoria;
	}

	public static void setCategoria(boolean isCategoria) {
		DataLayer.isCategoria = isCategoria;
	}

	public static Categoria getCategoriaJSon() {
		return categoriaJSon;
	}

	public static void setCategoriaJSon(Categoria categoriaJSon) {
		DataLayer.categoriaJSon = categoriaJSon;
	}

	public static List<Categoria> getCategoriasJSon() {
		return categoriasJSon;
	}

	public static void setCategoriasJSon(List<Categoria> categoriasJSon) {
		DataLayer.categoriasJSon = categoriasJSon;
	}

	public static boolean isBuldingCategoria() {
		return buldingCategoria;
	}

	public static void setBuldingCategoria(boolean buldingCategoria) {
		DataLayer.buldingCategoria = buldingCategoria;
	}

	public static boolean isPrograma() {
		return isPrograma;
	}

	public static void setPrograma(boolean isPrograma) {
		DataLayer.isPrograma = isPrograma;
	}

	public static boolean isBuldingPrograma() {
		return buldingPrograma;
	}

	public static void setBuldingPrograma(boolean buldingPrograma) {
		DataLayer.buldingPrograma = buldingPrograma;
	}

	public static Programas getProgramaJSon() {
		return programaJSon;
	}

	public static void setProgramaJSon(Programas programaJSon) {
		DataLayer.programaJSon = programaJSon;
	}

	public static List<Programas> getProgramasJSon() {
		return programasJSon;
	}

	public static void setProgramasJSon(List<Programas> programasJSon) {
		DataLayer.programasJSon = programasJSon;
	}

	public static String getArrayNameJSon() {
		return arrayNameJSon;
	}

	public static void setArrayNameJSon(String arrayNameJSon) {
		DataLayer.arrayNameJSon = arrayNameJSon;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public boolean isActivarMapa() {
		return activarMapa;
	}

	public void setActivarMapa(boolean activarMapa) {
		this.activarMapa = activarMapa;
	}

	public Integer getNumeroUbicaciones() {
		return numeroUbicaciones;
	}

	public void setNumeroUbicaciones(Integer numeroUbicaciones) {
		this.numeroUbicaciones = numeroUbicaciones;
	}

	public String getTitlePromoAsignaWin2() {
		return titlePromoAsignaWin2;
	}

	public void setTitlePromoAsignaWin2(String titlePromoAsignaWin2) {
		this.titlePromoAsignaWin2 = titlePromoAsignaWin2;
	}

	public String getTitlePromoAsignaWin1() {
		return titlePromoAsignaWin1;
	}

	public void setTitlePromoAsignaWin1(String titlePromoAsignaWin1) {
		this.titlePromoAsignaWin1 = titlePromoAsignaWin1;
	}

	public String getContadorPromociones() {
		return contadorPromociones;
	}

	public void setContadorPromociones(String contadorPromociones) {
		this.contadorPromociones = contadorPromociones;
	}

	public String getImagen1() {
		return imagen1;
	}

	public void setImagen1(String imagen1) {
		this.imagen1 = imagen1;
	}

	public String getImagen2() {
		return imagen2;
	}

	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}

	public String getImagen3() {
		return imagen3;
	}

	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}

	public String getImagen4() {
		return imagen4;
	}

	public void setImagen4(String imagen4) {
		this.imagen4 = imagen4;
	}

	public String getImagen5() {
		return imagen5;
	}

	public void setImagen5(String imagen5) {
		this.imagen5 = imagen5;
	}

	public String getImagen6() {
		return imagen6;
	}

	public void setImagen6(String imagen6) {
		this.imagen6 = imagen6;
	}

	public String getImagen7() {
		return imagen7;
	}

	public void setImagen7(String imagen7) {
		this.imagen7 = imagen7;
	}

	public String getImagen8() {
		return imagen8;
	}

	public void setImagen8(String imagen8) {
		this.imagen8 = imagen8;
	}

	public boolean isHidde1() {
		return hidde1;
	}

	public void setHidde1(boolean hidde1) {
		this.hidde1 = hidde1;
	}

	public boolean isHidde2() {
		return hidde2;
	}

	public void setHidde2(boolean hidde2) {
		this.hidde2 = hidde2;
	}

	public boolean isHidde3() {
		return hidde3;
	}

	public void setHidde3(boolean hidde3) {
		this.hidde3 = hidde3;
	}

	public Programas getProgramaLateral1() {
		return programaLateral1;
	}

	public void setProgramaLateral1(Programas programaLateral1) {
		this.programaLateral1 = programaLateral1;
	}

	public Programas getProgramaLateral2() {
		return programaLateral2;
	}

	public void setProgramaLateral2(Programas programaLateral2) {
		this.programaLateral2 = programaLateral2;
	}

	public Programas getProgramaLateral3() {
		return programaLateral3;
	}

	public void setProgramaLateral3(Programas programaLateral3) {
		this.programaLateral3 = programaLateral3;
	}

	public Programas getProgramaLateral4() {
		return programaLateral4;
	}

	public void setProgramaLateral4(Programas programaLateral4) {
		this.programaLateral4 = programaLateral4;
	}

	public Programas getProgramaLateral5() {
		return programaLateral5;
	}

	public void setProgramaLateral5(Programas programaLateral5) {
		this.programaLateral5 = programaLateral5;
	}

	public Programas getProgramaLateral6() {
		return programaLateral6;
	}

	public void setProgramaLateral6(Programas programaLateral6) {
		this.programaLateral6 = programaLateral6;
	}

	public Programas getProgramaLateral7() {
		return programaLateral7;
	}

	public void setProgramaLateral7(Programas programaLateral7) {
		this.programaLateral7 = programaLateral7;
	}

	public Programas getProgramaLateral8() {
		return programaLateral8;
	}

	public void setProgramaLateral8(Programas programaLateral8) {
		this.programaLateral8 = programaLateral8;
	}

	public Programas getProgramaLateral9() {
		return programaLateral9;
	}

	public void setProgramaLateral9(Programas programaLateral9) {
		this.programaLateral9 = programaLateral9;
	}

	public URL getUrlGoogleApi() {
		return urlGoogleApi;
	}

	public void setUrlGoogleApi(URL urlGoogleApi) {
		this.urlGoogleApi = urlGoogleApi;
	}

	public int getRowExcelDatos() {
		return rowExcelDatos;
	}

	public void setRowExcelDatos(int rowExcelDatos) {
		this.rowExcelDatos = rowExcelDatos;
	}

	public HSSFWorkbook getLibroExcel() {
		return libroExcel;
	}

	public void setLibroExcel(HSSFWorkbook libroExcel) {
		this.libroExcel = libroExcel;
	}

	public HSSFSheet getHojaExcel() {
		return hojaExcel;
	}

	public void setHojaExcel(HSSFSheet hojaExcel) {
		this.hojaExcel = hojaExcel;
	}

	public HSSFRow getFilaExcel() {
		return filaExcel;
	}

	public void setFilaExcel(HSSFRow filaExcel) {
		this.filaExcel = filaExcel;
	}

	public HSSFCell getCeldaExcel() {
		return celdaExcel;
	}

	public void setCeldaExcel(HSSFCell celdaExcel) {
		this.celdaExcel = celdaExcel;
	}

	public HSSFRichTextString getTextoExcel() {
		return textoExcel;
	}

	public void setTextoExcel(HSSFRichTextString textoExcel) {
		this.textoExcel = textoExcel;
	}

	public FileWriter getFichero() {
		return fichero;
	}

	public void setFichero(FileWriter fichero) {
		this.fichero = fichero;
	}

	public boolean isCheckPrograma() {
		return checkPrograma;
	}

	public void setCheckPrograma(boolean checkPrograma) {
		this.checkPrograma = checkPrograma;
	}

	public boolean isCheckCategoria() {
		return checkCategoria;
	}

	public void setCheckCategoria(boolean checkCategoria) {
		this.checkCategoria = checkCategoria;
	}

	public boolean isCheckDescripcionPromocion() {
		return checkDescripcionPromocion;
	}

	public void setCheckDescripcionPromocion(boolean checkDescripcionPromocion) {
		this.checkDescripcionPromocion = checkDescripcionPromocion;
	}

	public boolean isCheckNombrePromocion() {
		return checkNombrePromocion;
	}

	public void setCheckNombrePromocion(boolean checkNombrePromocion) {
		this.checkNombrePromocion = checkNombrePromocion;
	}

	public boolean isCheckSlogan() {
		return checkSlogan;
	}

	public void setCheckSlogan(boolean checkSlogan) {
		this.checkSlogan = checkSlogan;
	}

	public boolean isCheckTelefono() {
		return checkTelefono;
	}

	public void setCheckTelefono(boolean checkTelefono) {
		this.checkTelefono = checkTelefono;
	}

	public boolean isCheckURL() {
		return checkURL;
	}

	public void setCheckURL(boolean checkURL) {
		this.checkURL = checkURL;
	}

	public boolean isCheckNumeroSucursales() {
		return checkNumeroSucursales;
	}

	public void setCheckNumeroSucursales(boolean checkNumeroSucursales) {
		this.checkNumeroSucursales = checkNumeroSucursales;
	}

	public boolean isCheckOferta() {
		return checkOferta;
	}

	public void setCheckOferta(boolean checkOferta) {
		this.checkOferta = checkOferta;
	}
	
	
	
}
