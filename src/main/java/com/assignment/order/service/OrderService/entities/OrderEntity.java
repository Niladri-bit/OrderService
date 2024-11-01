package com.assignment.order.service.OrderService.entities;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders") 
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;
    
    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_mail", nullable = false)
    private String userMail;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public LocalDateTime getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(LocalDateTime canceledAt) {
		this.canceledAt = canceledAt;
	}

	public OrderEntity(Long id, Long bookId, String bookName, int quantity, float totalPrice, LocalDateTime orderedAt,
			Long userId, String userName, String userMail, LocalDateTime canceledAt) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.bookName = bookName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.orderedAt = orderedAt;
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.canceledAt = canceledAt;
	}

	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	} 

    
}
