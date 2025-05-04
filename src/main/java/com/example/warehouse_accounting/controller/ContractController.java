package com.example.warehouse_accounting.controller;


import com.example.warehouse_accounting.dto.ContractCreationDTO;
import com.example.warehouse_accounting.dto.ContractProductCreationDTO;
import com.example.warehouse_accounting.dto.IncomingProductCreationDTO;
import com.example.warehouse_accounting.model.ContractProduct;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.service.ContractService;
import com.example.warehouse_accounting.service.CounteragentService;
import com.example.warehouse_accounting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {
    private final ContractService contractService;
    private final ProductService productService;
    private final CounteragentService counteragentService;

    @GetMapping
    public String getContracts(Model model) {
        model.addAttribute("contracts", contractService.getAllContracts());
        return "contracts/contracts";
    }

    @GetMapping("/new")
    public String newContract(Model model) {
        model.addAttribute("contractDTO", new ContractCreationDTO());
        model.addAttribute("counteragents", counteragentService.getCounteragents());
        return "contracts/create_form";
    }

    @PostMapping("/new/products")
    public String newContractProduct(@ModelAttribute ("contractDTO") ContractCreationDTO contractDTO, Model model) {
        model.addAttribute("availableProducts", productService.getProducts());
        ContractProductCreationDTO productsForm = new ContractProductCreationDTO();
        for (int i = 0; i < 3; i++) productsForm.addProduct(new ContractProduct());
        model.addAttribute("productsForm", productsForm);
        return "contracts/add_products_form";
    }

    @PostMapping
    public String createContract
            (@ModelAttribute ("contractNumber") String contractNumber, @ModelAttribute("counteragentName") String counteragentName,
             @ModelAttribute("contractDate") LocalDate contractDate,
             @ModelAttribute ("productsForm") ContractProductCreationDTO productsForm,
             Model model) {

        contractService.createContract(contractNumber, counteragentName, contractDate, productsForm);
        productService.reserveProductQuantity(productsForm);
        return "redirect:/contracts";
    }



















    @GetMapping("/{id}")
    public String deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return "redirect:/contracts";
    }


}
