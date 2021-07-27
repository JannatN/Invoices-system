package com.invoice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.controllers.dto.UserDto;
import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;
public interface MapperDto<T>{
    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    public Class<T> convertToDto(Class<T> response);
    public Class<T> convertToEntity(@Valid Class<T>  response) throws ParseException;
    public List<T> convertToDto(List<T> all);
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass);

    }
