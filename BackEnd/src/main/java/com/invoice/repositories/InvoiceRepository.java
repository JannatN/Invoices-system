package com.invoice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
		
//		List<Invoice> findByPublished(boolean published);
//		List<Invoice> findByTitleContaining(String title);
	}

