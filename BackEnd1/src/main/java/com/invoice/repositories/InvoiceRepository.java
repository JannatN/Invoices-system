package com.invoice.repositories;

import java.util.Optional;

import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.invoice.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

//	@Query("select i from Invoice i where id like %?1%")
//    Page<Invoice> findById(Long id, Pageable pageable);

//Optional getCreatedBy();
    public Page<Invoice> findAll(Specification<Invoice> spec, Pageable pageable);
//    public List<Invoice> findAll(Specification<User> spec);
}
