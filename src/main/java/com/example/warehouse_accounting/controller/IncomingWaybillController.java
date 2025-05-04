package com.example.warehouse_accounting.controller;


import com.example.warehouse_accounting.dto.IncomingProductCreationDTO;
import com.example.warehouse_accounting.dto.IncomingWaybillCreationDTO;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.service.CounteragentService;
import com.example.warehouse_accounting.service.IncomingWaybillService;
import com.example.warehouse_accounting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
@RequestMapping("incoming_waybills")
public class IncomingWaybillController {
    private final IncomingWaybillService service;
    private final CounteragentService counteragentService;
    private final ProductService productService;

    @GetMapping
    public String getIncomingWaybills(Model model) {
        model.addAttribute("incomingWaybills", service.findAll());
        return "incomingwaybills/incoming_waybills";
    }

    @GetMapping("/new")
    public String newIncomingWaybill(Model model) {
        model.addAttribute("incomingWaybillDTO", new IncomingWaybillCreationDTO());
        model.addAttribute("counteragents", counteragentService.getCounteragents());
        return "incomingwaybills/create_form";
    }

    @PostMapping("/new/products")
    public String newIncomingWaybillProduct (@ModelAttribute ("incomingWaybillDTO") IncomingWaybillCreationDTO incomingWaybillDTO,
                                             Model model) {
        model.addAttribute("availableProducts", productService.getProducts());
        IncomingProductCreationDTO productsForm = new IncomingProductCreationDTO();
        for (int i = 0; i < 3; i++) productsForm.addProduct(new IncomingWaybillProduct());
        model.addAttribute("productsForm", productsForm);
        return "incomingwaybills/add_products_form";
    }

    @PostMapping
    public String createIncomingWaybill
    (@ModelAttribute ("incomingWaybillNumber") String incomingWaybillNumber, @ModelAttribute("counteragentName") String counteragentName,
     @ModelAttribute("incomingWaybillDate") LocalDate incomingWaybillDate,
     @ModelAttribute ("productsForm") IncomingProductCreationDTO productsForm) {

        service.createIncomingWaybill(incomingWaybillNumber, counteragentName, incomingWaybillDate, productsForm);
        productService.updateProductCostAndQuantity(productsForm);
        return  "redirect:/incoming_waybills";
    }





//    @GetMapping("/edit/{id}")
//    public String editIncomingWaybill(@PathVariable Long id, Model model) {
//        model.addAttribute("incomingWaybill", service.getIncomingWaybill(id));
//        return "incomingwaybills/edit_form";
//    }
//
//    @PostMapping("/{id}")
//    public String updateIncomingWaybill(@PathVariable Long id, @ModelAttribute IncomingWaybill incomingWaybill) {
//        return "redirect:/incoming_waybills";
//    }

    @GetMapping("/{id}")
    public String deleteIncomingWaybill(@PathVariable Long id) {
        service.deleteIncomingWaybill(id);
        return "redirect:/incoming_waybills";
    }





}
