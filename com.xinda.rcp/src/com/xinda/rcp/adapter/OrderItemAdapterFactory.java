package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.model.OrderItem;

public class OrderItemAdapterFactory implements IAdapterFactory {
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
			return null;
		}

		@Override
		public Object getParent(Object o) {
			return ((OrderItem) o).getOrder();
		}

	};

	private ITableLabelProvider labelProvider1 = new OrderItemTableLabelProvider();

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class)
			return workbenchAdapter;
		if (adapterType == ITableLabelProvider.class)
			return labelProvider1;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class, ITableLabelProvider.class };
	}

}
