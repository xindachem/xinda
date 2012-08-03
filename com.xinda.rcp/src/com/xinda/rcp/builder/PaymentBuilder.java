package com.xinda.rcp.builder;

import java.math.BigDecimal;
import java.util.Calendar;

import com.xinda.rcp.model.Payment;

public class PaymentBuilder {
	private Payment payment;

	public PaymentBuilder() {
		payment = new Payment();
		payment.setId(null);
		payment.setOrderId(null);
		payment.setType(null);
		payment.setBook((new BookBuilder()).getBook());
		payment.setDate(Calendar.getInstance().getTime());
		payment.setNoteId(null);
		payment.setAmount(BigDecimal.ZERO);
	}

	public Payment getPayment() {
		return payment;
	}
}
