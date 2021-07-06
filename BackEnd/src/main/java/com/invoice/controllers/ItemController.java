package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.Invoice;
import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.repositories.ItemRepository;
import com.invoice.services.InvoiceService;
import com.invoice.services.ItemService;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@PostMapping("/invoices/{invoiceID}/items")
	@PreAuthorize("hasRole('ADMIN')")
	public Item createItem(@PathVariable(value = "invoiceID") Long invoiceID, @Valid @RequestBody Item item)
			throws ResourceNotFoundException {
		return itemService.createItem(invoiceID, item);
	}

	@PutMapping("/invoices/{invoiceID}/items/{itemID}")
	@PreAuthorize("hasRole('ADMIN')")
	public Item updateItem(@PathVariable(value = "invoiceID") Long invoiceID,
			@PathVariable(value = "itemID") Long itemID, @Valid @RequestBody Item itemDetails)
			throws ResourceNotFoundException {
		return itemService.updateItem(invoiceID, itemID, itemDetails);
	}

//	@DeleteMapping("/items/{id}")
//	public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemID) throws ResourceNotFoundException {
//		Item item = itemRepository.findById(itemID)
//				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemID));
//
//		itemRepository.delete(item);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}
}