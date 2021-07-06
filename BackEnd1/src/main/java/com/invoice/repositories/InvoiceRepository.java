package com.invoice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice.entities.Invoice;
import com.invoice.entities.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
//
//	@Query(countByUserId)
//	Integer countByUserId(Long user_id);
//
//	final String countByUserId= "SELECT COUNT(i) FROM Invoice i WHERE i.userID = ?1";
	
	Invoice findTopByOrderByIdDesc();
}
