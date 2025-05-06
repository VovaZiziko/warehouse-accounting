package com.example.warehouse_accounting.model;
// расходная накладная

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "outgoing_waybills")
@Data
@NoArgsConstructor
@ToString
public class OutgoingWaybill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String outgoingWaybillNumber;
    private LocalDate outgoingWaybillDate;
    private String companyRepresentative;
    private String clientRepresentative;
    private String trust; //доверенность

    @OneToOne(mappedBy = "outgoingWaybill")
    private Contract contract;

    public void setContract(Contract contract) {
        this.contract = contract;
        if(contract != null) {
            contract.setOutgoingWaybill(this);
        }
    }

}
