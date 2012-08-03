package com.xinda.rcp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.common.base.Preconditions;
import com.xinda.rcp.dao.DefaultDAOFactory;
import com.xinda.rcp.dao.IDAO;
import com.xinda.rcp.dao.IDAOFactory;

public class Models {

	private static Models m_instance;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private IDAOFactory df = DefaultDAOFactory.getInstance();

	public static final String EVENT_FOLDER = "folder";

	static {
		m_instance = new Models();
	}

	public static void addPropertyChangeListener(PropertyChangeListener listener) {
		m_instance.pcs.addPropertyChangeListener(listener);
	}

	public static <T> void save(T obj) {
		Preconditions.checkNotNull(obj);
		@SuppressWarnings("unchecked")
		IDAO<T, Long> dao = (IDAO<T, Long>) m_instance.df
				.getDAO(obj.getClass());
		Preconditions.checkNotNull(dao);
		dao.save(obj);
		Activator.log("SAVE: " + obj.toString());
		m_instance.pcs.firePropertyChange(obj.getClass().getName(), null, obj);
	}

	public static <T> void delete(T obj) {
		Preconditions.checkNotNull(obj);
		@SuppressWarnings("unchecked")
		IDAO<T, Long> dao = (IDAO<T, Long>) m_instance.df
				.getDAO(obj.getClass());
		Preconditions.checkNotNull(dao);
		dao.delete(obj);
		Activator.log("DELETE: " + obj.toString());
		m_instance.pcs.firePropertyChange(obj.getClass().getName(), obj, null);
	}

	public static <T> IDAO<T, Long> getDAO(Class<T> clazz) {
		return m_instance.df.getDAO(clazz);
	}
}