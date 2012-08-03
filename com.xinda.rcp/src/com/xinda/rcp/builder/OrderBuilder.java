package com.xinda.rcp.builder;

import java.util.Calendar;

import com.xinda.rcp.model.Order;

public class OrderBuilder {
	private Order order;

	public OrderBuilder() {
		order = new Order();
		order.setId(null);
		order.setDate(Calendar.getInstance().getTime());
		order.setBook((new BookBuilder()).getBook());
	}

	public Order getOrder() {
		return order;
	}

}
