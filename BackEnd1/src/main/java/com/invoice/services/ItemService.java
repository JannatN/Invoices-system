package com.invoice.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.entities.FileDB;
import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.repositories.FileDBRepository;
import com.invoice.repositories.InvoiceRepository;
import com.invoice.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

//	@GetMapping("/posts/{postId}/comments")
//	public Page<Item> getAllItemsByInvoiceID(@PathVariable(value = "invoiceID") Long invoiceID, Pageable pageable)
//			throws ResourceNotFoundException {
//		return ItemRepository.findByInvoiceId(invoiceID, pageable);
//	}
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public ResponseEntity<Item> getItemById(Long itemID) throws ResourceNotFoundException {
		Item item = itemRepository.findById(itemID)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemID));
		return ResponseEntity.ok().body(item);
	}

	public Item createItem2(Long invoiceID, Item item) throws ResourceNotFoundException {
		return invoiceRepository.findById(invoiceID).map(invoice -> {
			item.setInvoice(invoice);
			item.setInvoiceID(invoiceID);
			return itemRepository.saveAndFlush(item);
		}).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
	}

	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	public Item getLastItem() {
		return itemRepository.findTopByOrderByIdDesc();
	}

	public Item updateItem(Long invoiceID, Long itemID, Item itemDetails) throws ResourceNotFoundException {
		if (!invoiceRepository.existsById(invoiceID)) {
			throw new ResourceNotFoundException("InvoiceID " + invoiceID + " not found");
		}

		return itemRepository.findById(itemID).map(item -> {
			item.setName(itemDetails.getName());
			item.setDescription(itemDetails.getDescription());
			item.setPrice(itemDetails.getPrice());
			item.setCurrency(itemDetails.getCurrency());
			item.setQuantity(itemDetails.getQuantity());
			item.setInvoiceID(itemDetails.getInvoiceID());
			return itemRepository.save(item);
		}).orElseThrow(() -> new ResourceNotFoundException("ItemID " + itemID + "not found"));
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
