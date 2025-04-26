package com.example.warehouse_accounting.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTOForInvoice {
    private long id;
    private String name;
    private String unitOfMeasurement;
    private double firstPrice;
    private double quantity;
    private double sellingPrice;


}
