package com.assignment.order.service.OrderService.exceptions;

public class BookNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522468147263307460L;

	public BookNotFoundException(String message) {
        super(message);
    }
}
