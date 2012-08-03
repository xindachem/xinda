package com.xinda.rcp.dao;

import java.util.List;

public interface IDAO<T, ID> {
	void save(T entity);

	T find(ID id);

	List<T> findAll();

	Long count();

	void delete(T entity);

	boolean exists(ID id);
}
