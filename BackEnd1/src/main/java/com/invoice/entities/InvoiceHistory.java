package com.invoice.entities;


    import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedBy;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;



    @Entity
    @EntityListeners(AuditingEntityListener.class)
    public class InvoiceHistory {
        @Id
        @GeneratedValue
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "invoiceid", foreignKey = @ForeignKey(name = "FK_invoice_history_invoice"))
        private Invoice invoice;
        @Transient
        private Invoice invoiceContentBefore;

        @Transient
        private Invoice invoiceContentAfter;

        @LastModifiedBy
        private String modifiedBy;

        @CreatedDate
        @Temporal(TIMESTAMP)
        private Date modifiedDate;

        @Enumerated(STRING)
        private Action action;

        public InvoiceHistory() {
        }

        public InvoiceHistory(Invoice invoice, Action action) {
            this.invoice = invoice;
            this.invoiceContentBefore = invoice;
            this.action = action;
        }

        public Invoice getInvoice() {
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        public Invoice getInvoiceContentBefore() {
            return invoiceContentBefore;
        }

        public void setInvoiceContentBefore(Invoice invoiceContentBefore) {
            this.invoiceContentBefore = invoiceContentBefore;
        }

        public Invoice getInvoiceContentAfter() {
            return invoiceContentAfter;
        }

        public void setInvoiceContentAfter(Invoice invoiceContentAfter) {
            this.invoiceContentAfter = invoiceContentAfter;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Invoice getFile() {
            return invoice;
        }

        public void setFile(Invoice invoice) {
            this.invoice = invoice;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Date getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

//        @Override
//        public String toString() {
//            return "InvoiceHistory{" +
//                    "id=" + id +
//                    ", invoice=" + invoice +
//                    ", invoiceContentBefore=" + invoiceContentBefore +
//                    ", invoiceContentAfter=" + invoiceContentAfter +
//                    ", modifiedBy='" + modifiedBy + '\'' +
//                    ", modifiedDate=" + modifiedDate +
//                    ", action=" + action +
//                    '}';
//        }
    }