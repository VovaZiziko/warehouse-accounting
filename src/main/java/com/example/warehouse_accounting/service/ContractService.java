package com.example.warehouse_accounting.service;


import com.example.warehouse_accounting.dto.ContractProductCreationDTO;
import com.example.warehouse_accounting.model.Contract;
import com.example.warehouse_accounting.model.ContractProduct;
import com.example.warehouse_accounting.model.IncomingWaybill;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }


    public Contract createContract(String contractNumber, String counteragentName
            , LocalDate contractDate, ContractProductCreationDTO productsForm) {

        Contract contract = new Contract();
        contract.setContractNumber(contractNumber);
        contract.setCounteragentName(counteragentName);
        contract.setContractDate(contractDate);
        List<ContractProduct> products = productsForm.getProducts();

        for (ContractProduct product : products) {
            product.setNetCost(Math.round((product.getSalePrice() * product.getQuantity()) * 100.00)/100.00);
            product.setVatAmount(Math.round((product.getNetCost() * product.getVatRate() / 100)* 100.00)/100.00);
            product.setTotalCost(Math.round((product.getNetCost() + product.getVatAmount())* 100.00)/100.00);
        }

        double netCost = products.stream()
                .mapToDouble(ContractProduct::getNetCost)
                .sum();
        contract.setNetCost(Math.round(netCost* 100.00)/100.00);

        double totalVat = products.stream()
                .mapToDouble(ContractProduct::getVatAmount)
                .sum();
        contract.setTotalVat(Math.round(totalVat* 100.00)/100.00);

        double totalCost = products.stream()
                .mapToDouble(ContractProduct::getTotalCost)
                .sum();
        contract.setTotalCost(Math.round(totalCost* 100.00)/100.00);
        contract.setContractProducts(products);

        return contractRepository.save(contract);
    }





















    public Contract getContractById(Long id) {
        return contractRepository.findById(id).get();
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }//дописать изменение количества
}
