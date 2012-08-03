package com.xinda.rcp.adapter;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.google.common.base.Strings;
import com.xinda.rcp.Messages;
import com.xinda.rcp.model.Constants;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.view.BookView;

public class InvoiceTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == BookView.ICON)
			return ResourceManager.getPluginImage("com.xinda.rcp",
					"icons/Document Spreadsheet.png");
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case BookView.ICON:
			return Messages.TXT_INVOICE;
		case BookView.DATE:
			if (element instanceof Invoice) {
				return new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5")
						.format(((Invoice) element).getDate().getTime());
			}
			break;
		case BookView.NUMBER:
			if (element instanceof Invoice) {
				return String.valueOf(((Invoice) element).getId());
			}
			break;
		case BookView.AMOUNT:
			if (element instanceof Invoice) {
				return String.valueOf(((Invoice) element).getAmount().setScale(
						Constants.AMOUNT_SCALE));
			}
			break;
		}
		return Strings.nullToEmpty(null);
	}
}
