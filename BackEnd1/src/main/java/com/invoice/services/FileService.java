package com.invoice.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.entities.File;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.FileDBRepository;
import com.invoice.repositories.InvoiceRepository;

@Service
public class FileService {

	@Autowired
	private FileDBRepository fileDBRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

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

	public File getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<File> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}
