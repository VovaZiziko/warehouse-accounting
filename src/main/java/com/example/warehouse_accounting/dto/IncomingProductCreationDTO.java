package com.example.warehouse_accounting.dto;

import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingProductCreationDTO {

    private List<IncomingWaybillProduct> products = new ArrayList<>();
    public void addProduct(IncomingWaybillProduct product) {

        this.products.add(product);
    }

}
