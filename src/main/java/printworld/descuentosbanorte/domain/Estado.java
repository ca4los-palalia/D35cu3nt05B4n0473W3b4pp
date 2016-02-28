package printworld.descuentosbanorte.domain;

import java.io.Serializable;

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
@Table (name= "Estado")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = -6891447220002443668L;
	private long idEstado;
	private String nombre;
	private String abreviatura;
	private String clave;
	private String descripcion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_estado" , nullable =false)
	public long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	
	@Column(name = "nombre", length = 100)
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
	@Column
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Column
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
