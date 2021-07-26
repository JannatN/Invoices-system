package com.invoice.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.invoice.repositories.FileDBRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity.BodyBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.controllers.dto.FileDto;
import com.invoice.entities.File;
import com.invoice.payload.response.MessageResponse;
import com.invoice.services.FileService;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/file")

public class FileController {

	@Autowired
	private FileService storageService;
	@Autowired
	private FileDBRepository fileDBRepository;

	@Autowired
	private ModelMapper modelMapper;

	public static final String DIRECTORY = System.getProperty("user.home") + "/Desktop/uploads/";

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/upload")
	@PreAuthorize("hasRole('ADMIN') ")
		public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		try {
			storageService.store(file);
//System.out.println(storageService);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
		}
	}
//	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
//		List<String> filenames = new ArrayList<>();
//		for(MultipartFile file : multipartFiles) {
//			String filename = StringUtils.cleanPath(file.getOriginalFilename());
//			Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
//			copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//			filenames.add(filename);
//
//		}
//
//		return ResponseEntity.ok().body(filenames);
//	}

	// Define a method to download files
//	@GetMapping("download/{filename}")
//	public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
////		Path filePath =  fileDBRepository.findByName(filename);
//		final Optional<File> filePath = fileDBRepository.findByName(filename);
//
////		if(!Files.exists(filePath)) {
////			throw new FileNotFoundException(filename + " was not found on the server");
////		}
//		Resource resource = new UrlResource(filePath.toUri());
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("File-Name", filename);
//		httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
//				.headers(httpHeaders).body(resource);
//	}

//	public BodyBuilder upload(@RequestParam("file") MultipartFile file) throws IOException {
//
//		System.out.println("Original Image Byte Size - " + file.getBytes().length);
//		File img = new File(file.getOriginalFilename(), file.getContentType(),file.getBytes());
//		fileDBRepository.save(img);
//		return ResponseEntity.status(HttpStatus.OK);
//	}

	

//	@GetMapping("/files")
//	@PreAuthorize("hasRole('ADMIN') ")
//	public ResponseEntity<List<File>> getListFiles() {
//		List<File> files = storageService.getAllFiles().map(dbFile -> {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//					.path(dbFile.getId()).toUriString();
//
//			return new File(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
//		}).collect(Collectors.toList());
//
//		return ResponseEntity.status(HttpStatus.OK).body(files);
//	}

//	@GetMapping("/files/{id}")
//	@PreAuthorize("hasRole('ADMIN') ")
//	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//		File fileDB = storageService.getFile(id);
//
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//				.body(fileDB.getData());
//	}
	private FileDto convertToDto(ResponseEntity<File> file) {
		FileDto fileDto = modelMapper.map(file, FileDto.class);
		return fileDto;
	}

	private File convertToEntity(@Valid FileDto fileDto) throws ParseException {
		File file = modelMapper.map(fileDto, File.class);
		return file;
	}

}
