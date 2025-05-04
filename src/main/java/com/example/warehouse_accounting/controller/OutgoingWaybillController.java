package com.example.warehouse_accounting.controller;

import com.example.warehouse_accounting.dto.OutgoingWaybillCreationDTO;
import com.example.warehouse_accounting.model.Contract;
import com.example.warehouse_accounting.model.OutgoingWaybill;
import com.example.warehouse_accounting.service.ContractService;
import com.example.warehouse_accounting.service.OutgoingWaybillService;
import com.example.warehouse_accounting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/outgoing_waybills")
public class OutgoingWaybillController {

    private final OutgoingWaybillService outgoingWaybillService;
    private final ContractService contractService;
    private final ProductService productService;

    @GetMapping
    public String getOutgoingWaybills(Model model) {
        model.addAttribute("outgoingWaybills", outgoingWaybillService.getAllOutgoingWaybills());
        return "outgoingwaybills/outgoing_waybills";
    }

    @GetMapping("/new/{id}")
    public String newOutgoingWaybill(@PathVariable Long id, Model model) {
        //model.addAttribute("contract", contractService.getContractById(id));
        model.addAttribute("id", id);
        System.out.println("КЛАСС" + id.getClass());
        model.addAttribute("outgoingWaybillDTO", new OutgoingWaybillCreationDTO());
        return "outgoingwaybills/create_form";
    }

    @PostMapping
    public String createOutgoingWaybill(@ModelAttribute ("outgoingWaybillDTO") OutgoingWaybillCreationDTO outgoingWaybillDTO,
                                        @ModelAttribute("id") Long id) {
                                        //@ModelAttribute ("contract") Contract contract) {
        System.out.println("КЛАСС" + id.getClass());
        System.out.println("КЛАСС" + id);
        Contract contract = contractService.getContractById(id);
        outgoingWaybillService.createOutgoingWaybill(outgoingWaybillDTO, contract);
        productService.subtractProductQuantity(contract);
        return "redirect:/outgoing_waybills";
    }
}
