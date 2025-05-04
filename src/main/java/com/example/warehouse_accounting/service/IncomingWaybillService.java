package com.example.warehouse_accounting.service;


import com.example.warehouse_accounting.dto.IncomingProductCreationDTO;
import com.example.warehouse_accounting.model.IncomingWaybill;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.repository.IncomingWaybillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.IteratorUtils.forEach;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomingWaybillService {

    private final IncomingWaybillRepository incomingWaybillRepository;

    public List<IncomingWaybill> findAll() {
        return incomingWaybillRepository.findAll();
    }

    public IncomingWaybill getIncomingWaybill(Long id) {
        return incomingWaybillRepository.getReferenceById(id);
    }

    public IncomingWaybill createIncomingWaybill(String incomingWaybillNumber, String counteragentName,
                                                 LocalDate incomingWaybillDate, IncomingProductCreationDTO productsForm) {

        IncomingWaybill waybill = new IncomingWaybill();
        waybill.setIncomingWaybillNumber(incomingWaybillNumber);
        waybill.setCounteragentName(counteragentName);
        waybill.setIncomingWaybillDate(incomingWaybillDate);
        List<IncomingWaybillProduct> products = productsForm.getProducts();

        for (IncomingWaybillProduct product : products) {
            product.setNetCost(Math.round((product.getPurchasePrice() * product.getQuantity()) * 100.00)/100.00);
            product.setVatAmount(Math.round((product.getNetCost() * product.getVatRate() / 100)* 100.00)/100.00);
            product.setTotalCost(Math.round((product.getNetCost() + product.getVatAmount())* 100.00)/100.00);
        }

        double netCost = products.stream()
                .mapToDouble(IncomingWaybillProduct::getNetCost)
                .sum();
        waybill.setNetCost(Math.round(netCost* 100.00)/100.00);

        double totalVat = products.stream()
                .mapToDouble(IncomingWaybillProduct::getVatAmount)
                .sum();
        waybill.setTotalVat(Math.round(totalVat* 100.00)/100.00);

        double totalCost = products.stream()
                .mapToDouble(IncomingWaybillProduct::getTotalCost)
                .sum();
        waybill.setTotalCost(Math.round(totalCost* 100.00)/100.00);
        waybill.setIncomingWaybillProducts(products);

        return incomingWaybillRepository.save(waybill);

    }









//    public IncomingWaybill updateIncomingWaybill(IncomingWaybill incomingWaybill) {
//        //логика
//        return incomingWaybillRepository.save(incomingWaybill);
//    }

    public void deleteIncomingWaybill(Long id) {
        incomingWaybillRepository.deleteById(id);
    }
}
