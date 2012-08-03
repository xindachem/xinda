package com.xinda.rcp.adapter;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.google.common.base.Strings;
import com.xinda.rcp.model.Constants;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.view.BookView;

public class PaymentTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == BookView.ICON)
			return ResourceManager.getPluginImage("com.xinda.rcp",
					"icons/Dollar2.png");
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case BookView.ICON:
			return ((Payment) element).getType().getName();
		case BookView.DATE:
			if (element instanceof Payment) {
				return new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5")
						.format(((Payment) element).getDate().getTime());
			}
			break;
		case BookView.NUMBER:
			if (element instanceof Payment) {
				Long id = ((Payment) element).getOrderId();
				if (null != id)
					return id.toString();
			}
			break;
		case BookView.AMOUNT:
			if (element instanceof Payment) {
				return String.valueOf(((Payment) element).getAmount().setScale(
						Constants.AMOUNT_SCALE));
			}
			break;
		}
		return Strings.nullToEmpty(null);
	}

}
