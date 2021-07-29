package com.invoice.controllers.dto;

import com.invoice.entities.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

public class InvoiceDto {
    // todo: add validations here
    private Long id;
    @CreationTimestamp
    private LocalDateTime date_created;
    @NotNull
    private LocalDateTime due_date;
    @NotBlank
    @Size(max = 20)
    private String type;
    @NotBlank
    @Size(max = 30)
    private String company;

    private Set<ItemDto> items;

    private Set<FileDto> files;

//    private UserDto user;
    private Long userid;

    @CreatedBy
    private String createdBy;

//	@CreatedDate
//	@Temporal(TIMESTAMP)
//	private Date createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModifiedDate;



//    public InvoiceDto(Long id, LocalDateTime date_created, LocalDateTime due_date, String type, String company,
//                      Set<ItemDto> items, Set<FileDto> files) {
//        super();
//        this.id = id;
//        this.date_created = date_created;
//        this.due_date = due_date;
//        this.type = type;
//        this.company = company;
//        this.items = items;
//        this.files = files;
//    }


    public InvoiceDto(Long id, LocalDateTime date_created, LocalDateTime due_date, String type, String company, Set<ItemDto> items, Set<FileDto> files, Long userid, String createdBy, String lastModifiedBy, Date lastModifiedDate) {
        this.id = id;
        this.date_created = date_created;
        this.due_date = due_date;
        this.type = type;
        this.company = company;
        this.items = items;
        this.files = files;
        this.userid = userid;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public InvoiceDto() {

    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    //	public UserDto getUser() {
//		return user;
//	}
//
//	public void setUser(UserDto user) {
//		this.user = user;
//	}

    public Set<FileDto> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDto> files) {
        this.files = files;
    }

//    public Long getUserid() {
//        return user.getId();
//    }
//
//    public void setUserid(Long userid) {
//        this.user.setId(userid);
//    }

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
        return "InvoiceDto{" +
                "id=" + id +
                ", date_created=" + date_created +
                ", due_date=" + due_date +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", items=" + items +
                ", files=" + files +
                ", userid=" + userid +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
