/**
 * 
 */
package printworld.descuentosbanorte.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * @author Carlos Palalía López
 */

@Entity
@Table
public class Ubicacion implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idUbicacion;
	private Double lat;
	private Double lon;
	private Promocion promocion;
	private Estado estado;
	private boolean nuevo;
	private String descripcion;
	private String unidad;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	
	@Column
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Column
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	@OneToOne
	@JoinColumn(name = "promocion")
	public Promocion getPromocion() {
		return promocion;
	}
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
	@OneToOne
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Transient
	public boolean isNuevo() {
		return nuevo;
	}
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	@Column(length = 500)
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Transient
	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
