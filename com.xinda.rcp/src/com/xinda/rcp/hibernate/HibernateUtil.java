package com.xinda.rcp.hibernate;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.eclipse.core.runtime.Platform;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Folder;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.OrderItem;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.model.PaymentType;
import com.xinda.rcp.model.Product;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static SessionFactory sf;

	static {
		FileSystemView fsv = FileSystemView.getFileSystemView();

		Configuration config = new Configuration();
		File conf = new File(Platform.getConfigurationLocation().getURL()
				.getPath(), "hibernate.cfg.xml");
		System.out.println(conf.getAbsolutePath());
		if (!conf.exists()) {

			config.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
			config.setProperty(Environment.DIALECT,
					"org.hibernate.dialect.HSQLDialect");
			config.setProperty(Environment.USER, "sa");
			config.setProperty(Environment.URL, "jdbc:hsqldb:file:"
					+ fsv.getDefaultDirectory().getAbsolutePath() + "/Dropbox"
					+ "/db/xinda;create=true;shutdown=true");
			config.setProperty(Environment.SHOW_SQL, "true");
			config.setProperty(Environment.POOL_SIZE, "1");
			config.setProperty(Environment.HBM2DDL_AUTO, "update");

			config.addAnnotatedClass(Product.class);
			config.addAnnotatedClass(Customer.class);
			config.addAnnotatedClass(Payment.class);
			config.addAnnotatedClass(PaymentType.class);
			config.addAnnotatedClass(Invoice.class);
			// config.addAnnotatedClass(Balance.class);
			config.addAnnotatedClass(Order.class);
			config.addAnnotatedClass(OrderItem.class);
			config.addAnnotatedClass(Book.class);
			config.addAnnotatedClass(Folder.class);
		} else {
			config.configure(conf);
		}
		// SchemaExport se = new SchemaExport(registry, config);
		// se.setOutputFile("C:/update.sql");
		// se.create(true, true);

		sf = config.buildSessionFactory();

	}

	public static void shutdown() {
		sf.close();
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}

}