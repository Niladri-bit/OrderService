package com.assignment.order.service.OrderService.exceptions;

public class InvalidQuantityException extends RuntimeException{


	private static final long serialVersionUID = 5971099665363357160L;
	
	public InvalidQuantityException(String message) {
        super(message);
    }

}
