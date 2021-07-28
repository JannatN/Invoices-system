package com.invoice.specification;

import com.invoice.controllers.dto.InvoiceDto;
import com.invoice.entities.Invoice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component

public class InvoiceSpecification {
    /**
     * 
     * @param request
     * @return
     */
    public static Specification<Invoice> getInvoices(Invoice request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getId()));
            }
            if (request.getUserID() != null) {
                predicates.add(criteriaBuilder.equal(root.get("userID"), request.getUserID()));
            }
            if (request.getCompany() != null && !request.getCompany().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("company")),
                        "%" + request.getCompany().toLowerCase() + "%"));
            }
            if (request.getType() != null && !request.getType().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")),
                        "%" + request.getType().toLowerCase() + "%"));
            }
            if (request.getDate_created() != null) {
                predicates.add(criteriaBuilder.equal(root.get("date_created"), request.getDate_created()));
            }
            if (request.getDue_date() != null) {
                predicates.add(criteriaBuilder.equal(root.get("due_date"), request.getDue_date()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("date_created")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
