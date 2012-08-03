package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Payment;

public class PaymentDAO extends AbstractDAO<Payment, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Payment find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Payment ret = Payment.class.cast(session.createCriteria(Payment.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Payment> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Payment> ret = session.createCriteria(Payment.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<Payment> findByBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Payment> ret = session.createCriteria(Payment.class)
				.add(Restrictions.like("book", book)).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
