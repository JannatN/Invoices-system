package com.invoice.repositories;

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

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    /**
     *
     * @param spec
     * @param pageable
     * @return
     */
    public Page<Invoice> findAll(Specification<Invoice> spec, Pageable pageable);

    public default List<File> getFiles(){
        Invoice v = new Invoice();
        List<File> list = v.getFiles();
        return list;
    }
}
