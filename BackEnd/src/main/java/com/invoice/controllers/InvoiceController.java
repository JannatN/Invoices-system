package com.invoice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.Invoice;
import com.invoice.repositories.InvoiceRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Invoice> invoices = new ArrayList<Invoice>();

			if (title == null)
				invoiceRepository.findAll().forEach(invoices::add);
//			else
//				invoiceRepository.findByTitleContaining(title).forEach(invoices::add);

			if (invoices.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}}