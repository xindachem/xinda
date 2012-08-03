package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Folder;

public class BookDAO extends AbstractDAO<Book, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Book find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Book ret = Book.class.cast(session.createCriteria(Book.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Book> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Book> ret = session.createCriteria(Book.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<Book> findByFolder(Folder folder) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Book> ret = session.createCriteria(Book.class)
				.add(Restrictions.like("folder", folder)).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
