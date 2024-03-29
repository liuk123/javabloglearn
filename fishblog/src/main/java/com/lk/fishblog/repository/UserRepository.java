package com.lk.fishblog.repository;

import com.lk.fishblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
    User findFirstById(Long id);
}
