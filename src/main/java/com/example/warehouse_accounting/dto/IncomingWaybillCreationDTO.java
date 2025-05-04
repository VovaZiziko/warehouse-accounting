package com.example.warehouse_accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingWaybillCreationDTO {

    private String incomingWaybillNumber;
    private LocalDate incomingWaybillDate;
    private String counteragentName;
}
