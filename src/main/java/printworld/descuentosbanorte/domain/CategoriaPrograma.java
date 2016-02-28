/**
 * 
 */
package printworld.descuentosbanorte.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Carlos Palalía López
 */

@Entity
@Table
public class CategoriaPrograma implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idCategoriaPrograma;
	private Programas programa;
	private Categoria categoria;
	private Date ultimaActualizacion;
	private String ultimaActualizacionString;
	private Boolean editingStatus;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCategoriaPrograma() {
		return idCategoriaPrograma;
	}

	public void setIdCategoriaPrograma(Long idCategoriaPrograma) {
		this.idCategoriaPrograma = idCategoriaPrograma;
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
	
	

	@Transient
	public String getUltimaActualizacionString() {
		if (ultimaActualizacion != null) {
			SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");

			try {
				ultimaActualizacionString = sdfr.format(ultimaActualizacion);
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		return ultimaActualizacionString;
	}

	public void setUltimaActualizacionString(String ultimaActualizacionString) {
		this.ultimaActualizacionString = ultimaActualizacionString;
	}

	@ManyToOne
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column
	public Boolean getEditingStatus() {
		return editingStatus;
	}

	public void setEditingStatus(Boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
	
	
}
