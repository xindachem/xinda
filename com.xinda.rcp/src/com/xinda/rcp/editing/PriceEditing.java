package com.xinda.rcp.editing;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import com.xinda.rcp.model.OrderItem;

public class PriceEditing extends EditingSupport {
	private TextCellEditor editor;
	private ColumnViewer viewer;

	public PriceEditing(TableViewer viewer) {
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
		return item.getPrice().setScale(2).toString();
	}

	@Override
	protected void setValue(Object element, Object value) {
		OrderItem item = (OrderItem) element;
		item.setPrice(new BigDecimal((String) value));
		viewer.update(element, null);
	}

}
