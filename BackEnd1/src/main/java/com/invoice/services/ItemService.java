package com.invoice.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.entities.File;
import com.invoice.entities.Invoice;
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

	/**
	 *
	 * @param invoiceID
	 * @param item
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public ResponseEntity<Item> createItem(Long invoiceID, Item item) throws ResourceNotFoundException {
		return invoiceRepository.findById(invoiceID).map(invoice -> {
			item.setInvoice(invoice);
			itemRepository.saveAndFlush(item);
			return new ResponseEntity<Item>(item, HttpStatus.CREATED);
		}).orElseThrow(() -> new ResourceNotFoundException("invoiceid " + invoiceID + " not found"));
	}

	/**
	 *
	 * @param invoiceID
	 * @param itemID
	 * @param itemDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
//	public Item updateItem(Long invoiceID, Long itemID, Item itemDetails) throws ResourceNotFoundException {
//		if (!invoiceRepository.existsById(invoiceID)) {
//			throw new ResourceNotFoundException("InvoiceID " + invoiceID + " not found");
//		}
//
//		return itemRepository.findById(itemID).map(item -> {
//			item.setName(itemDetails.getName());
//			item.setDescription(itemDetails.getDescription());
//			item.setPrice(itemDetails.getPrice());
//			item.setCurrency(itemDetails.getCurrency());
//			item.setQuantity(itemDetails.getQuantity());
//			return itemRepository.save(item);
//		}).orElseThrow(() -> new ResourceNotFoundException("ItemID " + itemID + "not found"));
//	}
}
