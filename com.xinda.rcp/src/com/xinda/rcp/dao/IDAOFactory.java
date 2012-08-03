package com.xinda.rcp.dao;

public interface IDAOFactory {

	public <T> IDAO<T, Long> getDAO(Class<T> clazz);
	
}
