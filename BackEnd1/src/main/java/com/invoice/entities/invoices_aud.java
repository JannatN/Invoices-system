package com.invoice.entities;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
//    @Table(name = "invoices_aud ")

public class invoices_aud {
    @Id
    @Column(name = "rev")
    private Integer rev;

    @Column(name = "id")
    private Integer id;

    @Column(name = "revtype")
    private Integer revtype;

    @Column(name = "company")
    private String company;
    @Column(name = "date_created")
    private Date date_created;
    @Column(name = "due_date")
    private Date due_date;
    @Column(name = "type")
    private String type;
    @Column(name = "userid")
    private Integer userid;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;


    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRev() {
        return rev;
    }

    public Integer getRevtype() {
        return revtype;
    }

    public String getCompany() {
        return company;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Date getDue_date() {
        return due_date;
    }

    public String getType() {
        return type;
    }

    public Integer getUserid() {
        return userid;
    }
}