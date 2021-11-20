package com.lk.fishblog.repository;

import com.lk.fishblog.model.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    List<Collect> findByUser_Id(Long userId);
}
