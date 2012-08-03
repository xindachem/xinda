package com.xinda.rcp.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.xinda.rcp.Models;
import com.xinda.rcp.dialog.FolderDialog;
import com.xinda.rcp.model.Folder;

public class NewFolderHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Folder catalog = new Folder();
		FolderDialog dialog = new FolderDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), catalog);
		if (dialog.open() == Window.OK) {
			Models.save(catalog);
		}
		return null;
	}

}
