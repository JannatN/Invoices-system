package com.invoice.specification;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component

public class InvoiceSpecification {
    /**
     * @param
     * @return
     */
    public static Specification<Invoice> getInvoices( String s) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

if(s!=null) {

    predicates.add(criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")),
            "%" + s.toLowerCase() + "%"), criteriaBuilder.like(criteriaBuilder.lower(root.get("company")),
            "%" + s.toLowerCase() + "%")));
//                predicates.add(criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("company"),criteriaBuilder.like(criteriaBuilder.lower(root.get("type")))).
//                         "%"+s.toLowerCase()+"%")));
//
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")),
//                        "%"+s.toLowerCase()+"%" ));


}

            query.orderBy(criteriaBuilder.desc(root.get("date_created")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
