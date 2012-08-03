package com.xinda.rcp.builder;

import com.xinda.rcp.model.Book;

public class BookBuilder {

	private Book book;

	public BookBuilder() {
		book = new Book();
		book.setCustomer(null);
	}

	public Book getBook() {
		return book;
	}

}
