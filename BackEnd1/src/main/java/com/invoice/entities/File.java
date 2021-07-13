package com.invoice.entities;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "files")
public class File {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String name;

	private String type;

	@Lob
	private byte[] data;

	public File() {
	}

	@ManyToOne
	@JoinColumn(name = "invoiceid")
//	@JsonManagedReference
	private Invoice invoice;

	public File(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public File(String id, String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data) + ", invoice="
				+ invoice + "]";
	}

}
