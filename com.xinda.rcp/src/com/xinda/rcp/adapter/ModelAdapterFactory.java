package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.dao.FolderDAO;

public class ModelAdapterFactory implements IAdapterFactory {

	private FolderDAO fdao = new FolderDAO();

	private IWorkbenchAdapter workbenchAdapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			return fdao.findAll().toArray();
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}

		@Override
		public String getLabel(Object o) {
			return null;
		}

		@Override
		public Object getParent(Object o) {
			return null;
		}

	};

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class)
			return workbenchAdapter;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}

}
