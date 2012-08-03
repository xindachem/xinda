package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.dao.BookDAO;
import com.xinda.rcp.model.Folder;
import com.xinda.rcp.model.Model;

public class FolderAdapterFactory implements IAdapterFactory {

	private IWorkbenchAdapter workbenchAdapter = new IWorkbenchAdapter() {

		private BookDAO dao = new BookDAO();

		@Override
		public Object[] getChildren(Object o) {
			return dao.findByFolder((Folder) o).toArray();
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}

		@Override
		public String getLabel(Object o) {
			return ((Folder) o).getName();
		}

		@Override
		public Object getParent(Object o) {
			return Model.ROOT;
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
