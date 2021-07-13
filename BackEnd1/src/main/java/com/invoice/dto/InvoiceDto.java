package com.invoice.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.invoice.entities.File;
import com.invoice.entities.Item;
import com.invoice.entities.User;

public class InvoiceDto {
	private Long id;

	private LocalDateTime date_created;

	private LocalDateTime due_date;

	private String type;

	private String company;

	private Set<ItemDto> items;
	@JsonBackReference
	private Set<File> files;

//	private UserDto user;
	private Long userid;

	public InvoiceDto(Long id, LocalDateTime date_created, LocalDateTime due_date, String type, String company,
			Set<ItemDto> items, Set<File> files) {
		super();
		this.id = id;
		this.date_created = date_created;
		this.due_date = due_date;
		this.type = type;
		this.company = company;
		this.items = items;
		this.files = files;
	}

	public InvoiceDto() {

	}

//	public UserDto getUser() {
//		return user;
//	}
//
//	public void setUser(UserDto user) {
//		this.user = user;
//	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

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

	@Override
	public String toString() {
		return "InvoiceDto [id=" + id + ", date_created=" + date_created + ", due_date=" + due_date + ", type=" + type
				+ ", company=" + company + ", items=" + items + ", files=" + files + ", userid=" + userid + "]";
	}

}
