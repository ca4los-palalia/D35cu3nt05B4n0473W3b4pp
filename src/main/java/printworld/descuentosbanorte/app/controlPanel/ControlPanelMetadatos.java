/**
 * 
 */
package printworld.descuentosbanorte.app.controlPanel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.google.gson.JsonElement;

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
public abstract class ControlPanelMetadatos extends BasicStructure {

	private static final long serialVersionUID = 3686010678115196973L;

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "categorias" })
	public void onUploadExcel(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		String filePath = "C:\\printworld\\";
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();

			File baseDir = new File(filePath);
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			Files.copy(new File(filePath + media.getName()), media.getStreamData());
			Integer registros = leerDatosDesdeExcel(filePath + media.getName());

			if (registros > 0) {
				categorias = categoriaService.getPrivados(false);
				Messagebox.show("Se han importado " + registros + " Categorias exitosamente desde " + filePath
						+ media.getName());
			} else
				descuentosBanorteUtils.showSuccessmessage("No se encontraron categorias para importar",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@SuppressWarnings("rawtypes")
	public Integer leerDatosDesdeExcel(String fileName) {
		Integer registros = 0;
		List<Categoria> itemNuevosExcel = new ArrayList<Categoria>();
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(1);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			while (rowIterator.hasNext()) {
				Categoria categoria = new Categoria();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 25) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							categoria = crearCategoria(categoria, hssfCell, j);
							categoria.setUltimaActualizacion(new Date());
							categoria.setPrivado(false);
							categoria.setVisible(true);
							categoria.setEditingStatus(false);
						} else
							break;
						j++;
					}
					itemNuevosExcel.add(categoria);
				}
				i++;
			}
			File borrarArchivo = new File(fileName);
			if (borrarArchivo.delete()) {
				for (Categoria item : itemNuevosExcel) {
					categoriaService.save(item);
				}
			}
			registros = itemNuevosExcel.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	private Categoria crearCategoria(Categoria categoria, XSSFCell valorDePropiedad, int indice) {
		switch (indice) {
		case 0:
			categoria.setNombreCategoria(String.valueOf(valorDePropiedad));
			break;
		case 1:
			if (valorDePropiedad != null && !valorDePropiedad.equals("")
					&& !String.valueOf(valorDePropiedad).equals("*")) {
				try {
					String valor = String.valueOf(valorDePropiedad);
					List<String> imagenNombre = getImagenNombre(valor);
					categoria.setBannerAImage(leerImagenFromDiscoToAImage(imagenNombre.get(0), imagenNombre.get(1)));
					categoria.setBannerByte(categoria.getBannerAImage().getByteData());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			if (valorDePropiedad != null && !valorDePropiedad.equals("")
					&& !String.valueOf(valorDePropiedad).equals("*")) {
				try {
					String valor = String.valueOf(valorDePropiedad);
					List<String> imagenNombre = getImagenNombre(valor);
					categoria.setLogoAImage(leerImagenFromDiscoToAImage(imagenNombre.get(0), imagenNombre.get(1)));
					categoria.setLogoByte(categoria.getLogoAImage().getByteData());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 3:
			if (String.valueOf(valorDePropiedad) != null && !String.valueOf(valorDePropiedad).equals("")
					&& !String.valueOf(valorDePropiedad).equals("*"))
				categoria.setDescripcion(String.valueOf(valorDePropiedad));
			break;
		case 4:

			break;

		}
		return categoria;
	}

	// --------------------------------------------------

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "programas" })
	public void onUploadExcelPrograma(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		String filePath = "C:\\printworld\\";
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();

			File baseDir = new File(filePath);
			if (!baseDir.exists())
				baseDir.mkdirs();

			Files.copy(new File(filePath + media.getName()), media.getStreamData());
			Integer registros = leerDatosDesdeExcelPrograma(filePath + media.getName());

			if (registros > 0) {
				programas = programasService.getPrivados(false);
				Messagebox.show("Se han importado " + registros + " Programas exitosamente desde " + filePath
						+ media.getName());
			} else
				descuentosBanorteUtils.showSuccessmessage("No se encontraron programas para importar",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@SuppressWarnings("rawtypes")
	public Integer leerDatosDesdeExcelPrograma(String fileName) {
		Integer registros = 0;
		List<Programas> itemNuevosExcel = new ArrayList<Programas>();
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			while (rowIterator.hasNext()) {
				Programas programa = new Programas();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 25) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							programa = crearPrograma(programa, hssfCell, j);
							programa.setUltimaActualizacion(new Date());
							programa.setPrivado(false);
							programa.setVisible(true);
							programa.setEditingStatus(false);
						} else
							break;
						j++;
					}
					itemNuevosExcel.add(programa);
				}
				i++;
			}
			File borrarArchivo = new File(fileName);
			if (borrarArchivo.delete()) {
				for (Programas item : itemNuevosExcel) {
					programasService.save(item);
				}
			}
			registros = itemNuevosExcel.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	private Programas crearPrograma(Programas programa, XSSFCell valorDePropiedad, int indice) {
		switch (indice) {
		case 0:
			programa.setNombre(String.valueOf(valorDePropiedad));
			break;
		case 1:
			if (valorDePropiedad != null && !valorDePropiedad.equals("")) {
				try {
					String valor = String.valueOf(valorDePropiedad);
					List<String> imagenNombre = getImagenNombre(valor);
					programa.setBannerAImage(leerImagenFromDiscoToAImage(imagenNombre.get(0), imagenNombre.get(1)));
					programa.setBannerByte(programa.getBannerAImage().getByteData());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			if (valorDePropiedad != null && !valorDePropiedad.equals("")) {
				try {
					String valor = String.valueOf(valorDePropiedad);
					List<String> imagenNombre = getImagenNombre(valor);
					programa.setLogoAImage(leerImagenFromDiscoToAImage(imagenNombre.get(0), imagenNombre.get(1)));
					programa.setLogoByte(programa.getLogoAImage().getByteData());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case 3:
			programa.setDescripcion(String.valueOf(valorDePropiedad));
			break;
		case 4:

			break;
		}
		return programa;
	}

	// -----------------------------------

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "programas" })
	public void onUploadExcelCategoriasPrograma(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {
		String filePath = "C:\\printworld\\";
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();

			File baseDir = new File(filePath);
			if (!baseDir.exists())
				baseDir.mkdirs();

			Files.copy(new File(filePath + media.getName()), media.getStreamData());
			Integer registros = leerDatosDesdeExcelCategoriasPrograma(filePath + media.getName());

			if (registros > 0) {
				// programas = programasService.getPrivados(false);
				Messagebox.show("Se han importado " + registros + " categorias a distintos Programas desde " + filePath
						+ media.getName());
			} else
				descuentosBanorteUtils.showSuccessmessage("No se encontraron Categorias que asignar a programas",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@SuppressWarnings("rawtypes")
	private Integer leerDatosDesdeExcelCategoriasPrograma(String fileName) {
		Integer registros = 0;
		categoriaProgramas = null;
		List<CategoriaPrograma> itemNuevosExcel = new ArrayList<CategoriaPrograma>();
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(2);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			while (rowIterator.hasNext()) {
				CategoriaPrograma categoriaPrograma = new CategoriaPrograma();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 25) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							categoriaPrograma = crearCategoriaPrograma(categoriaPrograma, hssfCell, j);
							categoriaPrograma.setUltimaActualizacion(new Date());
							categoriaPrograma.setEditingStatus(false);
						} else
							break;
						j++;
					}
					itemNuevosExcel.add(categoriaPrograma);
				}
				i++;
			}
			File borrarArchivo = new File(fileName);
			if (borrarArchivo.delete()) {
				for (CategoriaPrograma item : itemNuevosExcel) {
					categoriaProgramaService.save(item);
				}
			}
			registros = itemNuevosExcel.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	private CategoriaPrograma crearCategoriaPrograma(CategoriaPrograma categoriaPrograma, XSSFCell valorDePropiedad,
			int indice) {
		String valor = "";
		switch (indice) {
		case 0:
			valor = String.valueOf(valorDePropiedad);
			if (valor != null && !valor.equals("")) {
				if (valor.contains(".0"))
					valor = removerPuntoCero(valor);
				// Programas prog = programasService.getById(new Long(valor));
				Programas prog = new Programas();
				prog.setIdPrograma(new Long(valor));
				categoriaPrograma.setPrograma(prog);
			}
			break;
		case 1:
			valor = String.valueOf(valorDePropiedad);
			if (valor != null && !valor.equals("")) {
				if (valor.contains(".0"))
					valor = removerPuntoCero(valor);
				// Categoria cat = categoriaService.getById(new Long(valor));
				Categoria cat = new Categoria();
				cat.setIdCategoria(new Long(valor));
				categoriaPrograma.setCategoria(cat);
			}
			break;
		}
		return categoriaPrograma;
	}

	// -----------------------------------

	private List<String> getImagenNombre(String valor) {
		List<String> archivo = new ArrayList<String>();
		String extension = "", nombreArchivo = "";
		boolean extraerExtension = false;
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, i + 1);

			if (extraerExtension)
				extension += caracter;
			else if (!caracter.equals("."))
				nombreArchivo += caracter;
			if (caracter.equals("."))
				extraerExtension = true;
		}
		archivo.add(nombreArchivo);
		archivo.add(extension);
		return archivo;
	}

	@SuppressWarnings("null")
	private AImage leerImagenFromDiscoToAImage(String nombreArchivo, String extension) throws IOException {
		AImage aImage = null;
		String directorio = "C:\\printworld\\";
		String path = directorio + nombreArchivo + "." + extension;
		ByteArrayOutputStream baos = null;
		if (nombreArchivo != null || !nombreArchivo.equals("")) {
			try {
				byte[] imageInByte;
				BufferedImage originalImage = ImageIO.read(new File(path));

				// convert BufferedImage to byte array
				baos = new ByteArrayOutputStream();
				ImageIO.write(originalImage, extension, baos);
				baos.flush();
				imageInByte = baos.toByteArray();
				aImage = new AImage("bannerByte", imageInByte);
				baos.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
				baos.close();
			}
		}
		return aImage;
	}

	private String removerPuntoCero(String valor) {
		String salida = "";
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, (i + 1));
			if (caracter.equals("."))
				break;
			else
				salida += caracter;
		}
		return salida;
	}
	
	
	// ----------------------------------------------
	
	public void buscarPromocionesMetaData() {
		if (categoriaProgramaSelected != null) {
			promocionProgramaCategorias = promocionProgramaCategoriaService.getByCategoriaPrograma(
					categoriaProgramaSelected.getPrograma(), categoriaProgramaSelected.getCategoria());
			
			if(categoriaProgramaSelected.getPrograma() != null){
				titlePromoAsignaWin2 = "Promociones de " + 
						categoriaProgramaSelected.getPrograma().getNombre() + 
						" : "+ categoriaProgramaSelected.getCategoria().getNombreCategoria();
			}else
				titlePromoAsignaWin2 = "Promociones";
			
			
			if(promocionProgramaCategorias != null)
				titlePromoAsignaWin2 += " (" + promocionProgramaCategorias.size() + ")";
		}
	}

	

	
}
