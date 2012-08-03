package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.PaymentType;

public class PaymentTypeDAO extends AbstractDAO<PaymentType, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public PaymentType find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PaymentType ret = PaymentType.class.cast(session
				.createCriteria(PaymentType.class).add(Restrictions.idEq(id))
				.uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<PaymentType> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PaymentType> ret = session.createCriteria(PaymentType.class)
				.list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
