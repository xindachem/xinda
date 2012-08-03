package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Order;

public class OrderDAO extends AbstractDAO<Order, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Order find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Order ret = Order.class.cast(session.createCriteria(Order.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Order> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Order> ret = session.createCriteria(Order.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<Order> findByBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Order> ret = session.createCriteria(Order.class)
				.add(Restrictions.like("book", book)).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
