package com.example.warehouse_accounting.service;

import com.example.warehouse_accounting.dto.ContractProductCreationDTO;
import com.example.warehouse_accounting.dto.IncomingProductCreationDTO;
import com.example.warehouse_accounting.model.Contract;
import com.example.warehouse_accounting.model.ContractProduct;
import com.example.warehouse_accounting.model.IncomingWaybillProduct;
import com.example.warehouse_accounting.model.Product;
import com.example.warehouse_accounting.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findProductById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProductCostAndQuantity(IncomingProductCreationDTO productsForm){
        List<IncomingWaybillProduct> incomingProducts = productsForm.getProducts();
        List<Product> products = getProducts();
        for (IncomingWaybillProduct incomingProduct : incomingProducts) {
            for (Product product : products) {
                if (incomingProduct.getName().equals(product.getName())) {
                    double totalCost = (incomingProduct.getNetCost()+ product.getPurchasePrice()*product.getTotalQuantity());
                    double totalQuantity = incomingProduct.getQuantity() + product.getTotalQuantity();
                    product.setPurchasePrice(Math.round(totalCost/totalQuantity * 100.00)/100.00);
                    product.setTotalQuantity(totalQuantity);
                    productRepository.save(product);
                }
            }
        }
    }

    public void reserveProductQuantity(ContractProductCreationDTO productsForm){
        List<ContractProduct> contractProducts = productsForm.getProducts();
        List<Product> products = getProducts();
        for (ContractProduct contractProduct : contractProducts) {
            for (Product product : products) {
                if (contractProduct.getName().equals(product.getName())) {
                    double reservedQuantity = contractProduct.getQuantity();
                    double totalQuantity = product.getTotalQuantity() - reservedQuantity;
                    product.setTotalQuantity(totalQuantity);
                    product.setReservedQuantity(reservedQuantity + product.getReservedQuantity());
                    productRepository.save(product);
                }
            }

        }
    }


    public void subtractProductQuantity(Contract contract){
        List<ContractProduct> contractProducts = contract.getContractProducts();
        List<Product> products = getProducts();
        for (ContractProduct contractProduct : contractProducts) {
            for (Product product : products) {
                if (contractProduct.getName().equals(product.getName())) {
                    double subtractQuantity = contractProduct.getQuantity();
                    double reservedQuantity = product.getReservedQuantity()-subtractQuantity;
                    product.setReservedQuantity(reservedQuantity);
                    productRepository.save(product);
                }
            }
        }
    }





    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
