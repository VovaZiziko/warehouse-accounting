package com.example.warehouse_accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "products")
@Data
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@Column(nullable = false, unique = true)
    private String name;
    private String unitOfMeasurement;
    private double purchasePrice;
    private double vatRate;
    private double totalQuantity;
    private double reservedQuantity;
}
