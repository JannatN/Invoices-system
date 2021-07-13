package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.invoice.entities.FileDB;

@Repository
//@Service("FileStorageService")
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
