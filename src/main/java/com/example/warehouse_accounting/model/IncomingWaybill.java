package com.example.warehouse_accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "incoming_waybills")
@Data
@NoArgsConstructor
@ToString
public class IncomingWaybill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String incomingWaybillNumber;


    private LocalDate incomingWaybillDate;
    //private String incomingWaybillDate;
    private String counteragentName;
    private double totalCost;
    private double netCost;
    private double totalVat;
    @OneToMany(mappedBy = "incomingWaybill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncomingWaybillProduct> incomingWaybillProducts = new ArrayList<>();

}
