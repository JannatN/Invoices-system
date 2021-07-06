package com.invoice.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/items")
	@PreAuthorize("hasRole('ADMIN') ")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@GetMapping("/items/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemID) throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemID)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemID));
		return ResponseEntity.ok().body(item);
	}

	@Autowired
	private InvoiceRepository invoiceRepository;
	@PostMapping("items/{invoiceID}")
	@PreAuthorize("hasRole('ADMIN')")
	public Item createItem2(@PathVariable(value = "invoiceID") Long invoiceID, @Valid @RequestBody Item item)
			throws ResourceNotFoundException {
		return invoiceRepository.findById(invoiceID).map(invoice -> {
//			item.setInvoice(invoice);
			item.setInvoiceID(invoiceID);
			return itemRepository.saveAndFlush(item);
		}).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
	}
	
	
	@PostMapping("/items")
	@PreAuthorize("hasRole('ADMIN')")
	public Item createItem(@Valid @RequestBody Item item) {
		return itemRepository.save(item);
	}
	

//	@PostMapping("/items/{invoiceID}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public Item createItem2(@PathVariable(value = "invoiceID") Long id,@Valid @RequestBody Item item) {
//		return itemRepository.save(item);
//	}

	@GetMapping("/items/last")
	@PreAuthorize("hasRole('ADMIN') ")
	public Item getLastItem() {
//		invoiceRepository.
		return itemRepository.findTopByOrderByIdDesc();
		}
	
	
	
	@PutMapping("/items/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long itemID,
			@Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemID)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemID));

		item.setName(itemDetails.getName());
		item.setDescription(itemDetails.getDescription());
		item.setPrice(itemDetails.getPrice());
		item.setCurrency(itemDetails.getCurrency());
		item.setQuantity(itemDetails.getQuantity());
		item.setInvoiceID(itemDetails.getInvoiceID());


		final Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}

	@DeleteMapping("/items/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemID) throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemID)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemID));

		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}