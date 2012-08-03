package com.xinda.rcp.adapter;

import java.math.BigDecimal;
import java.util.Calendar;

import com.xinda.rcp.builder.BookBuilder;
import com.xinda.rcp.model.Invoice;

public class InvoiceBuilder {

	private Invoice invoice;

	public InvoiceBuilder() {
		invoice = new Invoice();
		invoice.setId(null);
		invoice.setBook((new BookBuilder()).getBook());
		invoice.setDate(Calendar.getInstance().getTime());
		invoice.setAmount(BigDecimal.ZERO);
	}

	public Invoice getInvoice() {
		return invoice;
	}

}
