package com.invoice.repositories;

import com.invoice.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDBRepository extends JpaRepository<File, String> {
    List<File> findByInvoice_id(Long invoiceID);

}
