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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
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
import com.xinda.rcp.dao.PaymentTypeDAO;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.model.PaymentType;

public class PaymentDialog extends TitleAreaDialog {
	private Text txtBillId;
	private Text txtAmount;
	private Text txtNo;
	private Payment payment;
	private ComboViewer bookViewer;
	private DateTime dtDate;
	private ComboViewer typeViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public PaymentDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public PaymentDialog(Shell parentShell, Payment p) {
		this(parentShell);
		payment = Preconditions.checkNotNull(p);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_PAYMENT_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText(Messages.LB_CUSTOMER);

		bookViewer = new ComboViewer(container, SWT.READ_ONLY);
		Combo combo = bookViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		combo.select(0);

		Label label_5 = new Label(container, SWT.NONE);
		label_5.setAlignment(SWT.RIGHT);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_5.setText(Messages.LB_DATE);

		dtDate = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN);
		dtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_1.setText(Messages.LB_BILLNO);

		txtBillId = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtBillId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtBillId.getText() == "")
					payment.setOrderId(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (payment.getOrderId() == null)
					payment.setOrderId(0L);
			}
		});
		txtBillId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_2.setText(Messages.LB_AMOUNT);

		txtAmount = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtAmount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_3.setText(Messages.LB_PAYMENTTYPE);

		typeViewer = new ComboViewer(container, SWT.READ_ONLY);
		Combo combo_1 = typeViewer.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_4 = new Label(container, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_4.setText(Messages.LB_NO);

		txtNo = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtNo.getText() == "")
					payment.setNoteId(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (payment.getNoteId() == null)
					payment.setNoteId(0L);
			}
		});
		txtNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		initDataBindings();

		return area;
	}

	protected void initDataBindings() {
		BookDAO book = BookDAO.class.cast(Models.getDAO(Book.class));
		ViewerSupport.bind(bookViewer, new WritableList(book.findAll(),
				Book.class), PojoProperties.value(Book.class, "customer.name"));

		PaymentTypeDAO paymentType = PaymentTypeDAO.class.cast(Models
				.getDAO(PaymentType.class));
		ViewerSupport.bind(typeViewer, new WritableList(paymentType.findAll(),
				PaymentType.class), PojoProperties.value(PaymentType.class,
				"name"));

		DataBindingContext dbc = new DataBindingContext();
		IObservableValue model;
		IObservableValue target;
		UpdateValueStrategy update = new UpdateValueStrategy();
		update.setConverter(new IConverter() {

			@Override
			public Object getFromType() {
				return Integer.class;
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
		// Date
		model = PojoProperties.value("date").observe(payment);
		target = WidgetProperties.selection().observe(dtDate);
		dbc.bindValue(target, model);
		// Amount
		model = PojoProperties.value("amount").observe(payment);
		target = WidgetProperties.text(SWT.Modify).observe(txtAmount);
		dbc.bindValue(target, model);
		// Note Id
		model = PojoProperties.value("note_id").observe(payment);
		target = WidgetProperties.text(SWT.Modify).observe(txtNo);
		dbc.bindValue(target, model, null, update);
		// Customer
		model = PojoProperties.value("book").observe(payment);
		target = ViewersObservables.observeSingleSelection(bookViewer);
		dbc.bindValue(target, model);
		// Type
		model = PojoProperties.value("type").observe(payment);
		target = ViewersObservables.observeSingleSelection(typeViewer);
		dbc.bindValue(target, model);
		// Bill
		model = PojoProperties.value("bill_id").observe(payment);
		target = WidgetProperties.text(SWT.Modify).observe(txtBillId);
		dbc.bindValue(target, model, null, update);

	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button_1 = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		button_1.setText(Messages.BTN_ADD);
		Button button = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button.setText(Messages.BTN_FINISH);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(260, 315);
	}

}
