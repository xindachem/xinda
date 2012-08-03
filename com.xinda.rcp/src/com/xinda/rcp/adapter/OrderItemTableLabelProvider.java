package com.xinda.rcp.adapter;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Strings;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.OrderItem;
import com.xinda.rcp.model.Constants;
import com.xinda.rcp.view.BookView;

public class OrderItemTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case BookView.PRODUCT:
			if (element instanceof OrderItem) {
				return ((OrderItem) element).getProduct().getName();
			}
			break;
		case BookView.QUANTITY:
			if (element instanceof OrderItem) {
				return String.valueOf(((OrderItem) element).getQuantity()
						.setScale(3));
			}
			break;
		case BookView.PRICE:
			if (element instanceof OrderItem) {
				return String.valueOf(((OrderItem) element).getPrice()
						.setScale(Constants.PRICE_SCALE));
			}
			break;
		case BookView.AMOUNT:
			if (element instanceof Order) {
				return String.valueOf(((Order) element).getAmount().setScale(
						Constants.AMOUNT_SCALE));
			} else if (element instanceof OrderItem) {
				return String.valueOf(((OrderItem) element).getAmount()
						.setScale(Constants.AMOUNT_SCALE));
			}
			break;
		}
		return Strings.nullToEmpty(null);
	}

}
