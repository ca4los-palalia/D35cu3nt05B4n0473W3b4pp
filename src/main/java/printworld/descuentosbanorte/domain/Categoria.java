/**
 * 
 */
package printworld.descuentosbanorte.domain;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
public class Categoria implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idCategoria;
	private String nombreCategoria;
	private String logo;
	private String banner;
	private String descripcion;
	private String zul;
	private Programas programa;
	private Integer orden;
	private Date ultimaActualizacion;
	private Boolean privado;
	private Boolean editingStatus;
	private String ultimaActualizacionString;
	private byte[] logoByte;
	private byte[] bannerByte;
	private AImage logoAImage;
	private AImage bannerAImage;
	
	private String top_ad; 
	private String title; 
	private Integer program_id;
	private Integer id;
	private Integer accion;
	private boolean visible;
	
	private boolean todos;
	private boolean subcategoria;
	
	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Column(length = 800)
	public String getLogo() {
		return logo;
	}

	@Column
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column
	public String getZul() {
		return zul;
	}

	public void setZul(String zul) {
		this.zul = zul;
	}
	
	@ManyToOne
	public Programas getPrograma() {
		return programa;
	}

	public void setPrograma(Programas programa) {
		this.programa = programa;
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
	
	@Column
	public Boolean getEditingStatus() {
		return editingStatus;
	}

	public void setEditingStatus(Boolean editingStatus) {
		this.editingStatus = editingStatus;
	}

	@SuppressWarnings("static-access")
	@Transient
	public String getUltimaActualizacionString() {
		if(ultimaActualizacion != null)
			ultimaActualizacionString = new DescuentosBanorteUtils().getFechaActualConHora(ultimaActualizacion);
		return ultimaActualizacionString;
	}

	public void setUltimaActualizacionString(String ultimaActualizacionString) {
		this.ultimaActualizacionString = ultimaActualizacionString;
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

	@Transient
	public AImage getLogoAImage() {
		if (logoByte != null) {
			try {
				logoAImage = new AImage("logoByte", logoByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logoAImage;
	}

	public void setLogoAImage(AImage logoAImage) {
		this.logoAImage = logoAImage;
	}

	@Transient
	public AImage getBannerAImage() {
		if (bannerByte != null) {
			try {
				bannerAImage = new AImage("bannerByte", bannerByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bannerAImage;
	}

	public void setBannerAImage(AImage bannerAImage) {
		this.bannerAImage = bannerAImage;
	}

	@Column(length = 800)
	public String getTop_ad() {
		return top_ad;
	}

	public void setTop_ad(String top_ad) {
		this.top_ad = top_ad;
	}

	@Column(length = 800)
	public String getTitle() {
		return title;
	}

	@Column(length = 800)
	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	public Integer getProgram_id() {
		return program_id;
	}

	public void setProgram_id(Integer program_id) {
		this.program_id = program_id;
	}

	@Column
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	@Column
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Column
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Transient
	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	@Transient
	public boolean isSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(boolean subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
	
}
