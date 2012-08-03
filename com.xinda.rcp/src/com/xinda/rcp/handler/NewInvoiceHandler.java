package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.adapter.InvoiceBuilder;
import com.xinda.rcp.dialog.InvoiceDialog;
import com.xinda.rcp.model.Invoice;

public class NewInvoiceHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Invoice invoice = (new InvoiceBuilder()).getInvoice();
		InvoiceDialog dialog = new InvoiceDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), invoice);
		if (dialog.open() == Window.OK) {
			Models.save(invoice);
		}
		return null;
	}

}
