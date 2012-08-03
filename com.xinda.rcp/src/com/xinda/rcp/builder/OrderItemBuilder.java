package com.xinda.rcp.builder;

import java.math.BigDecimal;

import com.xinda.rcp.model.OrderItem;

public class OrderItemBuilder {
	private OrderItem item;

	public OrderItemBuilder() {
		item = new OrderItem();
		item.setOrder(null);
		item.setProduct((new ProductBuilder()).getProduct());
		item.setQuantity(BigDecimal.ZERO);
		item.setPrice(BigDecimal.ZERO);
	}

	public OrderItem getOrderItem() {
		return item;
	}

}
