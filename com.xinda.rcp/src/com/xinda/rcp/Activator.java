package com.xinda.rcp;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.xinda.rcp.adapter.BalanceAdapterFactory;
import com.xinda.rcp.adapter.BookAdapterFactory;
import com.xinda.rcp.adapter.CustomerAdapterFactory;
import com.xinda.rcp.adapter.FolderAdapterFactory;
import com.xinda.rcp.adapter.InvoiceAdapterFactory;
import com.xinda.rcp.adapter.ModelAdapterFactory;
import com.xinda.rcp.adapter.OrderAdapterFactory;
import com.xinda.rcp.adapter.OrderItemAdapterFactory;
import com.xinda.rcp.adapter.PaymentAdapterFactory;
import com.xinda.rcp.adapter.ProductAdapterFactory;
import com.xinda.rcp.hibernate.HibernateUtil;
import com.xinda.rcp.model.Balance;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Folder;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.model.Model;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.OrderItem;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.model.Product;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.xinda.rcp"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Platform.getAdapterManager().registerAdapters(
				new ModelAdapterFactory(), Model.class);
		Platform.getAdapterManager().registerAdapters(
				new CustomerAdapterFactory(), Customer.class);
		Platform.getAdapterManager().registerAdapters(
				new ProductAdapterFactory(), Product.class);
		Platform.getAdapterManager().registerAdapters(
				new OrderAdapterFactory(), Order.class);
		Platform.getAdapterManager().registerAdapters(
				new PaymentAdapterFactory(), Payment.class);
		Platform.getAdapterManager().registerAdapters(
				new OrderItemAdapterFactory(), OrderItem.class);
		Platform.getAdapterManager().registerAdapters(
				new InvoiceAdapterFactory(), Invoice.class);
		Platform.getAdapterManager().registerAdapters(
				new FolderAdapterFactory(), Folder.class);
		Platform.getAdapterManager().registerAdapters(new BookAdapterFactory(),
				Book.class);
		Platform.getAdapterManager().registerAdapters(
				new BalanceAdapterFactory(), Balance.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		HibernateUtil.shutdown();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static void log(Object obj) {
		getDefault().getLog().log(
				new Status(IStatus.INFO, Activator.PLUGIN_ID, obj.toString()));
	}
}
