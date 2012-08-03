package com.xinda.rcp.dao;

import java.util.HashMap;
import java.util.Map;

import com.xinda.rcp.model.Balance;
import com.xinda.rcp.model.Book;
import com.xinda.rcp.model.Customer;
import com.xinda.rcp.model.Folder;
import com.xinda.rcp.model.Invoice;
import com.xinda.rcp.model.Order;
import com.xinda.rcp.model.Payment;
import com.xinda.rcp.model.PaymentType;
import com.xinda.rcp.model.Product;

public class DefaultDAOFactory implements IDAOFactory {

	private static DefaultDAOFactory instance = new DefaultDAOFactory();

	private Map<Class<?>, IDAO<?, Long>> daos = new HashMap<Class<?>, IDAO<?, Long>>();

	private DefaultDAOFactory() {
		daos.put(Order.class, new OrderDAO());
		daos.put(Payment.class, new PaymentDAO());
		daos.put(Invoice.class, new InvoiceDAO());
		daos.put(Book.class, new BookDAO());
		daos.put(Folder.class, new FolderDAO());
		daos.put(Customer.class, new CustomerDAO());
		daos.put(Product.class, new ProductDAO());
		daos.put(PaymentType.class, new PaymentTypeDAO());
		daos.put(Balance.class, new BalanceDAO());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> IDAO<T, Long> getDAO(Class<T> clazz) {
		return (IDAO<T, Long>) daos.get(clazz);
	}

	public static DefaultDAOFactory getInstance() {
		return instance;
	}

}
