package com.xinda.rcp.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import com.xinda.rcp.Models;
import com.xinda.rcp.dao.FolderDAO;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Folder;
import com.xinda.rcp.model.Model;

public class FolderView extends ViewPart {

	public static final String ID = "com.xinda.rcp.view.folder"; //$NON-NLS-1$
	private TreeViewer folderViewer;

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));

		folderViewer = new TreeViewer(container, SWT.BORDER);
		// folderViewer.addDoubleClickListener(new IDoubleClickListener() {
		// public void doubleClick(DoubleClickEvent event) {
		// IStructuredSelection sel = (IStructuredSelection) event
		// .getSelection();
		// if (sel == null)
		// return;
		// Object item = sel.getFirstElement();
		// if (item == null)
		// return;
		// DialogFactory factory = new DialogFactory();
		// Dialog dialog = factory.create(item);
		// if (dialog == null)
		// return;
		// if (dialog.open() == Window.OK) {
		// Models.save(item);
		// }
		// }
		// });
		Tree tree = folderViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		folderViewer.setLabelProvider(new WorkbenchLabelProvider());
		folderViewer.setContentProvider(new BaseWorkbenchContentProvider());
		folderViewer.setInput(Model.ROOT);

		Models.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getSource() == Folder.class
						|| event.getSource() == Book.class) {
					setInput();
				}
			}
		});

		folderViewer.expandAll();
		getSite().setSelectionProvider(folderViewer);
	}

	@Override
	public void setFocus() {
		folderViewer.getTree().setFocus();
	}

	/**
	 * 
	 */
	protected void setInput() {
		FolderDAO folder = FolderDAO.class.cast(Models.getDAO(Folder.class));
		folderViewer.setInput(folder.findAll().toArray(new Folder[0]));
	}

}
