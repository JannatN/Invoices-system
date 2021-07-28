package com.invoice.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.invoice.entities.File;
import com.invoice.repositories.FileDBRepository;
import com.invoice.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     *
     * @param page
     * @param request
     * @return
     */
    public Page<Invoice> findPaginated(Pageable page, Invoice request) {
//         page = PageRequest.of(0, 10, Sort.by("userID"));
        return invoiceRepository.findAll(InvoiceSpecification.getInvoices(request), page);
    }

    /**
     *
     * @param invoice
     * @return
     * @throws IOException
     */
    @Transactional
    public Invoice createInvoice(Invoice invoice) throws IOException {
        return invoiceRepository.save(invoice);
    }

    /**
     *
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    public Invoice getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
    }

    /**
     *
     * @param invoiceDetails
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    public Invoice updateInvoice(Invoice invoiceDetails, Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

//		invoice.setDate_created(invoiceDetails.getDate_created());
        invoice.setDue_date(invoiceDetails.getDue_date());
        invoice.setUser(invoiceDetails.getUser());
        invoice.setCompany(invoiceDetails.getCompany());
        invoice.setType(invoiceDetails.getType());
        invoice.setItems(invoiceDetails.getItems());

        return invoiceRepository.save(invoice);
    }

    /**
     *
     * @param invoiceID
     * @throws ResourceNotFoundException
     */
    public void deleteInvoice(Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

        invoiceRepository.delete(invoice);
    }

}