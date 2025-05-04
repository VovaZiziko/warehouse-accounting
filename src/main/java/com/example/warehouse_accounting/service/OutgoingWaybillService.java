package com.example.warehouse_accounting.service;


import com.example.warehouse_accounting.dto.OutgoingWaybillCreationDTO;
import com.example.warehouse_accounting.model.Contract;
import com.example.warehouse_accounting.model.OutgoingWaybill;
import com.example.warehouse_accounting.repository.OutgoingWaybillRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OutgoingWaybillService {
    private final OutgoingWaybillRepository outgoingWaybillRepository;

    public List<OutgoingWaybill> getAllOutgoingWaybills() {
        return outgoingWaybillRepository.findAll();
    }

    public OutgoingWaybill createOutgoingWaybill(OutgoingWaybillCreationDTO outgoingWaybillDTO, Contract contract) {
        OutgoingWaybill waybill = new OutgoingWaybill();
        waybill.setContract(contract);
        waybill.setOutgoingWaybillNumber(outgoingWaybillDTO.getOutgoingWaybillNumber());
        waybill.setOutgoingWaybillDate(outgoingWaybillDTO.getOutgoingWaybillDate());
        waybill.setCompanyRepresentative(outgoingWaybillDTO.getCompanyRepresentative());
        waybill.setClientRepresentative(outgoingWaybillDTO.getClientRepresentative());
        waybill.setTrust(outgoingWaybillDTO.getTrust());
        return outgoingWaybillRepository.save(waybill);
    }





    public void DeleteOutgoingWaybillById(long id) {
        outgoingWaybillRepository.deleteById(id);
    }
}
