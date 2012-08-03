package com.xinda.rcp.adapter;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.google.common.base.Preconditions;

public class WorkbenchTableLabelProvider extends WorkbenchLabelProvider
		implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		ITableLabelProvider provider = (ITableLabelProvider) Platform
				.getAdapterManager().getAdapter(element,
						ITableLabelProvider.class);
		Preconditions.checkNotNull(provider);
		return provider.getColumnImage(element, columnIndex);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ITableLabelProvider provider = (ITableLabelProvider) Platform
				.getAdapterManager().getAdapter(element,
						ITableLabelProvider.class);
		Preconditions.checkNotNull(provider);
		return provider.getColumnText(element, columnIndex);
	}

}
