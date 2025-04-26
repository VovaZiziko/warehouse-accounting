package com.example.warehouse_accounting.controller;


import com.example.warehouse_accounting.model.Counteragent;
import com.example.warehouse_accounting.model.Product;
import com.example.warehouse_accounting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products/products";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "products/create_form";

    }

    @PostMapping
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "products/edit_form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
                                     @ModelAttribute("product") Product product,
                                     Model model) {
        Product existingProduct = productService.getProduct(id);
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setUnitOfMeasurement(product.getUnitOfMeasurement());
        existingProduct.setPurchasePrice(product.getPurchasePrice());
        existingProduct.setVatRate(product.getVatRate());
        existingProduct.setTotalQuantity(product.getTotalQuantity());
        existingProduct.setReservedQuantity(product.getReservedQuantity());

        productService.updateProduct(existingProduct);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }


}
