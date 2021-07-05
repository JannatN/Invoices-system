package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
//TODO Read about 3 tier arch, DTO, Mapping between DTO and entites

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	@Autowired
	private InvoiceRepository invoiceRepository;
//TODO do pagination and searching/filterting
	@GetMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	@GetMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
		return ResponseEntity.ok().body(invoice);
	}

	@PostMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN')")
	public Invoice createInvoice(@Valid @RequestBody Invoice invoice) {
		return invoiceRepository.save(invoice);
	}


	
	@PutMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") Long invoiceID,
			@Valid @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

		invoice.setDateCreated(invoiceDetails.getDateCreated());
		invoice.setDueDate(invoiceDetails.getDueDate());
		invoice.setUserID(invoiceDetails.getUserID());
		invoice.setCompany(invoiceDetails.getCompany());
		invoice.setType(invoiceDetails.getType());

		final Invoice updatedInvoice = invoiceRepository.save(invoice);
		return ResponseEntity.ok(updatedInvoice);
	}

	@DeleteMapping("/invoices/{id}")
	public Map<String, Boolean> deleteInvoice(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

		invoiceRepository.delete(invoice);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
//	@RequestMapping("/invoice/{id}")
//	@ResponseBody 
//	public long getRatingInfo(@PathVariable("id") long id, HttpServletRequest req, HttpServletResponse res) throws Exception {
//
//	long userobj = invoiceRepository.countByUserId(id);
//	return userobj;
//	}
}
