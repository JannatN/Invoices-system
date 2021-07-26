package com.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.entities.File;

import java.util.Optional;

@Repository
public interface FileDBRepository extends JpaRepository<File, String> {
    Optional<File> findByName(String name);

}
