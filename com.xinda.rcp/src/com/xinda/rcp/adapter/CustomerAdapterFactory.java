package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.wb.swt.ResourceManager;

import com.xinda.rcp.model.Customer;

public class CustomerAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter adapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object object) {
			return ImageDescriptor.createFromImage(ResourceManager
					.getPluginImage("com.xinda.rcp", "icons/User.png"));
		}

		@Override
		public String getLabel(Object o) {
			Customer c = (Customer) o;
			return c.getName();
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
			return adapter;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}

}
