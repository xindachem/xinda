package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Balance;
import com.xinda.rcp.model.Book;

public class BalanceDAO extends AbstractDAO<Balance, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Balance find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Balance ret = Balance.class.cast(session.createCriteria(Balance.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Balance> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Balance> ret = session.createCriteria(Balance.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<Balance> findByBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Balance> ret = session.createCriteria(Balance.class)
				.add(Restrictions.like("book", book)).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
