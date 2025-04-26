package com.example.warehouse_accounting.service;


import com.example.warehouse_accounting.model.Counteragent;
import com.example.warehouse_accounting.repository.CounteragentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CounteragentService {
    private final CounteragentRepository counteragentRepository;

    public List<Counteragent> getCounteragents() {
        return counteragentRepository.findAll();
    }

    public Counteragent getCounteragent(Long id) {
        return counteragentRepository.findCounteragentById(id);
    }

    public Counteragent findCounteragentByName(String name) {
        return counteragentRepository.findCounteragentByName(name); //сделать Optional для поиска
    }

    public Counteragent findCounteragentByTin(String tin) {
        return counteragentRepository.findCounteragentByTin(tin);//сделать Optional для поиска
    }

    public void createCounteragent(Counteragent counteragent) {
        counteragentRepository.save(counteragent);
    }

    public void updateCounteragent(Counteragent counteragent) {
        counteragentRepository.save(counteragent);
    }
    public void deleteCounteragent(Long id) {
        counteragentRepository.deleteById(id);

    }


}
