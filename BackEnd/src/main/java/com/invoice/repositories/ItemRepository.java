package com.invoice.repositories;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entities.Item;
import com.invoice.entities.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    Page<Item> findByInvoiceId(Long invoiceID, Pageable pageable);

	


}
