package com.invoice.controllers;

import java.text.ParseException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.invoice.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.InvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ModelMapper modelMapper;

//	@GetMapping("/invoices")
//	public Page<Invoice> findPaginated(Pageable page) {
//		return invoiceService.findPaginated(page);
//	}
	@GetMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
	public List<InvoiceDto> getAllInvoices() {
		return convertToDto(invoiceService.getAllInvoices());
	}

	@ResponseBody
	@PostMapping("/invoices")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public InvoiceDto createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) throws ParseException {
		Invoice invoice = convertToEntity(invoiceDto);
		ResponseEntity<Invoice> invoiceCreated = invoiceService.createInvoice(invoice);
		return convertToDto(invoiceCreated);
	}

//	@GetMapping("/invoices/{id}")
//	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
//	public InvoiceDto getInvoiceById(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
//		return convertToDto(invoiceService.getInvoiceById(invoiceID));
//	}
	@GetMapping("/invoices/{id}")
	@PreAuthorize("hasRole(" + "'ADMIN') or hasRole('AUDITOR') ")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		return invoiceService.getInvoiceById(invoiceID);
	}

	@PutMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public InvoiceDto updateInvoice(@PathVariable(value = "id") Long invoiceID,
			@Valid @RequestBody InvoiceDto invoiceDetails) throws ResourceNotFoundException, ParseException {
		Invoice invoice = convertToEntity(invoiceDetails);
		return convertToDto(invoiceService.updateInvoice(invoice, invoiceID));
	}
//
	@DeleteMapping("/invoices/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteInvoice(@PathVariable(value = "id") Long invoiceID)
			throws ResourceNotFoundException {
		invoiceService.deleteInvoice(invoiceID);
	}

	private InvoiceDto convertToDto(ResponseEntity<Invoice> invoice) {
		InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
		return invoiceDto;
	}

	private Invoice convertToEntity(@Valid InvoiceDto invoiceDto) throws ParseException {
		Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
		return invoice;
	}

	private List<InvoiceDto> convertToDto(List<Invoice> allInvoices) {
		List<InvoiceDto> invoiceDtoList = mapList(allInvoices, InvoiceDto.class);
		return invoiceDtoList;
	}

	<S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

}
