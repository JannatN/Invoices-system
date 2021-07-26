package com.invoice.services;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.invoice.entities.File;
import com.invoice.repositories.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

//	public List<Invoice> getInvoicesList(int page, int size, String sortDir, String sort) {
//
//		PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
//
//		Page<Invoice> invoices = invoiceRepository.findByUser(invoiceRepository.getCurrentUser(), pageReq);
//		return invoices.getContent();
//	}

//	public Page<Invoice> showPage(@RequestParam(defaultValue = "0") int page) {
//		return invoiceRepository.findAll(new PageRequest(page, 10));
//	}

    public Page<Invoice> findPaginated(Pageable page) {
        return invoiceRepository.findAll(page);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) throws IOException {
//        invoice.getItems();
        System.out.println(invoice.toString());
//        invoice.setFiles();
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

//        System.out.println(FileDB.toString());
//        File file=new File();
//        file.setInvoice(invoice);
//         fileDBRepository.save(file);



        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

    }

    // todo: update items/files here
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

    public void deleteInvoice(Long invoiceID) throws ResourceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceID)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

        invoiceRepository.delete(invoice);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
        // return response;
    }

}