package com.invoice.controllers.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceDto {
    @Id
    private Long id;
    @CreationTimestamp
    private LocalDateTime date_created;

//    @NotNull
    private LocalDateTime due_date;

    @NotBlank
    @Size(max = 20)
    private String type;

    @NotBlank
    @Size(max = 30)
    private String company;

    private List<ItemDto> items;
    private List<FileDto> files;

//    private UserDto user;
    private Long userid;

    public InvoiceDto(Long id, LocalDateTime date_created, LocalDateTime due_date, String type, String company,
                      List<ItemDto> items, List<FileDto> files) {
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
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

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "InvoiceDto [id=" + id + ", date_created=" + date_created + ", due_date=" + due_date + ", type=" + type
                + ", company=" + company + ", items=" + items + ", files=" + files + ", userid=" + "]";
    }

}
