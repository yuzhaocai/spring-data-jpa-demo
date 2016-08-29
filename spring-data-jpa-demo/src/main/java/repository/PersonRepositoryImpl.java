package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 自己扩展JPARepository
 * @author Administrator
 *
 */
public class PersonRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Long> findPersonIdByFisrtName(String firstName){
		String hql = "select id from Person where firstName = ?1";
		Query query = em.createQuery(hql);
		query.setParameter(1,firstName);
		return query.getResultList();
	}
}
