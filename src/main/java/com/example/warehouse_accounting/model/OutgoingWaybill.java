package com.example.warehouse_accounting.model;
// расходная накладная

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
//@Table(name = "outcomingConsingmentNotes")
@Data
@ToString
public class OutgoingWaybill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne
//    @JoinColumn(name = "contract_id")
//    private Contract contract;
    private int numberOfBlank;
//    private String seriesOfBlank;
//    private LocalDate date;
//    private int powerOfAttorneyNumber;
//    private LocalDate powerOfAttorneyDate;
//    private String clientRepresentative;
//    private String myRepresentative;
//    private double outcomingTotalPrice;






}
