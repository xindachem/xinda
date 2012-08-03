package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.model.Book;

public class BookAdapterFactory implements IAdapterFactory {

	private IWorkbenchAdapter workbenchAdapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}

		@Override
		public String getLabel(Object o) {
			return ((Book) o).getCustomer().getName();
		}

		@Override
		public Object getParent(Object o) {
			return ((Book) o).getFolder();
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
