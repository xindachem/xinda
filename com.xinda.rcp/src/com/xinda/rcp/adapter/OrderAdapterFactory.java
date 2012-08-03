package com.xinda.rcp.adapter;

import java.util.Calendar;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Order;

public class OrderAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter workbenchAdapter = new IWorkbenchAdapter() {

		@Override
		public Object[] getChildren(Object obj) {
			return ((Order) obj).getItems().toArray();
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

	private ICalendarProvider calendarProvider = new ICalendarProvider() {

		@Override
		public Calendar getDate(Object obj) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Order) obj).getDate());
			return cal;
		}

	};

	private ICustomerProvider customerProvider = new ICustomerProvider() {

		@Override
		public Customer getCustomer(Object element) {
			return ((Order) element).getBook().getCustomer();
		}

	};

	private ITableLabelProvider labelProvider = new OrderTableLabelProvider();

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class)
			return workbenchAdapter;
		if (adapterType == ICalendarProvider.class)
			return calendarProvider;
		if (adapterType == ITableLabelProvider.class)
			return labelProvider;
		if (adapterType == ICustomerProvider.class)
			return customerProvider;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class, ICalendarProvider.class,
				ITableLabelProvider.class, ICustomerProvider.class };
	}

}
