/**
 * 
 */
package printworld.descuentosbanorte.mobile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import printworld.descuentosbanorte.VM.BasicStructure;
import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.CategoriaPrograma;
import printworld.descuentosbanorte.domain.Contacto;
import printworld.descuentosbanorte.domain.Email;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.Ubicacion;
import printworld.descuentosbanorte.utils.DescuentosBanorteConstants;
import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;

/**
 * @author Carlos Palalía López
 */
/**
 * @author CARLOS
 *
 */
public abstract class MobileMetadatos {

	private static final long serialVersionUID = 3686010678115196973L;
	
	public String urlConnection = "jdbc:mysql://localhost:3306/banorte";
	public String user = "root";
	public String pass = "1234";
	
	/*
	public String urlConnection = "jdbc:mysql://127.11.189.2:3306/app";
	public String user = "adminskU9EfJ";
	public String pass = "f3Ex7_ml-Zv2";
	*/
	public Connection conexion;
	public Statement st;
	public ResultSet rs;

	public Programas programaJSon;
	public List<Programas> programasJSon;
	public Categoria categoriaJSon;
	public List<Categoria> categoriasJSon;
	public Promocion promocionJSon;
	public List<Promocion> promocionsJSon;
	public List<Ubicacion> ubicacionesJSon;
	public List<Ubicacion> ubicaciones;
	public Ubicacion ubicacionJSon;
	
	
	public Categoria categoriaSeleccionada; 
	public Promocion promocionSeleccionada;
	
	public boolean visibleCategoria;
	public boolean visibleSubcategoria;
	public boolean visiblePromocion;
	public boolean visibleDetalle;
	public boolean visibleMenuCategoria;
	
	public float latitudLocal;
	public float longitudLocal;
	public float radioBusqueda = 1500f;
	
	protected Categoria subCategoriaSelected;
	protected List<Categoria> subCategorias;
	
	protected String titleCategoria;
	protected String titleSubCategoria;
	protected String titlePromocion;

	protected String fileContent; 
	public Programas getProgramaJSon() {
		return programaJSon;
	}

	public void setProgramaJSon(Programas programaJSon) {
		this.programaJSon = programaJSon;
	}

	public List<Programas> getProgramasJSon() {
		return programasJSon;
	}

	public void setProgramasJSon(List<Programas> programasJSon) {
		this.programasJSon = programasJSon;
	}

	public Categoria getCategoriaJSon() {
		return categoriaJSon;
	}

	public void setCategoriaJSon(Categoria categoriaJSon) {
		this.categoriaJSon = categoriaJSon;
	}

	public List<Categoria> getCategoriasJSon() {
		return categoriasJSon;
	}

	public void setCategoriasJSon(List<Categoria> categoriasJSon) {
		this.categoriasJSon = categoriasJSon;
	}
	
	public Categoria getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public Promocion getPromocionSeleccionada() {
		return promocionSeleccionada;
	}

	public void setPromocionSeleccionada(Promocion promocionSeleccionada) {
		this.promocionSeleccionada = promocionSeleccionada;
	}

	public Promocion getPromocionJSon() {
		return promocionJSon;
	}

	public void setPromocionJSon(Promocion promocionJSon) {
		this.promocionJSon = promocionJSon;
	}

	public List<Promocion> getPromocionsJSon() {
		return promocionsJSon;
	}

	public void setPromocionsJSon(List<Promocion> promocionsJSon) {
		this.promocionsJSon = promocionsJSon;
	}

	public boolean isVisibleCategoria() {
		return visibleCategoria;
	}

	public void setVisibleCategoria(boolean visibleCategoria) {
		this.visibleCategoria = visibleCategoria;
	}

	public boolean isVisibleSubcategoria() {
		return visibleSubcategoria;
	}

	public void setVisibleSubcategoria(boolean visibleSubcategoria) {
		this.visibleSubcategoria = visibleSubcategoria;
	}

	public boolean isVisiblePromocion() {
		return visiblePromocion;
	}

	public void setVisiblePromocion(boolean visiblePromocion) {
		this.visiblePromocion = visiblePromocion;
	}

	public boolean isVisibleDetalle() {
		return visibleDetalle;
	}

	public void setVisibleDetalle(boolean visibleDetalle) {
		this.visibleDetalle = visibleDetalle;
	}

	public List<Ubicacion> getUbicacionesJSon() {
		return ubicacionesJSon;
	}

	public void setUbicacionesJSon(List<Ubicacion> ubicacionesJSon) {
		this.ubicacionesJSon = ubicacionesJSon;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public Ubicacion getUbicacionJSon() {
		return ubicacionJSon;
	}

	public void setUbicacionJSon(Ubicacion ubicacionJSon) {
		this.ubicacionJSon = ubicacionJSon;
	}

	public double getLatitudLocal() {
		return latitudLocal;
	}

	public float getLongitudLocal() {
		return longitudLocal;
	}

	public void setLongitudLocal(float longitudLocal) {
		this.longitudLocal = longitudLocal;
	}

	public void setLatitudLocal(float latitudLocal) {
		this.latitudLocal = latitudLocal;
	}

	public float getRadioBusqueda() {
		return radioBusqueda;
	}

	public void setRadioBusqueda(float radioBusqueda) {
		this.radioBusqueda = radioBusqueda;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<Categoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

	public Categoria getSubCategoriaSelected() {
		return subCategoriaSelected;
	}

	public void setSubCategoriaSelected(Categoria subCategoriaSelected) {
		this.subCategoriaSelected = subCategoriaSelected;
	}

	public String getTitleCategoria() {
		return titleCategoria;
	}

	public void setTitleCategoria(String titleCategoria) {
		this.titleCategoria = titleCategoria;
	}

	public String getTitleSubCategoria() {
		return titleSubCategoria;
	}

	public void setTitleSubCategoria(String titleSubCategoria) {
		this.titleSubCategoria = titleSubCategoria;
	}

	public String getTitlePromocion() {
		return titlePromocion;
	}

	public void setTitlePromocion(String titlePromocion) {
		this.titlePromocion = titlePromocion;
	}

	public boolean isVisibleMenuCategoria() {
		return visibleMenuCategoria;
	}

	public void setVisibleMenuCategoria(boolean visibleMenuCategoria) {
		this.visibleMenuCategoria = visibleMenuCategoria;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	
	
	
	
	
}
