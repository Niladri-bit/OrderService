package com.assignment.order.service.OrderService.utils;

import com.assignment.order.service.OrderService.exceptions.InvalidQuantityException;

public class Util {

	public static Boolean validateOrderQuantity(int requestedQuantity,int inStockQuantity) {
		if (requestedQuantity<inStockQuantity) {
			return true;
		}
		else {
			throw new InvalidQuantityException("Order requested for book is more than in stock availability");
		}
	}
	
	public static Float calculateTotalPriceOfOrder(int quantity,Float price) {
		return quantity*price;
	}
}
