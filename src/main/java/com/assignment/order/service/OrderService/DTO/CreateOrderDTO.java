package com.assignment.order.service.OrderService.DTO;

public class CreateOrderDTO{
	
    private Long bookId;
    private int quantity;
    
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CreateOrderDTO(Long bookId, int quantity) {
		super();
		this.bookId = bookId;
		this.quantity = quantity;
	}
	public CreateOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
