package com.xinda.rcp.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Preconditions;
import com.xinda.rcp.Messages;
import com.xinda.rcp.model.Customer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class CustomerDialog extends TitleAreaDialog {

	private Customer customer;
	private Text txtShort;
	private Text txtLong;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CustomerDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public CustomerDialog(Shell parent, Customer c) {
		this(parent);
		customer = Preconditions.checkNotNull(c);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_CUSTOMER_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText(Messages.CustomerDialog_label_text);
		
		txtShort = new Text(container, SWT.BORDER);
		txtShort.setText("");
		txtShort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText(Messages.CustomerDialog_label_1_text);
		
		txtLong = new Text(container, SWT.BORDER);
		txtLong.setText("");
		txtLong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Messages.BTN_ADD,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				Messages.BTN_FINISH, false);
		initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(320, 204);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext dbc = new DataBindingContext();
		//
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(txtShort);
		IObservableValue model = PojoProperties.value("name").observe(customer);
		dbc.bindValue(target, model, null, null);
		//
		target = WidgetProperties.text(SWT.Modify).observe(txtLong);
		model = PojoProperties.value("invoice").observe(customer);
		dbc.bindValue(target, model, null, null);
		return dbc;
	}
}
