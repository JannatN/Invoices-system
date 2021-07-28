package com.invoice.controllers;

import java.text.ParseException;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import com.invoice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.InvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public Page<InvoiceDto> findPaginated(Pageable page, Invoice req) {
        return convertToDto(invoiceService.findPaginated(page, req));
    }

    private Page<InvoiceDto> convertToDto(Page<Invoice> paginated) {
        Page<InvoiceDto> dtoList = mapEntityPageIntoDtoPage(paginated, InvoiceDto.class);
        return dtoList;
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }
//    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
//    public List<InvoiceDto> getAllInvoices() {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        return convertToDto(invoiceService.getAllInvoices());
//    }

    @ResponseBody
    @PostMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) throws ParseException {
        Invoice invoice = convertToEntity(invoiceDto);
        Invoice invoiceCreated = invoiceService.createInvoice(invoice);
        System.out.println("invoice  "+invoiceCreated);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return convertToDto(invoiceCreated);
    }

    @GetMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
    public InvoiceDto getInvoiceById(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return convertToDto(invoiceService.getInvoiceById(invoiceID));
    }
//	@GetMapping("/invoices/{id}")
//	@PreAuthorize("hasRole(" + "'ADMIN') or hasRole('AUDITOR') ")
//	public Invoice getInvoiceById(@PathVariable(value = "id") Long invoiceID)
//			throws ResourceNotFoundException {
////		modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		return invoiceService.getInvoiceById(invoiceID);
//	}

    @PutMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InvoiceDto updateInvoice(@PathVariable(value = "id") Long invoiceID,
                                    @Valid @RequestBody InvoiceDto invoiceDetails) throws ResourceNotFoundException, ParseException {
        Invoice invoice = convertToEntity(invoiceDetails);
        return convertToDto(invoiceService.updateInvoice(invoice, invoiceID));
    }

    @DeleteMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteInvoice(@PathVariable(value = "id") Long invoiceID)
            throws ResourceNotFoundException {
        invoiceService.deleteInvoice(invoiceID);
    }

    private InvoiceDto convertToDto(Invoice invoice) {
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
