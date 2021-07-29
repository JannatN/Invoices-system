package com.invoice.services;

import javax.transaction.Transactional;

import com.invoice.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> findPaginated(Pageable page, Invoice request) {
        return invoiceRepository.findAll(InvoiceSpecification.getInvoices(request), page);
    }

//    public List<Invoice> getAllInvoices() {
//        return invoiceRepository.findAll();
//    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        System.out.println(invoice.getItems().toString());
//        invoice.setItems(invoice.getItems());
        return invoiceRepository.save(invoice);
    }
//    @org.springframework.transaction.annotation.Transactional("MANDATORY")
//    public void perform(Invoice target, Action action) {
//        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
//        entityManager.persist(new FileHistory(target, action));
//
//    }
    public Invoice getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

    }

    // todo: update items/files here
    public Invoice updateInvoice(Invoice invoiceDetails, Long invoiceID) throws ResourceNotFoundException {
//        System.out.println(invoiceDetails.toString());

        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
//System.out.println(invoice.toString());


//		invoice.setDate_created(invoiceDetails.getDate_created());
        invoice.setDue_date(invoiceDetails.getDue_date());
        invoice.setUser(invoiceDetails.getUser());
        invoice.setCompany(invoiceDetails.getCompany());
        invoice.setType(invoiceDetails.getType());
        invoice.setItems(invoiceDetails.getItems());

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

        invoiceRepository.delete(invoice);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
        // return response;
    }

}