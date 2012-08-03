package com.xinda.rcp;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.xinda.rcp.messages"; //$NON-NLS-1$
	public static String TTL_PAYMENT;
	public static String TXT_BOOK;
	public static String DLG_BILL_TITLE;
	public static String LB_CUSTOMER;
	public static String LB_BILLNO;
	public static String LB_DATE;
	public static String LB_TOTAL;
	public static String TBL_DATE;
	public static String TBL_ID;
	public static String TBL_CUSTOMER;
	public static String TBL_PRODUCT;
	public static String TBL_QUANTITY;
	public static String TBL_PRICE;
	public static String TBL_AMOUNT;
	public static String BTN_ADD;
	public static String BTN_FINISH;
	public static String DLG_INVOICE_TITLE;
	public static String DLG_PAYMENT_TITLE;
	public static String LB_AMOUNT;
	public static String LB_PAYMENTTYPE;
	public static String LB_NO;
	public static String LB_INVOICENO;
	public static String LB_INVOICENAME;
	public static String TOOL_REMOVE;
	public static String TOOL_ADD;
	public static String TBL_BILLID;
	public static String BookView_lblNewLabel_text;
	public static String BookView_lblNewLabel_1_text;
	public static String TXT_PAYMENT;
	public static String TXT_INVOICE;
	public static String TXT_BILL;
	public static String TXT_BALANCE;
	public static String DLG_CUSTOMER_TITLE;
	public static String CustomerDialog_label_text;
	public static String CustomerDialog_label_1_text;
	public static String WORKBENCH_TITLE;
	public static String DLG_CATALOG_TITLE;
	public static String DLG_BOOK_TITLE;
	public static String CatalogDialog_button_1_text;
	public static String CatalogDialog_tltmNewItem_3_text;
	public static String CatalogDialog_lblNewLabel_text;
	public static String BookDialog_lblNewLabel_text;
	public static String BookDialog_lblNewLabel_1_text;
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private Messages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Class initialization
	//
	////////////////////////////////////////////////////////////////////////////
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
