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

//	private Long invoiceID;

	@ManyToOne
	@JoinColumn(name = "invoiceID")
	private Invoice invoice;

	public Item() {

	}

//	public Item(Long invoiceID) {
//		this.invoiceID = invoiceID;
//
//	}

	public Item(String name, String description, Double price, String currency, Integer quantity) {
//		this.invoiceID = invoiceID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.currency = currency;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

//	public Long getInvoiceID() {
//		return invoiceID;
//	}
//
//	public void setInvoiceID(Long invoiceID) {
//		this.invoiceID = invoiceID;
//	}

//	public Invoice getInvoice() {
//		return invoice;
//	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", currency="
				+ currency + ", quantity=" + quantity + ", invoiceID="  + ", invoice=" + invoice + "]";
	}

}