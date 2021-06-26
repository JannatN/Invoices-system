package com.invoice.entities;


import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "date")
	private String date;


	public Invoice(int id, String type, String date) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
	}


	public Invoice() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Invoice [id=" + id + ", type=" + type + ", date=" + date + "]";
	}


}
