package com.xinda.rcp.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Preconditions;
import com.xinda.rcp.Messages;
import com.xinda.rcp.Models;
import com.xinda.rcp.dao.BookDAO;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Invoice;

public class InvoiceDialog extends TitleAreaDialog {
	private Text txtId;
	private Text txtAmount;
	private Invoice invoice;
	private DateTime dtDate;
	private ComboViewer bookViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public InvoiceDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public InvoiceDialog(Shell parentShell, Invoice invoice) {
		this(parentShell);
		Preconditions.checkNotNull(invoice);
		this.invoice = invoice;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_INVOICE_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(container, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText(Messages.LB_INVOICENO);

		txtId = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_1.setText(Messages.LB_DATE);

		dtDate = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN);
		dtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_2.setText(Messages.LB_INVOICENAME);

		bookViewer = new ComboViewer(container, SWT.READ_ONLY);
		Combo combo = bookViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		combo.select(0);

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_3.setText(Messages.LB_AMOUNT);

		txtAmount = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtAmount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));

		initDataBingings();

		return area;
	}

	private void initDataBingings() {
		// Book
		BookDAO book = BookDAO.class.cast(Models.getDAO(Book.class));
		ViewerSupport.bind(bookViewer, new WritableList(book.findAll(),
				Book.class), PojoProperties.value(Book.class, "customer.name"));

		DataBindingContext dbc = new DataBindingContext();
		IObservableValue model;
		IObservableValue target;
		// Id
		model = PojoProperties.value("id").observe(invoice);
		target = WidgetProperties.text(SWT.Modify).observe(txtId);
		UpdateValueStrategy update = new UpdateValueStrategy();
		update.setConverter(new IConverter() {

			@Override
			public Object getFromType() {
				return Long.class;
			}

			@Override
			public Object getToType() {
				return String.class;
			}

			@Override
			public Object convert(Object fromObject) {
				return fromObject.toString();
			}
		});
		dbc.bindValue(target, model, null, update);

		// Amount
		model = PojoProperties.value("amount").observe(invoice);
		target = WidgetProperties.text(SWT.Modify).observe(txtAmount);
		dbc.bindValue(target, model);
		// Date
		model = PojoProperties.value("date").observe(invoice);
		target = WidgetProperties.selection().observe(dtDate);
		dbc.bindValue(target, model);
		// Customer
		model = PojoProperties.value("book").observe(invoice);
		target = ViewersObservables.observeSingleSelection(bookViewer);
		dbc.bindValue(target, model);
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
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(260, 261);
	}

}
