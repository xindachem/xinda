package com.xinda.rcp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Folder;

public class FolderDAO extends AbstractDAO<Folder, Long> {

	@Override
	public Long count() {
		return null;
	}

	@Override
	public Folder find(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Folder ret = Folder.class.cast(session.createCriteria(Folder.class)
				.add(Restrictions.idEq(id)).uniqueResult());
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	@Override
	public List<Folder> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Folder> ret = session.createCriteria(Folder.class).list();
		session.getTransaction().commit();
		session.close();
		return ret;
	}

}
