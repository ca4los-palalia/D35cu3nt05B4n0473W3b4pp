/**
 * 
 */
package printworld.descuentosbanorte.utils;

import java.util.ArrayList;
import java.util.List;

import printworld.descuentosbanorte.domain.Categoria;
import printworld.descuentosbanorte.domain.Programas;
import printworld.descuentosbanorte.domain.Promocion;
import printworld.descuentosbanorte.domain.ReporteTemplate;

/**
 * @author Carlos Palalía López
 */
public class BuildingTables{

	public List<Categoria> construirCategorias(List<Object[]> listDatos){
		DescuentosBanorteUtils utilidades = new DescuentosBanorteUtils();
		List<Categoria> lista = new ArrayList<Categoria>();
		for (Object[] datos : listDatos) {
			Categoria item = new Categoria();
			if(!String.valueOf(datos[0]).equals("null"))
				item.setIdCategoria(Long.parseLong(String.valueOf(datos[0])));
			if(!String.valueOf(datos[1]).equals("null"))
				item.setAccion(Integer.parseInt(String.valueOf(datos[1])));
			if(!String.valueOf(datos[2]).equals("null"))
				item.setBanner(String.valueOf(datos[2]));
			if(!String.valueOf(datos[3]).equals("null"))
				item.setBannerByte(utilidades.getBytesDeObjeto(datos[3]));
			if(!String.valueOf(datos[4]).equals("null"))
				item.setDescripcion(String.valueOf(datos[4]));
			if(!String.valueOf(datos[5]).equals("null"))
				item.setEditingStatus(utilidades.convertirCadenaToBoolean(String.valueOf(datos[5])));
			
			
			if(!String.valueOf(datos[6]).equals("null"))
				item.setId(Integer.parseInt(String.valueOf(datos[6])));
			if(!String.valueOf(datos[7]).equals("null"))
				item.setLogo(String.valueOf(datos[7]));
			if(!String.valueOf(datos[8]).equals("null"))
				item.setLogoByte(utilidades.getBytesDeObjeto(datos[8]));
			if(!String.valueOf(datos[9]).equals("null"))
				item.setNombreCategoria(String.valueOf(datos[9]));
			if(!String.valueOf(datos[10]).equals("null"))
				item.setOrden(Integer.parseInt(String.valueOf(datos[10])));
			if(!String.valueOf(datos[11]).equals("null"))	
				item.setPrivado(utilidades.convertirCadenaToBoolean(String.valueOf(datos[11])));
			if(!String.valueOf(datos[12]).equals("null"))
				item.setProgram_id(Integer.parseInt(String.valueOf(datos[12])));
			if(!String.valueOf(datos[13]).equals("null"))	
				item.setTitle(String.valueOf(datos[13]));
			if(!String.valueOf(datos[14]).equals("null"))
				item.setTop_ad(String.valueOf(datos[14]));
			
			if(!String.valueOf(datos[15]).equals("null"))
				item.setUltimaActualizacion(utilidades.convertirStringToDate(String.valueOf(datos[15])));
			if(!String.valueOf(datos[16]).equals("null"))
				item.setVisible(utilidades.convertirCadenaToBoolean(String.valueOf(datos[16])));
			if(!String.valueOf(datos[17]).equals("null"))
				item.setZul(String.valueOf(datos[17]));
			//item.setProgram_id(String.valueOf(datos[18]));
			lista.add(item);
		}
		return lista;
	}
	
	
	public List<Programas> construirPrograma(List<Object[]> listDatos){
		DescuentosBanorteUtils utilidades = new DescuentosBanorteUtils();
		List<Programas> lista = new ArrayList<Programas>();
		for (Object[] datos : listDatos) {
			Programas item = new Programas();
			if(!String.valueOf(datos[0]).equals("null"))
				item.setIdPrograma(Long.parseLong(String.valueOf(datos[0])));
			if(!String.valueOf(datos[1]).equals("null"))
				item.setAccion(Integer.parseInt(String.valueOf(datos[1])));
			if(!String.valueOf(datos[2]).equals("null"))
				item.setBanner(String.valueOf(datos[2]));
			if(!String.valueOf(datos[3]).equals("null"))
				item.setBannerByte(utilidades.getBytesDeObjeto(datos[3]));
			if(!String.valueOf(datos[4]).equals("null"))
				item.setCover(String.valueOf(datos[4]));
			if(!String.valueOf(datos[5]).equals("null"))
				item.setDescripcion(String.valueOf(datos[5]));
			if(!String.valueOf(datos[6]).equals("null"))
				item.setEditingStatus(utilidades.convertirCadenaToBoolean(String.valueOf(datos[6])));
			if(!String.valueOf(datos[7]).equals("null"))
				item.setEstatus(utilidades.convertirCadenaToBoolean(String.valueOf(datos[7])));
			if(!String.valueOf(datos[8]).equals("null"))
				item.setId(Integer.parseInt(String.valueOf(datos[8])));
			if(!String.valueOf(datos[9]).equals("null"))
				item.setLogo(String.valueOf(datos[9]));
			if(!String.valueOf(datos[10]).equals("null"))
				item.setLogoByte(utilidades.getBytesDeObjeto(datos[10]));
			if(!String.valueOf(datos[11]).equals("null"))	
				item.setNombre(String.valueOf(datos[11]));
			if(!String.valueOf(datos[12]).equals("null"))
				item.setPrivado(utilidades.convertirCadenaToBoolean(String.valueOf(datos[12])));
			if(!String.valueOf(datos[13]).equals("null"))	
				item.setTitle(String.valueOf(datos[13]));
			if(!String.valueOf(datos[14]).equals("null"))
				item.setUltimaActualizacion(utilidades.convertirStringToDate(String.valueOf(datos[14])));
			if(!String.valueOf(datos[15]).equals("null"))
				item.setVisible(utilidades.convertirCadenaToBoolean(String.valueOf(datos[15])));
			lista.add(item);
		}
		return lista;
	}
	
