package com.lk.fishblog.repository;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllById(Long id);
}
