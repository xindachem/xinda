package com.xinda.rcp.adapter;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;

public class WorkbenchTableContentProvider extends BaseWorkbenchContentProvider {

	@Override
	public Object[] getChildren(Object element) {
		return super.getChildren(element);
	}

	@Override
	public Object[] getElements(Object element) {
		return ArrayContentProvider.getInstance().getElements(element);
	}

}
