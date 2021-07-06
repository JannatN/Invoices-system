package com.invoice.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.ItemService;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@GetMapping("/items")
	@PreAuthorize("hasRole('ADMIN') ")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@PostMapping("items/{invoiceID}")
	@PreAuthorize("hasRole('ADMIN')")
	public Item createItem2(@PathVariable(value = "invoiceID") Long invoiceID, @Valid @RequestBody Item item)
			throws ResourceNotFoundException {
		return itemService.createItem2(invoiceID, item);
	}

	@PostMapping("/items")
	@PreAuthorize("hasRole('ADMIN')")
	public Item createItem(@Valid @RequestBody Item item) {
		return itemService.createItem(item);
	}

	@GetMapping("/items/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemID) throws ResourceNotFoundException {
		return itemService.getItemById(itemID);
	}

	@PutMapping("/invoices/{invoiceID}/items/{itemID}")
	@PreAuthorize("hasRole('ADMIN')")
	public Item updateItem(@PathVariable(value = "invoiceID") Long invoiceID,
			@PathVariable(value = "itemID") Long itemID, @Valid @RequestBody Item itemDetails)
			throws ResourceNotFoundException {
		return itemService.updateItem(invoiceID, itemID, itemDetails);
	}

	@GetMapping("/items/last")
	@PreAuthorize("hasRole('ADMIN') ")
	public Item getLastItem() {
		return itemService.getLastItem();
	}

}