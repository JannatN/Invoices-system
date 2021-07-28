package com.invoice.entities;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
//    @Size(max = 20)
    private String auditorName;

    @NotBlank
//    @Size(max = 200)
//    @Embedded
    private String invoiceBefore;

    @NotBlank
    private String invoiceAfter;


    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updated_At;

    public Track(Long id, String auditorName,  String invoiceBefore, String invoiceAfter, LocalDateTime created_at, LocalDateTime updated_At) {
        this.id = id;
        this.auditorName = auditorName;
        this.invoiceBefore = invoiceBefore;
        this.invoiceAfter = invoiceAfter;
        this.created_at = created_at;
        this.updated_At = updated_At;
    }

    public Track() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getInvoiceBefore() {
        return invoiceBefore;
    }

    public void setInvoiceBefore(String invoiceBefore) {
        this.invoiceBefore = invoiceBefore;
    }

    public String getInvoiceAfter() {
        return invoiceAfter;
    }

    public void setInvoiceAfter(String invoiceAfter) {
        this.invoiceAfter = invoiceAfter;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }
}
