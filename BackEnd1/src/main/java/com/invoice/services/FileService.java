package com.invoice.services;

import com.invoice.entities.File;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.FileDBRepository;
import com.invoice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

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
	public File store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		File FileDB = new File(fileName, file.getContentType(), file.getBytes());

		return fileDBRepository.save(FileDB);
	}

	public File getFile(String id) {
		return fileDBRepository.findById(id).get();
	}


	public Stream<File> getAllFiles(Long invoiceID) throws ResourceNotFoundException {
		return invoiceRepository.findById(invoiceID).map(invoice -> {
			return fileDBRepository.findByInvoice_id(invoiceID).stream();



		}).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
	}
//	@Value("${files.path}")
//	private String filesPath;
//
//	public UrlResource download(String filename) {
//		try {
//			Path file = Paths.get(filesPath)
//					.resolve(filename);
//			UrlResource resource = new UrlResource(file.toUri());
//
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			} else {
//				throw new RuntimeException("Could not read the file!");
//			}
//		} catch (MalformedURLException e) {
//			throw new RuntimeException("Error: " + e.getMessage());
//		}
//	}
//	private ResponseFile pathToFileData(Path path) {
//		ResponseFile fileData = new ResponseFile();
//		String filename = path.getFileName()
//				.toString();
//		fileData.setName(filename);
//
//		try {
//			fileData.setType(Files.probeContentType(path));
//			fileData.setSize(Files.size(path));
//		} catch (IOException e) {
//			throw new RuntimeException("Error: " + e.getMessage());
//		}
//
//		return fileData;
//	}
}
