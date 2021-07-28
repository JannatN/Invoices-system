package com.invoice.repositories;

import com.invoice.entities.FileHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, Integer> {
}
