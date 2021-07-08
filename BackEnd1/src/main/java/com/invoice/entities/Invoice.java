
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
@Table(name = "invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userID;
	@CreationTimestamp
	private LocalDateTime date_created;
	@NotNull(message = "Please enter Date")
	private LocalDateTime due_date;
	@NotBlank
	@Size(max = 20)
	private String type;

	@NotBlank
	private String company;
	
	
	@ManyToOne
	@JoinColumn(name = "userID", insertable = false, updatable = false)
	private User user;

	
	@OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "invoiceid", referencedColumnName = "id")
	private Set<Item> items;
	
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "file_id", referencedColumnName = "id")
	    private FileDB file;
	

	public Invoice(Long userID, LocalDateTime dateCreated, LocalDateTime dueDate) {
		this.userID = userID;
		this.date_created = dateCreated;
		this.due_date = dueDate;
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
		return date_created;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.date_created = dateCreated;
	}

	public LocalDateTime getDueDate() {
		return due_date;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.due_date = dueDate;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
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

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", userID=" + userID + ", date_created=" + date_created + ", due_date=" + due_date
				+ ", type=" + type + ", company=" + company + ", user=" + user + ", items=" + items + ", file=" + file
				+ "]";
	}

	
}
