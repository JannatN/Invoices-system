package com.invoice.controllers;

import java.text.ParseException;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import com.invoice.controllers.dto.Invoices_audDto;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.File;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.mapper.Mapper;
import com.invoice.payload.response.ResponseFile;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.services.FileService;
import com.invoice.services.InvoiceAudService;
import com.invoice.services.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.invoice.entities.invoices_aud;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private FileService storageService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InvoiceAudService invoiceAudService;

    Mapper mapper;
    @GetMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public Page<InvoiceDto> findPaginated(Pageable page, Invoice req) {
        return convertToDto(invoiceService.findPaginated(page, req));
    }

    //    @ResponseBody
//    @PostMapping(path = "/invoices")
//    @PreAuthorize("hasRole('ADMIN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<InvoiceDto> createInvoice(@Valid @ModelAttribute("invoice") InvoiceDto invoiceDto,
//                                                    @RequestParam("file") MultipartFile files) throws ParseException, IOException {
//        Invoice invoice = convertToEntity(invoiceDto);
//        ResponseEntity<Invoice> invoiceCreated = invoiceService.createInvoice(invoice, files);
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        return convertToDto(invoiceCreated);
//
//    }
    @ResponseBody
    @PostMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) throws ParseException, IOException {
        Invoice invoice = convertToEntity(invoiceDto);
        Invoice invoiceCreated = invoiceService.createInvoice(invoice);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return convertToDto(invoiceCreated);
    }


    //@PostUpdate
    @PutMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InvoiceDto updateInvoice(@PathVariable(value = "id") Long invoiceID,
                                    @Valid @RequestBody InvoiceDto invoiceDetails) throws ResourceNotFoundException, ParseException {
        Invoice invoice = convertToEntity(invoiceDetails);
        return convertToDto(invoiceService.updateInvoice(invoice, invoiceID));
    }

    @GetMapping("/invoices/Aud")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public List<Invoices_audDto> getAllInvoicesAud() {
        return convertToDto(invoiceAudService.getAllInvoicesAud());
    }

    @GetMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
    public InvoiceDto getInvoiceById(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        return convertToDto(invoiceService.getInvoiceById(invoiceID));
    }

    @GetMapping("/invoices/Aud/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
    public List<Invoices_audDto> getInvoiceAudById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return convertToDto(invoiceAudService.getInvoiceAudById(id));
    }





    @GetMapping("/invoices/files/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<List<ResponseFile>> getInvoiceFiles(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        List<ResponseFile> files = storageService.getAllFiles(invoiceID).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/")
                    .path(dbFile.getId()).toUriString();

            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
//    @GetMapping("/file/{id}")
//    @PreAuthorize("hasRole('ADMIN') ")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//        File fileDB = storageService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
//    }

    @DeleteMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteInvoice(@PathVariable(value = "id") Long invoiceID)
            throws ResourceNotFoundException {
        invoiceService.deleteInvoice(invoiceID);
    }
/////////////////////////////////////////////////////////////////////////////////////////////

    private List<Invoices_audDto> convertToDto(List<invoices_aud> invoiceAudById) {
        List<Invoices_audDto> invoiceDtoList = mapList(invoiceAudById, Invoices_audDto.class);
        return invoiceDtoList;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    private Page<InvoiceDto> convertToDto(Page<Invoice> paginated) {
        Page<InvoiceDto> dtoList = mapEntityPageIntoDtoPage(paginated, InvoiceDto.class);
        return dtoList;
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }


    private ResponseEntity<InvoiceDto> convertToDto(ResponseEntity<Invoice> invoice) {
        ResponseEntity<InvoiceDto> invoiceDto = modelMapper.map(invoice, ResponseEntity.class);
        return invoiceDto;
    }


    private InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        return invoiceDto;
    }

    private Invoice convertToEntity(@Valid InvoiceDto invoiceDto) throws ParseException {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        return invoice;
    }

}