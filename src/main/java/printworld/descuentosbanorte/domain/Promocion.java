/**
 * 
 */
package printworld.descuentosbanorte.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.zkoss.image.AImage;

import printworld.descuentosbanorte.utils.DescuentosBanorteUtils;

/**
 * @author Carlos Palalía López
 */

@Entity
@Table
public class Promocion implements Comparable{

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idPromocion;
	private String nombre;
	private String banner;
	private String slogan;
	private String descripcion;
	private Direccion direccion;
	private Contacto contacto;
	private Categoria categoria;

	private Boolean editingStatus;
	private Boolean privado;
	private Date ultimaActualizacion;
	private String ultimaActualizacionString;
	private String descripcionPromocion;
	/*
	 * private Categoria categoria; private Programas programas; private Double
	 * latitud; private Double Longitud; private String localizacion;
	 */

	private byte[] logoByte;
	private byte[] bannerByte;
	private AImage logoAImage;
	private AImage bannerAImage;

	private String accion;
	private boolean notificar;
	private String footer;
	private String url;
	private String cat_id;
	private String oferta;
	private List<Ubicacion> ubicacion;
	private String logo;
	private String telefono;
	private String id;
	private String name;
	private boolean visible;
	private String programaNombre;
	private String categoriaNombre;
	private PromocionProgramaCategoria promocionProgramaCategoria;
	
	private float lat;
	private float lon;
	private String distancia;
	private String unidad;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}

	@Column(length = 800)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@Column(length = 800)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToOne
	@JoinColumn(name = "direccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@OneToOne
	@JoinColumn(name = "contacto")
	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Column
	public Boolean getEditingStatus() {
		return editingStatus;
	}

	public void setEditingStatus(Boolean editingStatus) {
		this.editingStatus = editingStatus;
	}

	@Column
	@Lob
	public byte[] getLogoByte() {
		return logoByte;
	}

	public void setLogoByte(byte[] logoByte) {
		this.logoByte = logoByte;
	}

	@Column
	@Lob
	public byte[] getBannerByte() {
		return bannerByte;
	}

	public void setBannerByte(byte[] bannerByte) {
		this.bannerByte = bannerByte;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	@Column
	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}

	@Column(length = 800)
	public String getDescripcionPromocion() {
		return descripcionPromocion;
	}

	public void setDescripcionPromocion(String descripcionPromocion) {
		this.descripcionPromocion = descripcionPromocion;
	}

	@Column
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	/*
	 * @Column public String getLogo() { return logo; }
	 * 
	 * public void setLogo(String logo) { this.logo = logo; }
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "categoria") public Categoria getCategoria() { return
	 * categoria; }
	 * 
	 * public void setCategoria(Categoria categoria) { this.categoria =
	 * categoria; }
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "programas") public Programas getProgramas() { return
	 * programas; }
	 * 
	 * public void setProgramas(Programas programas) { this.programas =
	 * programas; }
	 * 
	 * @Column public Double getLatitud() { return latitud; }
	 * 
	 * public void setLatitud(Double latitud) { this.latitud = latitud; }
	 * 
	 * @Column public Double getLongitud() { return Longitud; }
	 * 
	 * public void setLongitud(Double longitud) { Longitud = longitud; }
	 * 
	 * @Column public String getLocalizacion() { return localizacion; }
	 * 
	 * public void setLocalizacion(String localizacion) { this.localizacion =
	 * localizacion; }
	 */
	// ************** CAMPOS JSON *************************

	@Column
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Column
	public boolean isNotificar() {
		return notificar;
	}

	public void setNotificar(boolean notificar) {
		this.notificar = notificar;
	}

	@Column
	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	@Column(length = 800)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column
	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	@Column(length = 800)
	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	@Column
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(length = 800)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 800)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public AImage getLogoAImage() {
		try {
			if (logoByte != null) {
				logoAImage = new AImage("logoByte", logoByte);

			} else {
				/*
				 * if (footer != null) { if(logoAImage == null){ AImage imgTemp
				 * = new
				 * DescuentosBanorteUtils().convertirImagenJSonToAIMage(logo);
				 * logoAImage = new AImage("logoByte", imgTemp.getByteData()); }
				 * }
				 */
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logoAImage;
	}

	public void setLogoAImage(AImage logoAImage) {
		this.logoAImage = logoAImage;
	}

	@Transient
	public AImage getBannerAImage() {
		try {
			if (bannerByte != null) {
				bannerAImage = new AImage("bannerByte", bannerByte);
			} else {
				/*
				 * if (footer != null) { if(bannerAImage == null){ AImage
				 * imgTemp = new
				 * DescuentosBanorteUtils().convertirImagenJSonToAIMage(footer);
				 * bannerAImage = new AImage("bannerByte",
				 * imgTemp.getByteData()); } }
				 */
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bannerAImage;
	}

	public void setBannerAImage(AImage bannerAImage) {
		this.bannerAImage = bannerAImage;
	}

	@SuppressWarnings("static-access")
	@Transient
	public String getUltimaActualizacionString() {

		if (ultimaActualizacion != null) {
			ultimaActualizacionString = new DescuentosBanorteUtils().getFechaActualConHora(ultimaActualizacion);
		}

		return ultimaActualizacionString;
	}

	public void setUltimaActualizacionString(String ultimaActualizacionString) {
		this.ultimaActualizacionString = ultimaActualizacionString;
	}

	@Transient
	public List<Ubicacion> getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(List<Ubicacion> ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Transient
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Transient
	public PromocionProgramaCategoria getPromocionProgramaCategoria() {
		return promocionProgramaCategoria;
	}

	public void setPromocionProgramaCategoria(PromocionProgramaCategoria promocionProgramaCategoria) {
		this.promocionProgramaCategoria = promocionProgramaCategoria;
	}

	@Column
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Transient
	public String getProgramaNombre() {
		return programaNombre;
	}

	public void setProgramaNombre(String programaNombre) {
		this.programaNombre = programaNombre;
	}

	@Transient
	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	@Transient
	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	@Transient
	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	@Transient
	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	@Transient
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public int compareTo(Object o) {
		Promocion promo = (Promocion)o;
		Float a = 0F;
        Float b = 1F;
        
        if(promo.getDistancia() != null)
        	b=new Float(promo.getDistancia());
		if(this.getDistancia() != null)
			a=new Float(this.getDistancia());
		
        return a.compareTo(b);
	}
}
