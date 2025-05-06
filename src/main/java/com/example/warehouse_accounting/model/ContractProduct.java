package com.example.warehouse_accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "contract_products")
@Data
@ToString
public class ContractProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    private String unitOfMeasurement;
    private double purchasePrice;
    private double salePrice;
    private double vatRate;
    private double quantity;

    //расчетные величины
    private double netCost;
    private double vatAmount;
    private double totalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

}
