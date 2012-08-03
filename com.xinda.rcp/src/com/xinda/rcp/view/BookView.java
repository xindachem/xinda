package com.xinda.rcp.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.xinda.rcp.Messages;
import com.xinda.rcp.Models;
import com.xinda.rcp.adapter.ICalendarProvider;
import com.xinda.rcp.adapter.WorkbenchTableContentProvider;
import com.xinda.rcp.adapter.WorkbenchTableLabelProvider;
import com.xinda.rcp.dao.BalanceDAO;
import com.xinda.rcp.dao.InvoiceDAO;
import com.xinda.rcp.dao.OrderDAO;
import com.xinda.rcp.dao.PaymentDAO;
import com.xinda.rcp.dialog.DialogFactory;
import com.xinda.rcp.model.Balance;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.util.Selections;

public class BookView extends ViewPart {

	public static final String ID = "com.xinda.rcp.view.book"; //$NON-NLS-1$

	public static final int ICON = 0;
	public static final int DATE = 1;
	public static final int NUMBER = 2;
	// public static final int CUSTOMER = 2;
	public static final int PRODUCT = 3;
	public static final int QUANTITY = 4;
	public static final int PRICE = 5;
	public static final int AMOUNT = 6;

	private TreeViewer recordViewer;

	protected Book book;

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));

		recordViewer = new TreeViewer(container, SWT.BORDER);
		recordViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event
						.getSelection();
				if (sel == null)
					return;
				Object item = sel.getFirstElement();
				if (item == null)
					return;
				DialogFactory factory = new DialogFactory();
				Dialog dialog = factory.create(item);
				if (dialog == null)
					return;
				if (dialog.open() == Window.OK) {
					Models.save(item);
				}
			}
		});

		recordViewer.setUseHashlookup(true);
		// recordViewer.setAutoExpandLevel(2);
		Tree tree = recordViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		TreeColumn col;
		int width = 150;
		//
		col = new TreeColumn(tree, SWT.NONE, ICON);
		col.setWidth(100);
		col.setAlignment(SWT.CENTER);
		//
		col = new TreeColumn(tree, SWT.NONE, DATE);
		col.setText(Messages.TBL_DATE);
		col.setWidth(width);
		col.setAlignment(SWT.LEFT);
		//
		col = new TreeColumn(tree, SWT.NONE, NUMBER);
		col.setText(Messages.TBL_ID);
		col.setWidth(width);
		col.setAlignment(SWT.RIGHT);
		//
		// col = new TreeColumn(tree, SWT.NONE, CUSTOMER);
		// col.setText(Messages.TBL_CUSTOMER);
		// col.setWidth(width);
		// col.setAlignment(SWT.LEFT);
		//
		col = new TreeColumn(tree, SWT.NONE, PRODUCT);
		col.setText(Messages.TBL_PRODUCT);
		col.setWidth(width);
		col.setAlignment(SWT.LEFT);
		//
		col = new TreeColumn(tree, SWT.NONE, QUANTITY);
		col.setText(Messages.TBL_QUANTITY);
		col.setWidth(width);
		col.setAlignment(SWT.RIGHT);
		//
		col = new TreeColumn(tree, SWT.NONE, PRICE);
		col.setText(Messages.TBL_PRICE);
		col.setWidth(width);
		col.setAlignment(SWT.RIGHT);
		//
		col = new TreeColumn(tree, SWT.NONE, AMOUNT);
		col.setText(Messages.TBL_AMOUNT);
		col.setWidth(width);
		col.setAlignment(SWT.RIGHT);

		// recordViewer.addFilter(new ViewerFilter() {
		//
		// @Override
		// public boolean select(Viewer viewer, Object parentElement,
		// Object element) {
		// ICustomerProvider provider = (ICustomerProvider) Platform
		// .getAdapterManager().getAdapter(element,
		// ICustomerProvider.class);
		// if (provider == null || book == null)
		// return true;
		// if (provider.getCustomer(element).equals(book.getCustomer()))
		// return true;
		// return false;
		// }
		// });

		recordViewer.setComparator(new ViewerComparator() {

			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				ICalendarProvider provider1 = (ICalendarProvider) Platform
						.getAdapterManager().getAdapter(e1,
								ICalendarProvider.class);
				ICalendarProvider provider2 = (ICalendarProvider) Platform
						.getAdapterManager().getAdapter(e2,
								ICalendarProvider.class);
				if (provider1 == null || provider2 == null)
					return 0;
				Calendar c1 = provider1.getDate(e1);
				Calendar c2 = provider2.getDate(e2);
				int ret = c1.compareTo(c2);
				if (0 == ret) {
					if (e1 instanceof Balance)
						return 1;
					if (e2 instanceof Balance)
						return -1;
				}
				return ret;
			}

		});

		recordViewer.setLabelProvider(new WorkbenchTableLabelProvider());
		recordViewer.setContentProvider(new WorkbenchTableContentProvider());

		getSite().setSelectionProvider(recordViewer);

		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(new ISelectionListener() {

					@Override
					public void selectionChanged(IWorkbenchPart part,
							ISelection selection) {
						Object sel = Selections.selectionToObject(selection);
						if (sel != null && sel instanceof Book) {
							book = (Book) sel;
							setPartName(book.getCustomer().getName());
							setInput();
						}
					}
				});

		Models.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getPropertyName() == Order.class.getName()
						|| event.getPropertyName() == Payment.class.getName()
						|| event.getPropertyName() == Invoice.class.getName()
						|| event.getPropertyName() == Balance.class.getName()) {
					setInput();
				}
			}
		});
	}

	protected void refresh() {
		recordViewer.refresh();
	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void setFocus() {
		recordViewer.getTree().setFocus();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] getRecords() {
		OrderDAO order = OrderDAO.class.cast(Models.getDAO(Order.class));
		PaymentDAO payment = PaymentDAO.class
				.cast(Models.getDAO(Payment.class));
		InvoiceDAO invoice = InvoiceDAO.class
				.cast(Models.getDAO(Invoice.class));
		BalanceDAO balance = BalanceDAO.class.cast(Models
				.getDAO(Balance.class));
		List records = new ArrayList();
		records.addAll(order.findByBook(book));
		records.addAll(payment.findByBook(book));
		records.addAll(invoice.findByBook(book));
		records.addAll(balance.findByBook(book));
		return records.toArray();
	}

	/**
	 * 
	 */
	protected void setInput() {
		recordViewer.setInput(getRecords());
	}
}
