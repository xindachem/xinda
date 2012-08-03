package com.xinda.rcp.editing;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.xinda.rcp.Models;
import com.xinda.rcp.dao.ProductDAO;
import com.xinda.rcp.model.OrderItem;
import com.xinda.rcp.model.Product;

public class ProductEditing extends EditingSupport {

	private ComboBoxViewerCellEditor editor;
	private TableViewer viewer;
	private ProductDAO product;

	public ProductEditing(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
		editor = new ComboBoxViewerCellEditor(viewer.getTable(), SWT.READ_ONLY);
		editor.setLabelProvider(new WorkbenchLabelProvider());
		editor.setContentProvider(ArrayContentProvider.getInstance());
		product = (ProductDAO) Models.getDAO(Product.class);
		editor.setInput(product.findAll());
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
		return item.getProduct();
	}

	@Override
	protected void setValue(Object element, Object value) {
		OrderItem item = (OrderItem) element;
		item.setProduct((Product) value);
		viewer.update(element, null);
	}

}
