package com.invoice.services;

import java.io.IOException;
import java.util.*;

import javax.transaction.Transactional;

import com.invoice.entities.File;
import com.invoice.repositories.FileDBRepository;
import com.invoice.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private FileDBRepository fileDBRepository;

    /**
     * @param page
     * @param request
     * @return
     */
    public Page<Invoice> findPaginated(Pageable page, Invoice request) {
//         page = PageRequest.of(0, 10, Sort.by("userID"));
        return invoiceRepository.findAll(InvoiceSpecification.getInvoices(request), page);
    }

    /**
     * @param invoice
     * @return
     * @throws IOException
     */
    @Transactional
    public ResponseEntity<Invoice> createInvoice(Invoice invoice, MultipartFile files) throws IOException {
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        File FileDB = new File(fileName, files.getContentType(), files.getBytes());
//        File f = fileDBRepository.save(FileDB);
        List<File> list = new LinkedList<File>();
        list.add(FileDB);
        invoice.setFiles(list);
        System.out.println("fileeeees "+ Arrays.asList(list));
        final Invoice inv = invoiceRepository.save(invoice);
        return ResponseEntity.ok(inv);
    }

    /**
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    public Invoice getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
    }

    /**
     * @param invoiceDetails
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    public Invoice updateInvoice(Invoice invoiceDetails, Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
        invoice.setDue_date(invoiceDetails.getDue_date());
        invoice.setUserID(invoiceDetails.getUserID());
        invoice.setCompany(invoiceDetails.getCompany());
        invoice.setType(invoiceDetails.getType());
        invoice.setItems(invoiceDetails.getItems());

        return invoiceRepository.save(invoice);
    }

    /**
     * @param invoiceID
     * @throws ResourceNotFoundException
     */
    public void deleteInvoice(Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

        invoiceRepository.delete(invoice);
    }

}