package com.lk.fishblog.service;

import com.lk.fishblog.model.Amount;
import com.lk.fishblog.repository.AmountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AmountService {

    final
    AmountRepository amountRepository;

    public AmountService(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }

    public Amount save(Long id, String name, Long amount){
        return amountRepository.save(
                Amount.builder().id(id).name(name).amount(amount).build()
        );
    }
    public Amount findByName(String name){
        return amountRepository.findFirstByName(name);
    }
    public void delOne(Long id){
        amountRepository.deleteById(id);
    }
}
