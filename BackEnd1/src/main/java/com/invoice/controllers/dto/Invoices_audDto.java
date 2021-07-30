package com.invoice.controllers.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Invoices_audDto {
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

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevtype() {
        return revtype;
    }

    public void setRevtype(Integer revtype) {
        this.revtype = revtype;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
