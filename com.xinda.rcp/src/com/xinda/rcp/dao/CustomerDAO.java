package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Customer;

public class CustomerDAO extends AbstractDAO<Customer, Long> {
	
	@Override
	public Long count() {
		return null;
	}

	@Override
	public Customer find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer ret = Customer.class.cast(session.createCriteria(Customer.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Customer> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Customer> ret = session.createCriteria(Customer.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
