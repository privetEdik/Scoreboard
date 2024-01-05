package kettlebell.dao;

import java.io.Serializable;
import java.util.List;

import kettlebell.entity.BaseEntity;

public interface Repository <K extends Serializable, E extends BaseEntity<K>>{
	E save(E entity);
	List<E> findAll();
 }
