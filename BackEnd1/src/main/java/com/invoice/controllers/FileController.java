package com.invoice.controllers;

import com.invoice.controllers.dto.FileDto;
import com.invoice.entities.File;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.payload.response.MessageResponse;
import com.invoice.payload.response.ResponseFile;
import com.invoice.services.FileService;
import com.invoice.services.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService storageService;

    @Autowired
    private ModelMapper modelMapper;

    //    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/upload/{invoiceID}")
//    @PreAuthorize("hasRole('ADMIN') ")
//    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        try {
//            storageService.store(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
//        }
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @PathVariable Long id) {
        String message = "";

//        Object invoice=new Invoice();
//        String in=(String) invoice;

//        invoiceService.createInvoice(invoice);
        try {
            storageService.store(file, id);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }
//    @PostMapping("/upload")
//    @PreAuthorize("hasRole('ADMIN') ")
//    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        try {
//            storageService.store(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
//        }
//    }

    @GetMapping("/filess/{id}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<List<ResponseFile>> getInvoiceFiles(@PathVariable(value = "id") Long invoiceID) throws ResourceNotFoundException {
        List<ResponseFile> files = storageService.getAllFiles(invoiceID).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(dbFile.getId()).toUriString();
//            System.out.println("fileeeee " + fileDownloadUri);
//            String headerAuth = request.getHeader("Authorization");
//            System.out.println("Header " + headerAuth);
//            res.addHeader("Authorization", "Bearer " + headerAuth);
            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length,dbFile.getId());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(dbFile.getId()).toUriString();

            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length,dbFile.getId());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('AUDITOR')")
    public ResponseEntity<byte[]> getFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse res) {
        File fileDB = storageService.getFile(id);
        String headerAuth = request.getHeader("Authorization");
        System.out.println("Header "+ headerAuth);
        res.addHeader("Authorization", "Bearer " + headerAuth);
//        fileDB.getData();
//    FileDto fileDto=new FileDto();
//    fileDto.setData(fileDB.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/file/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public void deleteInvoice(@PathVariable(value = "id") String fileID)
            throws ResourceNotFoundException {
        storageService.deleteFile(fileID);
    }

    private FileDto convertToDto(ResponseEntity<File> file) {
        FileDto fileDto = modelMapper.map(file, FileDto.class);
        return fileDto;
    }

    private File convertToEntity(@Valid FileDto fileDto) throws ParseException {
        File file = modelMapper.map(fileDto, File.class);
        return file;
    }

}
