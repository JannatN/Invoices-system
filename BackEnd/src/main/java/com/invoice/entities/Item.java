package com.invoice.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long invoice_id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@NotBlank
	@Size(max = 200)
	private String description;

	private Double price;

	@NotBlank
	@Size(max = 3)
	private String currency;

	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id", insertable = false, updatable = false)
	private Invoice invoice;

	public Item() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}