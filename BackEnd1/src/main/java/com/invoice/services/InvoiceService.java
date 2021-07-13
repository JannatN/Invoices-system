package com.invoice.services;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.invoice.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.repositories.UserRepository;

import org.springframework.data.domain.Sort;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

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
	public Invoice createInvoice(Invoice invoice) {
		System.out.println(invoice.getItems().toString());
		return invoiceRepository.save(invoice);
	}

	public Invoice getInvoiceById(Long invoiceID) throws ResourceNotFoundException {
		return invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

	}

	public Invoice updateInvoice(Invoice invoiceDetails, Long invoiceID) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceID)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + invoiceID));

		invoice.setDate_created(invoiceDetails.getDate_created());
		invoice.setDue_date(invoiceDetails.getDue_date());
		invoice.setUserID(invoiceDetails.getUserID());
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