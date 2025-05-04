package com.example.warehouse_accounting.dto;

import com.example.warehouse_accounting.model.ContractProduct;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractProductCreationDTO {

    private List<ContractProduct> products = new ArrayList<>();

    public void addProduct(ContractProduct product) {
        this.products.add(product);
    }
}
