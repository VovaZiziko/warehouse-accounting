package com.example.warehouse_accounting.service;


import com.example.warehouse_accounting.model.IncomingWaybill;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.repository.IncomingWaybillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.IteratorUtils.forEach;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomingWaybillService {

    private final IncomingWaybillRepository incomingWaybillRepository;
    private final IncomingWaybillProductService incomingWaybillProductService;

    public List<IncomingWaybill> findAll() {
        return incomingWaybillRepository.findAll();
    }

    public IncomingWaybill getIncomingWaybill(Long id) {
        return incomingWaybillRepository.getReferenceById(id);
    }





    public IncomingWaybill createIncomingWaybill(IncomingWaybill incomingWaybill, List<IncomingWaybillProduct> list ) {
        List<IncomingWaybillProduct> newList = list;
        IncomingWaybill waybill = new IncomingWaybill();
        waybill.setId(incomingWaybill.getId());
        waybill.setIncomingWaybillNumber(incomingWaybill.getIncomingWaybillNumber());
        waybill.setIncomingWaybillDate(incomingWaybill.getIncomingWaybillDate());
        waybill.setCounteragentName(incomingWaybill.getCounteragentName());
        for (IncomingWaybillProduct product : newList) {
            product.setNetCost(product.getPurchasePrice() * product.getQuantity());
            product.setVatAmount(product.getNetCost() * product.getVatRate() / 100);
            product.setTotalCost(product.getNetCost() + product.getVatAmount());
        }
        double netCost= newList.stream().map(IncomingWaybillProduct::getNetCost)
                .reduce((double) 0, Double::sum);
        waybill.setNetCost(netCost);
        double vatAmount = newList.stream().map(IncomingWaybillProduct::getVatAmount)
                .reduce((double) 0, Double::sum);
        waybill.setTotalVat(vatAmount);
        double totalCost = newList.stream().map(IncomingWaybillProduct::getTotalCost)
                .reduce((double) 0, Double::sum);
        waybill.setTotalCost(totalCost);

        //waybill.setIncomingWaybillProducts(newList);



        return incomingWaybillRepository.save(waybill);
    }









    public IncomingWaybill updateIncomingWaybill(IncomingWaybill incomingWaybill) {
        //логика
        return incomingWaybillRepository.save(incomingWaybill);
    }

    public void deleteIncomingWaybill(Long id) {
        incomingWaybillRepository.deleteById(id);
    }
}
