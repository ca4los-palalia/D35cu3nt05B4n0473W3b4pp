package printworld.descuentosbanorte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Carlos Palalía López
 */
@Entity
@Table
public class Pais {

	private Long idPais;
	private String nombre;
	private String abreviatura;

	
	@Id
	@Column(name = "idPais", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	
	
}
