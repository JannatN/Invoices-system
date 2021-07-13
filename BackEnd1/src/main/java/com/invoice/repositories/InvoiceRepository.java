package com.invoice.repositories;

import java.util.Optional;

import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
//
//	@Query(countByUserId)
//	Integer countByUserId(Long user_id);
//	Revisions<Invoice, Long> findRevisions(ID id);
//
//	final String countByUserId= "SELECT COUNT(i) FROM Invoice i WHERE i.userID = ?1";
//	void findRevisions( Integer id);
	Invoice findTopByOrderByIdDesc();
}
