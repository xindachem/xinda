package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Invoice;

public class InvoiceDAO extends AbstractDAO<Invoice, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Invoice find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Invoice ret = Invoice.class.cast(session.createCriteria(Invoice.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Invoice> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Invoice> ret = session.createCriteria(Invoice.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<Invoice> findByBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Invoice> ret = session.createCriteria(Invoice.class)
				.add(Restrictions.like("book", book)).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
