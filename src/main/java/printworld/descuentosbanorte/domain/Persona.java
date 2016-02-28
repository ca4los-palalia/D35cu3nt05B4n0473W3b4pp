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




/**
 * @author Carlos Palalía López
 */
@Entity
@Table
public class Persona implements Serializable {

	private static final long serialVersionUID = 1334909317661584425L;
	private Long idPersona;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private String nombreCompleto;
	private String imagen;
	private Direccion direccion;
	private Contacto contacto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	@Column
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getNombreCompleto() {
		if (nombre.isEmpty() &&  apellidoPaterno.isEmpty() && apellidoPaterno.isEmpty()) {
			return nombreCompleto;
		} else {
			return nombreCompleto = nombre + " " + apellidoPaterno + " "
					+ apellidoMaterno;	
		}
		
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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

}
