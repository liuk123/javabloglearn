package com.lk.fishblog.repository;
import com.lk.fishblog.model.Amount;

public interface AmountRepository extends BaseRepository<Amount, Long> {
    Boolean existsByName(String name);
    Amount findFirstByName(String name);
}
