package com.xinda.rcp.dao;

import org.hibernate.Session;

import com.xinda.rcp.hibernate.HibernateUtil;

public abstract class AbstractDAO<T, ID> implements IDAO<T, ID> {

	@Override
	public void delete(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean exists(ID id) {
		return null == find(id);
	}

	@Override
	public void save(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
	}

}
