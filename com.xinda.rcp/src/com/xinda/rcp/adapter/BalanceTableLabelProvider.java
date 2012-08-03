package com.xinda.rcp.adapter;

import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.google.common.base.Strings;
import com.xinda.rcp.Messages;
import com.xinda.rcp.model.Balance;
import com.xinda.rcp.model.Constants;
import com.xinda.rcp.view.BookView;

public class BalanceTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == BookView.ICON)
			return ResourceManager.getPluginImage("com.xinda.rcp",
					"icons/Poll.png");
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case BookView.ICON:
			return Messages.TXT_BALANCE;
		case BookView.DATE:
			if (element instanceof Balance) {
				ICalendarProvider provider = (ICalendarProvider) Platform
						.getAdapterManager().getAdapter(element,
								ICalendarProvider.class);
				return new SimpleDateFormat("yyyy\u5E74MM\u6708")
						.format(provider.getDate(element).getTime());
			}
			break;
		case BookView.AMOUNT:
			if (element instanceof Balance) {
				return String.valueOf(((Balance) element).getAmount().setScale(
						Constants.AMOUNT_SCALE));
			}
			break;
		}
		return Strings.nullToEmpty(null);
	}
}