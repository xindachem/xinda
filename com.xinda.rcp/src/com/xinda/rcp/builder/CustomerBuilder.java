package com.xinda.rcp.builder;

import com.google.common.base.Strings;
import com.xinda.rcp.model.Customer;

public class CustomerBuilder {
	private Customer customer;
	
	public CustomerBuilder() {
		customer = new Customer();
		customer.setId(null);
		customer.setName(Strings.nullToEmpty(null));
		customer.setInvoice(Strings.nullToEmpty(null));
	}
	
	public Customer build() {
		return customer;
	}

}
