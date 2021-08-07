package com.invoice.controllers;

import java.text.ParseException;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import com.invoice.controllers.dto.Invoices_audDto;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.mapper.Mapper;
import com.invoice.payload.response.ResponseFile;
import com.invoice.services.FileService;
import com.invoice.services.InvoiceAudService;
import com.invoice.services.InvoiceService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
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
    private InvoiceAudService invoiceAudService;

    /**
     *
     * @param page
     * @param key
     * @return
     */
    @GetMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public Page<InvoiceDto> findPaginated(Pageable page,@RequestParam(required = false) String key) {
        System.out.println(key);
        return Mapper.convertToDto(invoiceService.findPaginated(page,key));
    }

    /**
     *
     * @param invoiceDto
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) throws ParseException, IOException {
        Invoice invoice = Mapper.convertToEntity(invoiceDto);
        Invoice invoiceCreated = invoiceService.createInvoice(invoice);
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return Mapper.convertToDto(invoiceCreated);
    }


    //@PostUpdate

    /**
     *
     * @param invoiceID
     * @param invoiceDetails
     * @return
     * @throws ResourceNotFoundException
     * @throws ParseException
     */
    @PutMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InvoiceDto updateInvoice(@PathVariable(value = "id") Long invoiceID,
                                    @Valid @RequestBody InvoiceDto invoiceDetails) throws ResourceNotFoundException, ParseException {
        Invoice invoice = Mapper.convertToEntity(invoiceDetails);
        return Mapper.convertToDto(invoiceService.updateInvoice(invoice, invoiceID));
    }

    @GetMapping("/invoices/Aud")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
    public List<Invoices_audDto> getAllInvoicesAud() {
        return Mapper.convertToDtoInvoice(invoiceAudService.getAllInvoicesAud());
    }

    /**
     *
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/invoices/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
    public InvoiceDto getInvoiceById(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        return Mapper.convertToDto(invoiceService.getInvoiceById(invoiceID));
    }

    /**
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/invoices/Aud/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
    public List<Invoices_audDto> getInvoiceAudById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return Mapper.convertToDtoInvoice(invoiceAudService.getInvoiceAudById(id));
    }

    /**
     *
     * @param invoiceID
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/invoices/files/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<List<ResponseFile>> getInvoiceFiles(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        List<ResponseFile> files = storageService.getAllFiles(invoiceID).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/")
                    .path(dbFile.getId()).toUriString();

            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length,dbFile.getId());
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

}