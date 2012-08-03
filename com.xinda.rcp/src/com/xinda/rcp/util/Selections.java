package com.xinda.rcp.util;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class Selections {

	public static Object selectionToObject(ISelection selection) {
		if (selection != null && selection instanceof IStructuredSelection)
			return ((IStructuredSelection) selection).getFirstElement();
		return null;
	}

}
