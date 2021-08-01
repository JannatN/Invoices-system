package com.invoice.mapper;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.controllers.dto.Invoices_audDto;
import com.invoice.controllers.dto.UserDto;
import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import com.invoice.entities.invoices_aud;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

//    public Class<T> convertToDto(Class<T> responseEntity) {
//
//        Class<T> objDto = modelMapper.map(responseEntity, Class.class );
//        return objDto;
//    }
//
//    public Class<T> convertToEntity(@Valid Class objDto) throws ParseException {
//        Class<T> obj = modelMapper.map(objDto, Class.class);
//        return obj;
//    }
//
//    public List<Object> convertToDto(List<Object> all) {
//        List<Object> dtoList = mapList(all, Object.class);
//        return dtoList;
//    }
//
//    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
//        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
//    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    private Page<InvoiceDto> convertToDto(Page<Invoice> paginated) {
        Page<InvoiceDto> dtoList = mapEntityPageIntoDtoPage(paginated, InvoiceDto.class);
        return dtoList;
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }


    private ResponseEntity<InvoiceDto> convertToDto(ResponseEntity<Invoice> invoice) {
        ResponseEntity<InvoiceDto> invoiceDto = modelMapper.map(invoice, ResponseEntity.class);
        return invoiceDto;
    }


    private InvoiceDto convertToDto(Invoice invoice) {
        InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
        return invoiceDto;
    }

    private Invoice convertToEntity(@Valid InvoiceDto invoiceDto) throws ParseException {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        return invoice;
    }


}
