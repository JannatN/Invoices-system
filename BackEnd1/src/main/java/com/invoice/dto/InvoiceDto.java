package com.invoice.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.invoice.entities.Item;
import com.invoice.entities.User;

public class InvoiceDto {

	@Id
	private Long id;

	private Date date_created;

	private Date due_date;

	private String type;

	private String company;

	private Set<ItemDto> items;

//	private User user;

	private Long userid;

	public InvoiceDto(Long id, Date date_created, Date due_date, String type, String company,
			Set<ItemDto> items) {
		super();
		this.id = id;
		this.date_created = date_created;
		this.due_date = due_date;
		this.type = type;
		this.company = company;
		this.items = items;
	}

	public InvoiceDto() {

	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
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

	public Set<ItemDto> getItems() {
		return items;
	}

	public void setItems(Set<ItemDto> items) {
		this.items = items;
	}

}
