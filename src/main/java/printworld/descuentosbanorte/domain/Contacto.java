package printworld.descuentosbanorte.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Carlos Palalía López
 */
@Entity
@Table
public class Contacto implements Serializable {

	private static final long serialVersionUID = -7065704145995755117L;
	private Long idContacto;
	private Telefono telefono;
	private Email email;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Long idContacto) {
		this.idContacto = idContacto;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "telefono")
	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
