package com.xinda.rcp.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import com.xinda.rcp.Messages;
import com.xinda.rcp.dao.CustomerDAO;
import com.xinda.rcp.dao.FolderDAO;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Folder;

public class BookDialog extends TitleAreaDialog {

	private Book book;
	private ListViewer customerViewer;
	private ListViewer folderViewer;
	private CustomerDAO cdao = new CustomerDAO();
	private FolderDAO fdao = new FolderDAO();

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public BookDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public BookDialog(Shell shell, Book book) {
		this(shell);
		this.book = book;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_BOOK_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText(Messages.BookDialog_lblNewLabel_text);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setText(Messages.BookDialog_lblNewLabel_1_text);

		customerViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list = customerViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		folderViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list_1 = folderViewer.getList();
		list_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		button.setText(Messages.BTN_ADD);
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText(Messages.BTN_FINISH);
		initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(260, 239);
	}

	protected DataBindingContext initDataBindings() {
		ViewerSupport.bind(customerViewer, new WritableList(cdao.findAll(),
				Customer.class), PojoProperties.value(Customer.class, "name"));

		ViewerSupport.bind(folderViewer, new WritableList(fdao.findAll(),
				Folder.class), PojoProperties.value(Folder.class, "name"));

		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue target = ViewerProperties.singleSelection().observe(
				customerViewer);
		IObservableValue model = PojoProperties.value("customer").observe(book);
		bindingContext.bindValue(target, model, null, null);
		//
		return bindingContext;
	}

}
