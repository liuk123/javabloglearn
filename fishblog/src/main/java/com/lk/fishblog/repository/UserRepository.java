package com.lk.fishblog.repository;

import com.lk.fishblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByPhone(String phone);
    User findFirstByUsername(String username);
}
