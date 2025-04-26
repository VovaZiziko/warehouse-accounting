package com.example.warehouse_accounting.controller;

import com.example.warehouse_accounting.model.Counteragent;
import com.example.warehouse_accounting.service.CounteragentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/counteragents")

public class CounteragentController {
    private final CounteragentService counteragentService;



    @GetMapping
    public String getCounteragents(Model model) {
        model.addAttribute("counteragents", counteragentService.getCounteragents());
        return "counteragents/counteragents";
    }

    @GetMapping("/new")
    public String newCounteragent(Model model) {
        Counteragent counteragent = new Counteragent();
        model.addAttribute("counteragent", counteragent);
        return "counteragents/create_form";

    }

    @PostMapping
    public String createCounteragent(@ModelAttribute("counteragent") Counteragent counteragent) {
        counteragentService.createCounteragent(counteragent);
        return "redirect:/counteragents";
    }


    @GetMapping("/edit/{id}")
    public String editCounteragent(@PathVariable Long id, Model model) {
        model.addAttribute("counteragent", counteragentService.getCounteragent(id));
        return "counteragents/edit_form";
    }

    @PostMapping("/{id}")
    public String updateCounteragent(@PathVariable Long id,
                                     @ModelAttribute("counteragent") Counteragent counteragent) {
        Counteragent existingCounteragent = counteragentService.getCounteragent(id);
        existingCounteragent.setId(id);
        existingCounteragent.setName(counteragent.getName());
        existingCounteragent.setTin(counteragent.getTin());
        existingCounteragent.setEmail(counteragent.getEmail());
        existingCounteragent.setAddress(counteragent.getAddress());
        existingCounteragent.setPhone(counteragent.getPhone());
        existingCounteragent.setBankAccount(counteragent.getBankAccount());
        existingCounteragent.setBankName(counteragent.getBankName());

        counteragentService.updateCounteragent(existingCounteragent);
        return "redirect:/counteragents";
    }

    @GetMapping("/{id}")
    public String deleteCounteragent(@PathVariable Long id) {
        counteragentService.deleteCounteragent(id);
        return "redirect:/counteragents";
    }

}
