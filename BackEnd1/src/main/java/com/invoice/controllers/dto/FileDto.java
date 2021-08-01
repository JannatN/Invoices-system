package com.invoice.controllers.dto;

public class FileDto {
    private String id;
    private String name;

    private String type;

    private byte[] data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//	private InvoiceDto invoice;

    public FileDto(String name, String type, byte[] data,String id) {
        this.id=id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public FileDto() {
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

//	public InvoiceDto getInvoice() {
//		return invoice;
//	}
//
//	public void setInvoice(InvoiceDto invoice) {
//		this.invoice = invoice;
//	}

}
