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
public class Programas implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idPrograma;
	private String nombre;
	private String logo;
	private String banner;
	private String descripcion;
	private Date ultimaActualizacion;
	private Boolean privado;
	private Boolean editingStatus;
	private Boolean estatus;
	private String ultimaActualizacionString;

	private byte[] logoByte;
	private byte[] bannerByte;
	private AImage logoAImage;
	private AImage bannerAImage;
	
	private Integer accion;
	private Integer id;
	private String title;
	private String cover;
	private boolean visible;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(length = 800)
	public String getLogo() {
		return logo;
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

	@Column(length = 800)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@Column
	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	@Column
	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	@Column
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 800)
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Column
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
