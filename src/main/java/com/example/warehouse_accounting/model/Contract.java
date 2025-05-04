package com.example.warehouse_accounting.model;
//счет-договор

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "contracts")
@Data
@NoArgsConstructor
@ToString

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;
    private LocalDate contractDate;
    private String counteragentName;
    private double totalCost;
    private double netCost;
    private double totalVat;
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractProduct> contractProducts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "outgoing_waybill_id")
    private OutgoingWaybill outgoingWaybill;

    public void setContractProducts(List<ContractProduct> contractProducts) {
        this.contractProducts = contractProducts;
        for (ContractProduct contractProduct : contractProducts) {
            contractProduct.setContract(this);
        }
    }
}
