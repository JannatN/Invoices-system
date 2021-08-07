package com.invoice.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import com.invoice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.controllers.dto.ItemDto;
import com.invoice.entities.Item;
import com.invoice.exception.ResourceNotFoundException;
import com.invoice.services.ItemService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	private ItemService itemService;


	/**
	 *
	 * @param invoiceID
	 * @param itemDto
	 * @return
	 * @throws ResourceNotFoundException
	 * @throws ParseException
	 */
	@PostMapping("items/{invoiceID}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createItem(@PathVariable(value = "invoiceID") Long invoiceID, @Valid @RequestBody ItemDto itemDto)
			throws ResourceNotFoundException, ParseException {
		Item item = Mapper.convertToEntity(itemDto);
		System.out.println(item.toString());
		ResponseEntity<Item> itemCreated = itemService.createItem(invoiceID, item);
		return Mapper.convertToDto(itemCreated);
	}



}