package com.xinda.rcp.editing;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import com.xinda.rcp.model.OrderItem;

public class QuantityEditing extends EditingSupport {
	private TextCellEditor editor;
	private ColumnViewer viewer;

	public QuantityEditing(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
		editor = new TextCellEditor(viewer.getTable(), SWT.NONE);
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		OrderItem item = (OrderItem) element;
		return item.getQuantity().setScale(3).toString();
	}

	@Override
	protected void setValue(Object element, Object value) {
		OrderItem item = (OrderItem) element;
		item.setQuantity(new BigDecimal((String) value));
		viewer.update(element, null);
	}

}
