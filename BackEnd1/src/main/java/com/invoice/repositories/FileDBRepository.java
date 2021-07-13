package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entities.File;

@Repository
public interface FileDBRepository extends JpaRepository<File, String> {

}
