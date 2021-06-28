//package com.invoice.entities;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import org.hibernate.annotations.CreationTimestamp;
//import javax.persistence.Entity;
//
//@Entity
//@Table(name = "invoice", uniqueConstraints = { @UniqueConstraint(columnNames = "userID") })
//public class Invoice {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
////	@JoinColumn(name = "userID", referencedColumnName = "id")
////	@ManyToOne(targetEntity = User.class)
//	private Long userID;
//
//	@NotBlank
//	@CreationTimestamp
//	private LocalDateTime dateCreated;
//
//	@NotBlank
//	private LocalDateTime dueDate;
//
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "invoice_item", joinColumns = @JoinColumn(name = "invoice_ID"), inverseJoinColumns = @JoinColumn(name = "item_ID"))
//	private Set<Item> items = new HashSet<>();
//
//	public Invoice(Long userID, LocalDateTime dateCreated, LocalDateTime dueDate) {
//		this.userID = userID;
//		this.dateCreated = dateCreated;
//		this.dueDate = dueDate;
//	}
//
//	public Invoice() {
//
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getUserID() {
//		return userID;
//	}
//
//	public void setUserID(Long userID) {
//		this.userID = userID;
//	}
//
//	public LocalDateTime getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(LocalDateTime dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//
//	public LocalDateTime getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(LocalDateTime dueDate) {
//		this.dueDate = dueDate;
//	}
//
//	public Set<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(Set<Item> items) {
//		this.items = items;
//	}
//
//}
package com.invoice.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userID;
	@NotNull(message = "Please enter Date")
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@NotNull(message = "Please enter Date")
	private LocalDateTime dueDate;

	@ManyToOne
	@JoinColumn(name = "userID", insertable = false, updatable = false)
	private User user;
//	 private Set<User> recordings = new HashSet<>();

//	@ManyToOne(optional=false)
//	@JoinColumn(name="userID",referencedColumnName="id", insertable=false, updatable=false)
//	private User user;

//	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<User> recordings = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "invoice_item", joinColumns = @JoinColumn(name = "invoice_ID"), inverseJoinColumns = @JoinColumn(name = "item_ID"))
	private Set<Item> items = new HashSet<>();

	public Invoice(Long userID, LocalDateTime dateCreated, LocalDateTime dueDate) {
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

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
