package com.example.warehouse_accounting.model;
//счет-договор

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private boolean isCurrent; // проведен/распроведен
    //private boolean isPaid;
    private int numberOfInvoice;
//    private LocalDate dateOfCreation;
//    @ManyToOne
//    @JoinColumn(name = "counteragent_id")
//    private Counteragent counteragent;
//    private String author;
//    private String textOfInvoice;
//    @OneToMany
//    private List<Product> products;
//    private double invoicePrice;
}
