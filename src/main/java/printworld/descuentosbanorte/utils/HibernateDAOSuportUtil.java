/**
 * 
 */
package printworld.descuentosbanorte.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Carlos Palalía López
 */
public abstract class HibernateDAOSuportUtil extends HibernateDaoSupport {

	@Autowired
	public void init(final SessionFactory sessionFactory)
			throws DataAccessException {
		setSessionFactory(sessionFactory);
	}
}
