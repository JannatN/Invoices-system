package com.invoice.services;

import javax.transaction.Transactional;

import com.invoice.entities.File;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.FileDBRepository;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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
//    @Transactional
//    public ResponseEntity<Invoice> createInvoice(Invoice invoice, MultipartFile files) throws IOException {
//        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
//        File FileDB = new File(fileName, files.getContentType(), files.getBytes());
////        File f = fileDBRepository.save(FileDB);
//        List<File> list = new LinkedList<File>();
//        list.add(FileDB);
//        invoice.setFiles(list);
//        System.out.println("fileeeees "+ Arrays.asList(list));
//        final Invoice inv = invoiceRepository.save(invoice);
//        return ResponseEntity.ok(inv);
//    }
    @Transactional
    public Invoice createInvoice(Invoice invoice) throws IOException {
        return invoiceRepository.save(invoice);
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
//        System.out.println(invoiceDetails.toString());

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

    public Stream<File> getAllFiles(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID).map(invoice -> {
            return fileDBRepository.findByInvoice_id(invoiceID).stream();
        }).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
    }

}