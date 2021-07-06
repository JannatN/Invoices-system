package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
