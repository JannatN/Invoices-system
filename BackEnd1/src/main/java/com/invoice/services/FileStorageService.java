package com.invoice.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.entities.File;
import com.invoice.repositories.FileDBRepository;

@Service
public class FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;
	@Transactional
	public File store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		File file1 = new File();
		File FileDB = new File(file1.getId(), fileName, file.getContentType(), file.getBytes());

		return fileDBRepository.save(FileDB);
	}

	public File getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<File> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}
