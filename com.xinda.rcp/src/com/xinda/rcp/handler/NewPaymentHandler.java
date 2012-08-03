package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.builder.PaymentBuilder;
import com.xinda.rcp.dialog.PaymentDialog;
import com.xinda.rcp.model.Payment;

public class NewPaymentHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Payment payment = (new PaymentBuilder()).getPayment();
		PaymentDialog dialog = new PaymentDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), payment);
		if (dialog.open() == Window.OK) {
			Models.save(payment);
		}
		return null;
	}

}
