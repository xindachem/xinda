package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.builder.BookBuilder;
import com.xinda.rcp.dialog.BookDialog;
import com.xinda.rcp.model.Book;

public class NewBookHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Book book = (new BookBuilder()).getBook();
		BookDialog dialog = new BookDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), book);
		if (dialog.open() == Window.OK) {
			Models.save(book);
		}
		return null;
	}

}
