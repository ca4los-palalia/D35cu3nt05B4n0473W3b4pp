package printworld.descuentosbanorte.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

/**
 * @author Carlos Palalía López
 */
@Repository
public class SessionUtils {

	public static final String USUARIO = "usuario";
	public static final String SUCURSAL = "sucursal";
	public static final String MATRIZ = "matriz";
	public static final String FIRMA = "FIRMA";

	private Logger log = LogManager.getLogger(this.getClass().getName());

	public boolean addToSession(final String keyAttribute, final Object object) {

		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return false;
		}
		session.setAttribute(keyAttribute, object);
		log.info(keyAttribute + " added to session");
		return true;
	}

	public Object getFromSession(final String keyAttribute) {
		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return null;
		}
		return session.getAttribute(keyAttribute);
	}

	public boolean logOut() {
		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return false;
		}
		session.invalidate();
		log.info("user key removed from session");
		return true;
	}
	
}
