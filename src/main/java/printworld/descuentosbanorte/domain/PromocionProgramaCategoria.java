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
public class PromocionProgramaCategoria implements Serializable {

	private static final long serialVersionUID = 7367612078810564830L;
	private Long idPromocionProgramaCategoria;
	private Categoria categoria;
	private Programas programas;
	private Promocion promocion;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdPromocionProgramaCategoria() {
		return idPromocionProgramaCategoria;
	}

	public void setIdPromocionProgramaCategoria(Long idPromocionProgramaCategoria) {
		this.idPromocionProgramaCategoria = idPromocionProgramaCategoria;
	}

	@OneToOne
	@JoinColumn(name = "categoria")
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@OneToOne
	@JoinColumn(name = "programas")
	public Programas getProgramas() {
		return programas;
	}

	public void setProgramas(Programas programas) {
		this.programas = programas;
	}

	@OneToOne
	@JoinColumn(name = "promocion")
	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
	
	
}
