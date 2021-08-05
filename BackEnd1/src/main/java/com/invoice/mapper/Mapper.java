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
    static ModelMapper modelMapper;

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
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

//    private FileDto convertToDto(ResponseEntity<File> file) {
//        FileDto fileDto = modelMapper.map(file, FileDto.class);
//        return fileDto;
//    }

    private File convertToEntity(@Valid FileDto fileDto) throws ParseException {
        File file = modelMapper.map(fileDto, File.class);
        return file;
    }

//    private ResponseEntity<?> convertToDto(ResponseEntity<?> item) {
//        ResponseEntity<?> itemDto = modelMapper.map(item, ResponseEntity.class);
//        return itemDto;
//    }

    private LoginRequest convertToEntity(@Valid LoginDto loginDto) throws ParseException {
        LoginRequest req = modelMapper.map(loginDto, LoginRequest.class);
        return req;
    }

    private SignupRequest convertToEntity(@Valid SignUpDto req) throws ParseException {
        SignupRequest signup = modelMapper.map(req, SignupRequest.class);
        return signup;
    }

//    private ItemDto convertToDto(ResponseEntity<Item> item) {
//        ItemDto itemDto = modelMapper.map(item, ItemDto.class);
//        return itemDto;
//    }

    private Item convertToEntity(@Valid ItemDto ItemDto) throws ParseException {
        Item item = modelMapper.map(ItemDto, Item.class);
        return item;
    }

    private UserDto convertToDto(User responseEntity) {
        UserDto userDto = modelMapper.map(responseEntity, UserDto.class);
        return userDto;
    }

    private User convertToEntity(@Valid UserDto userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    private List<UserDto> convertToDto(List<User> allUsers) {
        List<UserDto> userDtoList = mapList(allUsers, UserDto.class);
        return userDtoList;
    }
}
