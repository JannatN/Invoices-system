package com.invoice.entities;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "invoices")
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
	@NotBlank
	@Size(max = 20)
	private String type;

	@NotBlank
	private String company;
	
	@OneToMany(mappedBy = "invoiceID", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Item> items;
	
	@ManyToOne
	@JoinColumn(name = "userID", insertable = false, updatable = false)
	private User user;


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



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}