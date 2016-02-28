package printworld.descuentosbanorte.domain;


/**
 * @author Carlos Palalía López
 */

public class ReporteTemplate {

	private Long idReportetemplate;
	private Promocion promocion;
	private Programas programa;
	private Categoria categoria;;
	private Ubicacion ubicacion;
	private Estado estado;
	private String nombre;
	private int total;
	
	public Long getIdReportetemplate() {
		return idReportetemplate;
	}
	public void setIdReportetemplate(Long idReportetemplate) {
		this.idReportetemplate = idReportetemplate;
	}
	public Promocion getPromocion() {
		return promocion;
	}
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
	public Programas getPrograma() {
		return programa;
	}
	public void setPrograma(Programas programa) {
		this.programa = programa;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
