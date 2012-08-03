package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.builder.CustomerBuilder;
import com.xinda.rcp.dialog.CustomerDialog;
import com.xinda.rcp.model.Customer;

public class NewCustomerHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		CustomerBuilder builder = new CustomerBuilder();
		Customer customer = builder.build();
		CustomerDialog dialog = new CustomerDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), customer);
		if (dialog.open() == Window.OK) {
			Models.save(customer);
		}
		return null;
	}

}
