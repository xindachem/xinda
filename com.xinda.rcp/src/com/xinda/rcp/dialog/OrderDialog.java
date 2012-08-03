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
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wb.swt.ResourceManager;

import com.google.common.base.Preconditions;
import com.xinda.rcp.Messages;
import com.xinda.rcp.Models;
import com.xinda.rcp.builder.OrderItemBuilder;
import com.xinda.rcp.dao.BookDAO;
import com.xinda.rcp.editing.PriceEditing;
import com.xinda.rcp.editing.ProductEditing;
import com.xinda.rcp.editing.QuantityEditing;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Constants;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.OrderItem;

public class OrderDialog extends TitleAreaDialog {
	public final int PRODUCT = 0;
	public final int QUANTITY = 1;
	public final int PRICE = 2;
	public final int AMOUNT = 3;

	private Text txtId;
	private Order order;
	private TableViewer itemViewer;
	private ComboViewer bookViewer;
	private DateTime dtDate;
	private Text txtTotal;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public OrderDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.TITLE);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public OrderDialog(Shell shell, Order b) {
		this(shell);
		order = Preconditions.checkNotNull(b);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.DLG_BILL_TITLE);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText(Messages.LB_BILLNO);

		txtId = new Text(container, SWT.BORDER | SWT.RIGHT);
		txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText(Messages.LB_DATE);

		dtDate = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN);
		dtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_1.setText(Messages.LB_CUSTOMER);

		{
			bookViewer = new ComboViewer(container, SWT.READ_ONLY);
			Combo combo = bookViewer.getCombo();
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
					1, 1));
			combo.select(0);
		}

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_2.setText(Messages.LB_TOTAL);

		txtTotal = new Text(container, SWT.BORDER | SWT.READ_ONLY | SWT.RIGHT);
		txtTotal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		{
			Group grpDetail = new Group(container, SWT.SHADOW_IN);
			grpDetail.setLayout(new GridLayout(1, false));
			GridData gd_grpDetail = new GridData(SWT.FILL, SWT.FILL, true,
					true, 2, 1);
			gd_grpDetail.heightHint = 151;
			grpDetail.setLayoutData(gd_grpDetail);

			ToolBar toolBar = new ToolBar(grpDetail, SWT.FLAT | SWT.RIGHT);
			toolBar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
					false, 1, 1));

			ToolItem toolAdd = new ToolItem(toolBar, SWT.NONE);
			toolAdd.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					OrderItemBuilder builder = new OrderItemBuilder();
					OrderItem item = builder.getOrderItem();
					item.setOrder(order);
					order.getItems().add(item);
					itemViewer.setInput(order.getItems());
				}
			});

			toolAdd.setText(Messages.TOOL_ADD);
			toolAdd.setImage(ResourceManager.getPluginImage("com.xinda.rcp", "icons/Plus.png"));

			final ToolItem toolRemove = new ToolItem(toolBar, SWT.NONE);
			toolRemove.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					order.getItems().remove(
							(OrderItem) itemViewer.getElementAt(itemViewer
									.getTable().getSelectionIndex()));
					itemViewer.setInput(order.getItems());
				}
			});

			toolRemove.setEnabled(false);
			toolRemove.setImage(ResourceManager.getPluginImage("com.xinda.rcp",
					"icons/minus.png"));
			toolRemove.setText(Messages.TOOL_REMOVE);

			{
				itemViewer = new TableViewer(grpDetail, SWT.BORDER
						| SWT.FULL_SELECTION);
				itemViewer.setUseHashlookup(true);

				// Product
				TableViewerColumn col = new TableViewerColumn(itemViewer,
						SWT.NONE, PRODUCT);
				col.setLabelProvider(new ColumnLabelProvider() {
					private WorkbenchLabelProvider provider = new WorkbenchLabelProvider();

					@Override
					public String getText(Object element) {
						OrderItem item = (OrderItem) element;
						return provider.getText(item.getProduct());
					}

				});
				col.setEditingSupport(new ProductEditing(itemViewer));
				col.getColumn().setWidth(100);
				col.getColumn().setText(Messages.TBL_PRODUCT);
				col.getColumn().setAlignment(SWT.LEFT);

				// Quantity
				col = new TableViewerColumn(itemViewer, SWT.NONE, QUANTITY);
				col.setLabelProvider(new ColumnLabelProvider() {

					@Override
					public String getText(Object element) {
						OrderItem item = (OrderItem) element;
						return item.getQuantity()
								.setScale(Constants.QUANTITY_SCALE).toString();
					}

				});
				col.setEditingSupport(new QuantityEditing(itemViewer));
				col.getColumn().setWidth(100);
				col.getColumn().setText(Messages.TBL_QUANTITY);
				col.getColumn().setAlignment(SWT.RIGHT);

				// Price
				col = new TableViewerColumn(itemViewer, SWT.NONE, PRICE);
				col.setLabelProvider(new ColumnLabelProvider() {

					@Override
					public String getText(Object element) {
						OrderItem item = (OrderItem) element;
						return item.getPrice().setScale(Constants.PRICE_SCALE)
								.toString();
					}

				});
				col.setEditingSupport(new PriceEditing(itemViewer));
				col.getColumn().setWidth(100);
				col.getColumn().setText(Messages.TBL_PRICE);
				col.getColumn().setAlignment(SWT.RIGHT);

				// Amount
				col = new TableViewerColumn(itemViewer, SWT.NONE, AMOUNT);
				col.setLabelProvider(new ColumnLabelProvider() {

					@Override
					public String getText(Object element) {
						OrderItem item = (OrderItem) element;
						return item.getAmount()
								.setScale(Constants.AMOUNT_SCALE).toString();
					}
				});
				col.getColumn().setWidth(100);
				col.getColumn().setText(Messages.TBL_AMOUNT);
				col.getColumn().setAlignment(SWT.RIGHT);

				// itemViewer.setLabelProvider(new ItemTableLabelProvider());
				itemViewer.setContentProvider(ArrayContentProvider
						.getInstance());
				itemViewer.setInput(order.getItems());
				itemViewer.getTable().setLayoutData(
						new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
				itemViewer.getTable().setHeaderVisible(true);
				itemViewer.getTable().setLinesVisible(true);
			}
		}
		initDataBindings();
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Messages.BTN_ADD, true);
		createButton(parent, IDialogConstants.CANCEL_ID, Messages.BTN_FINISH,
				false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(453, 435);
	}

	protected void initDataBindings() {
		BookDAO book = BookDAO.class.cast(Models.getDAO(Book.class));
		ViewerSupport.bind(bookViewer, new WritableList(book.findAll(),
				Book.class), PojoProperties.value(Book.class, "customer.name"));
		DataBindingContext dbc = new DataBindingContext();
		//
		IObservableValue target;
		IObservableValue model;
		//
		target = ViewersObservables.observeSingleSelection(bookViewer);
		model = PojoProperties.value("book").observe(order);
		dbc.bindValue(target, model);
		//
		target = WidgetProperties.selection().observe(dtDate);
		model = PojoProperties.value("date").observe(order);
		dbc.bindValue(target, model);
		//
		target = WidgetProperties.text(SWT.Modify).observe(txtId);
		model = PojoProperties.value("number").observe(order);
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
		//
	}
}