	public List<Promocion> construirPromocion(List<Object[]> listDatos, boolean joinPromocionProgramaCategoria){
		DescuentosBanorteUtils utilidades = new DescuentosBanorteUtils();
		List<Promocion> lista = new ArrayList<Promocion>();
		for (Object[] datos : listDatos) {
			Promocion item = new Promocion();
			if(!String.valueOf(datos[0]).equals("null"))
				item.setIdPromocion(Long.parseLong(String.valueOf(datos[0])));
			if(!String.valueOf(datos[1]).equals("null"))
				item.setAccion(String.valueOf(datos[1]));
			
			if(!String.valueOf(datos[2]).equals("null"))
				item.setBanner(String.valueOf(datos[2]));
			if(!String.valueOf(datos[3]).equals("null"))
			item.setBannerByte(utilidades.getBytesDeObjeto(datos[3]));
			if(!String.valueOf(datos[4]).equals("null"))
			item.setCat_id(String.valueOf(datos[4]));
			if(!String.valueOf(datos[5]).equals("null"))
			item.setDescripcion(String.valueOf(datos[5]));
			if(!String.valueOf(datos[6]).equals("null"))
			item.setDescripcionPromocion(String.valueOf(datos[6]));
			if(!String.valueOf(datos[7]).equals("null"))
			item.setEditingStatus(utilidades.convertirCadenaToBoolean(String.valueOf(datos[7])));
			
			if(!String.valueOf(datos[8]).equals("null"))
				item.setFooter(String.valueOf(datos[8]));
			if(!String.valueOf(datos[9]).equals("null"))
				item.setId(String.valueOf(datos[9]));
			if(!String.valueOf(datos[10]).equals("null"))
				item.setLogo(String.valueOf(datos[10]));
			
			/*
		    try {
		    	out = new ByteArrayOutputStream();
				os = new ObjectOutputStream(out);
				os.writeObject(String.valueOf(datos[11]));
				item.setLogoByte(out.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			if(!String.valueOf(datos[11]).equals("null"))
				item.setBannerByte(utilidades.getBytesDeObjeto(datos[11]));
			if(!String.valueOf(datos[12]).equals("null"))
				item.setName(String.valueOf(datos[12]));
			if(!String.valueOf(datos[13]).equals("null"))
				item.setNombre(String.valueOf(datos[13]));
			if(!String.valueOf(datos[14]).equals("null"))
				item.setNotificar(utilidades.convertirCadenaToBoolean(String.valueOf(datos[14])));
			if(!String.valueOf(datos[15]).equals("null"))
				item.setOferta(String.valueOf(datos[15]));
			
			if(!String.valueOf(datos[16]).equals("null"))
				item.setPrivado(utilidades.convertirCadenaToBoolean(String.valueOf(datos[16])));
			if(!String.valueOf(datos[17]).equals("null"))
				item.setSlogan(String.valueOf(datos[17]));
			if(!String.valueOf(datos[18]).equals("null"))
				item.setTelefono(String.valueOf(datos[18]));
			
			
			
			/*
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date date = formatter.parse(String.valueOf(datos[19]));
				item.setUltimaActualizacion(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
			if(!String.valueOf(datos[19]).equals("null"))
				item.setUltimaActualizacion(utilidades.convertirStringToDate(String.valueOf(datos[19])));
			if(!String.valueOf(datos[20]).equals("null"))
				item.setUrl(String.valueOf(datos[20]));
			if(!String.valueOf(datos[21]).equals("null"))
				item.setVisible(utilidades.convertirCadenaToBoolean(String.valueOf(datos[21])));
			
			//item.setContacto(); 22
			//item.setDireccion(); 23
			
			
			if(joinPromocionProgramaCategoria){
				if(!String.valueOf(datos[24]).equals("null")){
					item.setCategoriaNombre(String.valueOf(datos[24]));
				}
				if(!String.valueOf(datos[25]).equals("null")){
					item.setProgramaNombre(String.valueOf(datos[25]));
				}
			}
			
			
			lista.add(item);
		}
		return lista;
	}
	
	
	public List<ReporteTemplate> construirConteoPromocionesPorEstados(List<Object[]> listDatos){
		List<ReporteTemplate> lista = new ArrayList<ReporteTemplate>();
		for (Object[] datos : listDatos) {
			ReporteTemplate item = new ReporteTemplate();
			
			Promocion promo;
			if(!String.valueOf(datos[0]).equals("null")){
				promo = new Promocion();
				promo.setIdPromocion(Long.parseLong(String.valueOf(datos[0])));
				item.setPromocion(promo);
			}	
			if(!String.valueOf(datos[1]).equals("null"))
				item.setNombre(String.valueOf(datos[1]));
			if(!String.valueOf(datos[2]).equals("null"))
				item.setTotal(Integer.parseInt(String.valueOf(datos[2])));
			lista.add(item);
		}
		return lista;
	}
}
