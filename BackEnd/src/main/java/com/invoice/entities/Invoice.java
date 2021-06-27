package com.invoice.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "invoice", uniqueConstraints = { @UniqueConstraint(columnNames = "userID") })
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private Long userID;
	@NotBlank
	private Date dateCreated;

	@NotBlank
	private Date dueDate;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "invoice_item", joinColumns = @JoinColumn(name = "invoice_ID"), inverseJoinColumns = @JoinColumn(name = "item_ID"))
	private Set<Item> items = new HashSet<>();

	public Invoice(Long userID, Date dateCreated, Date dueDate) {
		this.userID = userID;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
	}

	public Invoice() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
