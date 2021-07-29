
package com.invoice.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userID;
	@CreationTimestamp
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime date_created;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime due_date;
	@NotBlank
	@Size(max = 20)
	private String type;

	@NotBlank
	private String company;

	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "invoiceid", referencedColumnName = "id")
	private List<Item> items;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "invoiceid", referencedColumnName = "id")
	private List<File> files;

	public Invoice(Long id, LocalDateTime date_created, LocalDateTime due_date,
			@NotBlank @Size(max = 20) String type, @NotBlank String company, User user, List<Item> items, List<File> files) {
		super();
		this.id = id;
//		this.userID = userID;
		this.date_created = date_created;
		this.due_date = due_date;
		this.type = type;
		this.company = company;
		this.user = user;
		this.items = items;
		this.files= files;
	}

	public Invoice() {

	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public LocalDateTime getDate_created() {
		return date_created;
	}

	public void setDate_created(LocalDateTime date_created) {
		this.date_created = date_created;
	}

	public LocalDateTime getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDateTime due_date) {
		this.due_date = due_date;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
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
		return "Invoice [id=" + id + ", date_created=" + date_created + ", due_date=" + due_date
				+ ", type=" + type + ", company=" + company + ", user=" + user + ", items=" + items + ", file=" + files
				+ "]";
	}

}
