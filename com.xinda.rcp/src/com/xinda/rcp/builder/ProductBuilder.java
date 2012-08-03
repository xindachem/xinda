package com.xinda.rcp.builder;

import com.google.common.base.Strings;
import com.xinda.rcp.model.Product;

public class ProductBuilder {
	private Product product;
	
	public ProductBuilder() {
		product = new Product();
		product.setId(null);
		product.setName(Strings.nullToEmpty(null));
		product.setCategory(Strings.nullToEmpty(null));
	}
	
	public Product getProduct() {
		return product;
	}

}
