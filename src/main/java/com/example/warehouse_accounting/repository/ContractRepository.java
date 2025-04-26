package com.example.warehouse_accounting.repository;

import com.example.warehouse_accounting.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
