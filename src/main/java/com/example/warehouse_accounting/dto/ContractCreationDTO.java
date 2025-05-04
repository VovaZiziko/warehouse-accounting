package com.example.warehouse_accounting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreationDTO {

    private String contractNumber;
    private LocalDate contractDate;
    private String counteragentName;
}
