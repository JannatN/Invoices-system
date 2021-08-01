package com.invoice.repositories;

import com.invoice.entities.invoices_aud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceAudRepository extends JpaRepository<invoices_aud, Integer> {

    List<invoices_aud> findByIdEquals(Integer id);

}
