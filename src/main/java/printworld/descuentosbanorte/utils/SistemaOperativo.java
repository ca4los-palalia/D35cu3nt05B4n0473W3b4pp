package printworld.descuentosbanorte.utils;

/**
 * @author Carlos Palalía López
 */
public class SistemaOperativo {

	private String version;
	private String arquitectura;
	private String directorioTemporal;
	private String separadorDeArchivos;
	private String separadorDePath;
	private String usuario;
	private String directorioDeTrabajoDelusuario;
	private String directorioDeInicioDelUsuario;
	
	public String getVersion() {
		version = System.getProperty("os.version");
		return version;
	}
	public String getArquitectura() {
		arquitectura = System.getProperty("os.arch");
		return arquitectura;
	}
	public String getDirectorioTemporal() {
		directorioTemporal = System.getProperty("java.io.tmpdir");
		return directorioTemporal;
	}
	public String getSeparadorDeArchivos() {
		separadorDeArchivos = System.getProperty("file.separator");
		return separadorDeArchivos;
	}
	public String getSeparadorDePath() {
		separadorDePath = System.getProperty("path.separator");
		return separadorDePath;
	}
	public String getUsuario() {
		usuario = System.getProperty("user.name");
		return usuario;
	}
	public String getDirectorioDeTrabajoDelusuario() {
		directorioDeTrabajoDelusuario = System.getProperty("user.dir");
		return directorioDeTrabajoDelusuario;
	}
	public String getDirectorioDeInicioDelUsuario() {
		directorioDeInicioDelUsuario = System.getProperty("user.home");
		return directorioDeInicioDelUsuario;
	}
}
