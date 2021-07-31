package com.invoice.repositories;

import java.util.Optional;

import com.invoice.controllers.dto.InvoiceDto;
import org.springframework.data.domain.Auditable;
import com.invoice.entities.File;
import com.invoice.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    /**
     *
     * @param spec
     * @param pageable
     * @return
     */
    public Page<Invoice> findAll(Specification<Invoice> spec, Pageable pageable);

    public default Set<File> getFiles(){
        Invoice v = new Invoice();
        Set<File> list = v.getFiles();
        return list;
    }

    Invoice findTopByOrderByIdDesc();

}
