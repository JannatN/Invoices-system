package com.invoice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invoice.entities.File;
import com.invoice.payload.response.MessageResponse;
import com.invoice.services.FileService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

	@Autowired
	private FileService storageService;
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/upload/{invoiceID}")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file,
			@PathVariable Long invoiceID) {
		String message = "";

		try {
			storageService.store(file, invoiceID);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
		}
	}

//	@GetMapping("/files")
//	@PreAuthorize("hasRole('ADMIN') ")
//	public ResponseEntity<List<File>> getListFiles() {
//		List<File> files = storageService.getAllFiles().map(dbFile -> {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//					.path(dbFile.getId()).toUriString();
//
//			return new File(dbFile.getId(),dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
//		}).collect(Collectors.toList());
//
//		return ResponseEntity.status(HttpStatus.OK).body(files);
//	}

	@GetMapping("/files/{id}")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		File fileDB = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}
}
