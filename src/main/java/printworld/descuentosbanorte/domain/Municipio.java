package printworld.descuentosbanorte.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Carlos Palalía López
 */
@Entity
@Table
public class Municipio implements Serializable {

	private static final long serialVersionUID = 2327486550971803091L;
	private Long idMunicipio;
	private String nombre;
	private String numeroMunicipio;
	private Estado estado;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_estado", nullable = false)
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToOne
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Column
	public String getNumeroMunicipio() {
		return numeroMunicipio;
	}

	public void setNumeroMunicipio(String numeroMunicipio) {
		this.numeroMunicipio = numeroMunicipio;
	}
	
}
