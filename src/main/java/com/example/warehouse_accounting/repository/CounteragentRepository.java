package com.example.warehouse_accounting.repository;

import com.example.warehouse_accounting.model.Counteragent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CounteragentRepository extends JpaRepository<Counteragent, Long> {

    Counteragent findCounteragentById(long id);
    Counteragent findCounteragentByName(String name);
    Counteragent findCounteragentByTin(String tin);

}
