package com.invoice.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.repositories.UserRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	public Invoice getLastInvoice() {
		return invoiceRepository.findTopByOrderByIdDesc();
	}
	@Transactional
	public ResponseEntity<Invoice> createInvoice(Invoice invoice) {
//		Set<Item> items = new HashSet<Item>();
		System.out.println(invoice.getItems().toString());

//		invoice.getItems().addAll(invoice.getItems());
		invoiceRepository.save(invoice);
		return new ResponseEntity<Invoice>(invoice, HttpStatus.CREATED);
	}

	public ResponseEntity<Invoice> getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));
		return ResponseEntity.ok().body(invoice);
	}

	public ResponseEntity<Invoice> updateInvoice(Long invoiceID, Invoice invoiceDetails)
			throws ResourceNotFoundException {
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

	public Map<String, Boolean> deleteInvoice(Long invoiceID) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

		invoiceRepository.delete(invoice);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

} 