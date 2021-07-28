package com.invoice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.invoice.entities.Invoice;
import com.invoice.entities.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    /**
     *
     * @param spec
     * @param pageable
     * @return
     */
    public Page<Invoice> findAll(Specification<Invoice> spec, Pageable pageable);
}
