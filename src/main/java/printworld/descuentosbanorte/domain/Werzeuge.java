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
public class Werzeuge implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idWerzeuge;
	private String nombre;
	private String descripcion;
	private Date ultimaActualizacion;
	private String ultimaActualizacionString;
	private byte[] jSonSource;
	private boolean actualizado;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdWerzeuge() {
		return idWerzeuge;
	}

	public void setIdWerzeuge(Long idWerzeuge) {
		this.idWerzeuge = idWerzeuge;
	}

	@Column(length = 800)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Column
	@Lob
	public byte[] getjSonSource() {
		return jSonSource;
	}

	public void setjSonSource(byte[] jSonSource) {
		this.jSonSource = jSonSource;
	}

	@Column
	public boolean isActualizado() {
		return actualizado;
	}

	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}
	
	
}
