package com.alippo.growskill.entities;

import lombok.*;

import com.alippo.growskill.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Total Quantity")
	private Integer totalQuantity;

	@Column(name = "Total Price")
	private BigDecimal totalPrice;

	@Column(name = "Status")
	private OrderStatus orderStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;

	public Order() {

	}
}
