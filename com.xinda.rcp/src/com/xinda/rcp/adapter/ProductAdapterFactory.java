package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.model.Product;

public class ProductAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter adapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getLabel(Object o) {
			Product p = (Product) o;
			return p.getName();
		}

		@Override
		public Object getParent(Object o) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class)
			return adapter;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}

}
