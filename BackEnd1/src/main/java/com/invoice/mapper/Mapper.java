package com.invoice.mapper;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.controllers.dto.UserDto;
import com.invoice.entities.Invoice;
import com.invoice.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper<T> {
    @Autowired
    private ModelMapper modelMapper;

    public Class<T> convertToDto(Class<T> responseEntity) {
        Class<T> objDto = modelMapper.map(responseEntity, Class.class);
        return objDto;
    }

    public Class<T> convertToEntity(@Valid Class objDto) throws ParseException {
        Class<T> obj = modelMapper.map(objDto, Class.class);
        return obj;
    }

    public List<Object> convertToDto(List<Object> all) {
        List<Object> dtoList = mapList(all, Object.class);
        return dtoList;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }


}
