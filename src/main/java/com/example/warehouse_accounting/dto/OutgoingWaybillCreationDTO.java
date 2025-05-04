package com.example.warehouse_accounting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutgoingWaybillCreationDTO {

    private String outgoingWaybillNumber;
    private LocalDate outgoingWaybillDate;
    private String companyRepresentative;
    private String clientRepresentative;
    private String trust;
}
