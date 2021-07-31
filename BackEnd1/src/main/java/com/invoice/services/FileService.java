package com.invoice.services;

import com.invoice.entities.File;
import com.invoice.entities.Invoice;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.payload.response.JwtResponse;
import com.invoice.repositories.FileDBRepository;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    //	public File store(MultipartFile file) throws IOException {
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		File FileDB = new File(fileName, file.getContentType(), file.getBytes());
//
//		return fileDBRepository.save(FileDB);
//	}
    public File store(MultipartFile file, Long invoiceID) throws IOException, ResourceNotFoundException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        return invoiceRepository.findById(invoiceID).map(invoice -> {
            FileDB.setInvoice(invoice);
            return fileDBRepository.save(FileDB);

        }).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
    }


    public File store(MultipartFile file) throws IOException, ResourceNotFoundException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        //System.out.println(FileDB.toString());

        return fileDBRepository.save(FileDB);

    }

    public File getFile(String id) {


        return fileDBRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public Stream<File> getAllFiles(Long invoiceID) throws ResourceNotFoundException {
        return invoiceRepository.findById(invoiceID).map(invoice -> {
            return fileDBRepository.findByInvoice_id(invoiceID).stream();
        }).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
    }

    public void deleteFile(String file) throws ResourceNotFoundException {
        File file1 = fileDBRepository.findById(file)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + file));

        fileDBRepository.delete(file1);
    }
}
