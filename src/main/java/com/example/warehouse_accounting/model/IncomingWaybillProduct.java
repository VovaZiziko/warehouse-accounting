package com.example.warehouse_accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "incoming_waybill_products")
@Data
@ToString
public class IncomingWaybillProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String unitOfMeasurement;
    private double purchasePrice;
    private double vatRate;
    private double quantity;

    //расчетные величины
    private double netCost;
    private double vatAmount;
    private double totalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incoming_waybill_id")
    private IncomingWaybill incomingWaybill;

}

