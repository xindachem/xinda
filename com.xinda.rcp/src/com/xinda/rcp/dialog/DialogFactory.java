package com.xinda.rcp.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.Payment;

public class DialogFactory {

	public Dialog create(Object o) {
		Dialog dialog = null;
		if (o instanceof Order)
			dialog = new OrderDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), (Order) o);
		else if (o instanceof Payment)
			dialog = new PaymentDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), (Payment) o);
		else if (o instanceof Invoice)
			dialog = new InvoiceDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), (Invoice) o);
		else if (o instanceof Customer)
			dialog = new CustomerDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), (Customer) o);
		return dialog;
	}

}
