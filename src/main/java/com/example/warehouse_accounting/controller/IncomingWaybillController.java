package com.example.warehouse_accounting.controller;


import com.example.warehouse_accounting.dto.IncomingProductCreationDTO;
import com.example.warehouse_accounting.model.Counteragent;
import com.example.warehouse_accounting.model.IncomingWaybill;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.service.CounteragentService;
import com.example.warehouse_accounting.service.IncomingWaybillProductService;
import com.example.warehouse_accounting.service.IncomingWaybillService;
import com.example.warehouse_accounting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("incoming_waybills")
public class IncomingWaybillController {
    private final IncomingWaybillService service;
    private final IncomingWaybillProductService waybillProductService;
    private final CounteragentService counteragentService;
    private final ProductService productService;

    @GetMapping
    public String getIncomingWaybills(Model model) {
        model.addAttribute("incomingWaybills", service.findAll());
        return "incomingwaybills/incoming_waybills";
    }

    @GetMapping("/new")
    public String newIncomingWaybill(Model model) {
        model.addAttribute("incomingWaybill", new IncomingWaybill());
        model.addAttribute("counteragents", counteragentService.getCounteragents());
        return "incomingwaybills/create_form";
    }


    @PostMapping("/new/products")
    public String newIncomingWaybillProduct (@ModelAttribute ("incomingWaybill") IncomingWaybill incomingWaybill,
                                             Model model) {
        IncomingProductCreationDTO productsForm = new IncomingProductCreationDTO();
        for (int i = 0; i < 3; i++) productsForm.addProduct(new IncomingWaybillProduct());
        model.addAttribute("productsForm", productsForm);
        return "incomingwaybills/add_products_form";
    }

    @PostMapping//дописать!!!
    public String createIncomingWaybill
            (@ModelAttribute ("incomingWaybill") IncomingWaybill incomingWaybill,
             @ModelAttribute ("productsForm") IncomingProductCreationDTO productsForm,
             Model model) {
        return "incomingwaybills/incoming_waybills";
    }


    @GetMapping("/edit/{id}")
    public String editIncomingWaybill(@PathVariable Long id, Model model) {
        model.addAttribute("incomingWaybill", service.getIncomingWaybill(id));
        return "incomingwaybills/edit_form";
    }

    @PostMapping("/{id}")
    public String updateIncomingWaybill(@PathVariable Long id, @ModelAttribute IncomingWaybill incomingWaybill) {
        return "redirect:/incoming_waybills";
    }

    @GetMapping("/{id}")
    public String deleteIncomingWaybill(@PathVariable Long id) {
        service.deleteIncomingWaybill(id);
        return "redirect:/incoming_waybills";
    }





}
