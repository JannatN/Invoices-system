package com.invoice.services;

import com.invoice.entities.invoices_aud;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceAudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class InvoiceAudService {
    @Autowired
    private InvoiceAudRepository invoiceAudRepository;

    public List<invoices_aud> getAllInvoicesAud() {
        return invoiceAudRepository.findAll();
    }


    public List<invoices_aud> getInvoiceAudById(Integer id)
            throws ResourceNotFoundException {
        return invoiceAudRepository.findByIdEquals(id);
    }

}
