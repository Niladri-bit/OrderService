package com.assignment.order.service.OrderService.DTO;

import java.time.LocalDateTime;

public class OrderResponseDTO {

	private Long id;
	private Long bookId;
    private String bookName;
    private int quantity;
    private float totalPrice;
    private LocalDateTime orderedAt;
    private String userName;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}
	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public OrderResponseDTO(Long id, Long bookId, String bookName, int quantity, float totalPrice,
			LocalDateTime orderedAt, String userName) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.bookName = bookName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.orderedAt = orderedAt;
		this.userName = userName;
	}
	public OrderResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
}
