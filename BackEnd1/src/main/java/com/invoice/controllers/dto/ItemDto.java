package com.invoice.controllers.dto;

import javax.validation.constraints.*;

public class ItemDto {
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @NotNull
    @DecimalMax("15.0") @DecimalMin("0.0")
    private Double price;

    @NotBlank
    @Size(max = 4)
    private String currency;

    @NotNull
    @Min(1)
    @Max(999999)
    private Integer quantity;

//	private Invoice invoice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
