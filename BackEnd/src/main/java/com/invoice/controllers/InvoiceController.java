package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

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
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.InvoiceService;
//TODO Read about 3 tier arch, DTO, Mapping between DTO and entites
//TODO do pagination and searching/filterting

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
	public List<Invoice> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}

	@GetMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		return invoiceService.getInvoiceById(invoiceID);
	}

	@PostMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
		return invoiceService.addInvoice(invoice);
	}

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
