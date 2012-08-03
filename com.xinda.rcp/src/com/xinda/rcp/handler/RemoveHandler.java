package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import com.xinda.rcp.Models;
import com.xinda.rcp.util.Selections;

public class RemoveHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Models.delete(Selections.selectionToObject(HandlerUtil
				.getCurrentSelection(event)));
		return null;
	}

}
