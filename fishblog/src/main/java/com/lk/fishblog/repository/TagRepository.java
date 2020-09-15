package com.lk.fishblog.repository;

import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllBy();
}
