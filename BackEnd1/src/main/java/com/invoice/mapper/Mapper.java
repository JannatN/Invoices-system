package com.invoice.mapper;

import com.invoice.controllers.dto.*;
import com.invoice.entities.*;
import com.invoice.payload.request.LoginRequest;
import com.invoice.payload.request.SignupRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class Mapper {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapper();

    public static List<Invoices_audDto> convertToDtoInvoice(List<invoices_aud> allInvoicesAud) {
        List<Invoices_audDto> audDto = mapList(allInvoicesAud, Invoices_audDto.class);
        return audDto;
    }

    static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    public static Page<InvoiceDto> convertToDto(Page<Invoice> paginated) {
        Page<InvoiceDto> dtoList = mapEntityPageIntoDtoPage(paginated, InvoiceDto.class);
        return dtoList;
    }

    public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }


    public static ResponseEntity<?> convertToDto(ResponseEntity<?> res) {
        ResponseEntity<?> dto = modelMapper.map(res, ResponseEntity.class);
        return dto;
    }


    public static InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        return invoiceDto;
    }

    public static Invoice convertToEntity(@Valid InvoiceDto invoiceDto) throws ParseException {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        return invoice;
    }


    public static LoginRequest convertToEntity(@Valid LoginDto loginDto) throws ParseException {
        LoginRequest req = modelMapper.map(loginDto, LoginRequest.class);
        return req;
    }

    public static SignupRequest convertToEntity(@Valid SignUpDto req) throws ParseException {
        SignupRequest signup = modelMapper.map(req, SignupRequest.class);
        return signup;
    }

//    private ItemDto convertToDto(ResponseEntity<Item> item) {
//        ItemDto itemDto = modelMapper.map(item, ItemDto.class);
//        return itemDto;
//    }

    public static Item convertToEntity(@Valid ItemDto ItemDto) throws ParseException {
        Item item = modelMapper.map(ItemDto, Item.class);
        return item;
    }

    public static UserDto convertToDto(User responseEntity) {
        UserDto userDto = modelMapper.map(responseEntity, UserDto.class);
        return userDto;
    }

    public static User convertToEntity(@Valid UserDto userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public static List<UserDto> convertToDto(List<User> allUsers) {
        List<UserDto> userDtoList = mapList(allUsers, UserDto.class);
        return userDtoList;
    }


    public static Page<UserDto> convertToDtoUser(Page<User> paginatedUsers) {
        Page<UserDto> dtoList = mapEntityPageIntoDtoPage(paginatedUsers, UserDto.class);
        return dtoList;
    }

    public static FileDto convertToDto(File fileDB) {
        FileDto fileDto = modelMapper.map(fileDB, FileDto.class);
        return fileDto;
    }
}
