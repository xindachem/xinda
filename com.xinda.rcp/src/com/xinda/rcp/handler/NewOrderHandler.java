package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.builder.OrderBuilder;
import com.xinda.rcp.dialog.OrderDialog;
import com.xinda.rcp.model.Order;

public class NewOrderHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		OrderBuilder builder = new OrderBuilder();
		Order order = builder.getOrder();
		OrderDialog dialog = new OrderDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), order);
		if (dialog.open() == Window.OK) {
			Models.save(order);
		}
		return null;
	}

}
