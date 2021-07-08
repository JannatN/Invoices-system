package com.invoice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.entities.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Item findTopByOrderByIdDesc();

}
