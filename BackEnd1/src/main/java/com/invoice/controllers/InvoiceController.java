package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.services.InvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@GetMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
	public List<Invoice> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}
	
	
	

	
//	@GetMapping("/getInfo/{id}")
//    public void getInfo(@PathVariable  int id){
//        System.out.println(invoiceRepository.findLastChangeRevision(id));
//    }
//	
	@GetMapping("/invoices/last")
	@PreAuthorize("hasRole('ADMIN') ")
	public Invoice getLastInvoice() {
		return invoiceService.getLastInvoice();
	}
	Invoice invoice;
//	@Autowired
//	public EntityManagerFactory entityManagerFactory;
//	@GetMapping("/invoices/info/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//public void get(@PathVariable Long id){
////   System.out.println(AuditReaderFactory.get(entityManagerFactory.createEntityManager()).toString());
//		invoiceRepository.findRevisions(id);
//}
	@PostMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) {
//		invoiceRepository.save(invoice.setId(id));
		return invoiceService.createInvoice(invoice);
	}

	@GetMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		return invoiceService.getInvoiceById(invoiceID);
	}

//	@PostMapping("/invoices")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
//		return invoiceService.addInvoice(invoice);
//	}

	@PutMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") Long invoiceID,
			@Valid @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException {
		return invoiceService.updateInvoice(invoiceID, invoiceDetails);
	}

	@DeleteMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteInvoice(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		return invoiceService.deleteInvoice(invoiceID);
	}

	
	

}
