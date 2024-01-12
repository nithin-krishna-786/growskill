package com.alippo.growskill.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Order Tracking Number")
	private String orderTrackingNumber;
	
	@Column(name = "Total Quantity")
	private int totalQuantity;
	
	@Column(name = "Total Price")
	private BigDecimal totalPrice;
	
	@Column(name = "Status")
	private String status;

	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdated;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Address billingAddress;

	public Order() {
	}
}
