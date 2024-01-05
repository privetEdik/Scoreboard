package kettlebell.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import kettlebell.dao.Repository;
import kettlebell.entity.BaseEntity;
import static kettlebell.utils.HibernateRunner.getInstanceSession;

public class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {
	private Class<E> clazz;
	private Session session;

	public BaseRepository(Class<E> clazz) {
		this.clazz = clazz;
		this.session = getInstanceSession();
	}

	public Session getSession() {
		return session;
	}

	@Override
	public E save(E entity) {
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
		return entity;
	}

	@Override
	public List<E> findAll() {
		session.beginTransaction();
		CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(clazz);
		criteria.from(clazz);
		List<E> list = session.createQuery(criteria).getResultList();
		session.getTransaction().commit();
		return list;
	}

}
