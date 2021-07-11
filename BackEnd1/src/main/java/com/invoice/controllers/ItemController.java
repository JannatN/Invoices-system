package com.invoice.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.dto.InvoiceDto;
import com.invoice.dto.ItemDto;
import com.invoice.entities.Invoice;
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

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("items/{invoiceID}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDto createItem(@PathVariable(value = "invoiceID") Long invoiceID, @Valid @RequestBody ItemDto itemDto)
			throws ResourceNotFoundException, ParseException {
		Item item = convertToEntity(itemDto);
		ResponseEntity<Item> itemCreated = itemService.createItem(invoiceID, item);
		return convertToDto(itemCreated);
	}

//	@GetMapping("/items/{id}")
//	@GetMapping("/invoices/{id}")
//	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR')")
//	public InvoiceDto getInvoiceById(@PathVariable(value = "id") Long itemID) throws ResourceNotFoundException {
//		return convertToDto(itemService.getItemById(itemID));
//	}
//	@PutMapping("/invoices/{invoiceID}/items/{itemID}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public Item updateItem(@PathVariable(value = "invoiceID") Long invoiceID,
//			@PathVariable(value = "itemID") Long itemID, @Valid @RequestBody Item itemDetails)
//			throws ResourceNotFoundException {
//		return itemService.updateItem(invoiceID, itemID, itemDetails);
//	}
	@GetMapping("/items")
	@PreAuthorize("hasRole('ADMIN') or hasRole('AUDITOR') ")
	public List<ItemDto> getAllItems() {
		return convertToDto(itemService.getAllItems());
	}

	private ItemDto convertToDto(ResponseEntity<Item> item) {
		ItemDto itemDto = modelMapper.map(item, ItemDto.class);
		return itemDto;
	}

	private Item convertToEntity(@Valid ItemDto ItemDto) throws ParseException {
		Item item = modelMapper.map(ItemDto, Item.class);
		return item;
	}

	private List<ItemDto> convertToDto(List<Item> allItems) {
		List<ItemDto> itemService = mapList(allItems, ItemDto.class);
		return itemService;
	}

	<S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

}