package com.xinda.rcp.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.xinda.rcp.Messages;
import com.xinda.rcp.model.Folder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class FolderDialog extends TitleAreaDialog {
	private Text text;
	private Folder catalog;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FolderDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public FolderDialog(Shell shell, Folder catalog) {
		this(shell);
		this.catalog = catalog;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_CATALOG_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.grabExcessHorizontalSpace = false;
		gd_container.grabExcessVerticalSpace = false;
		container.setLayoutData(gd_container);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText(Messages.CatalogDialog_lblNewLabel_text);
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
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
		return new Point(260, 172);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue model = PojoProperties.value("name").observe(catalog);
		bindingContext.bindValue(target, model, null, null);
		//
		return bindingContext;
	}
}
