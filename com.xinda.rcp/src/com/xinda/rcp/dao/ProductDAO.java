package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Product;

public class ProductDAO extends AbstractDAO<Product, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Product find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Product ret = Product.class.cast(session.createCriteria(Product.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Product> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Product> ret = session.createCriteria(Product.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
