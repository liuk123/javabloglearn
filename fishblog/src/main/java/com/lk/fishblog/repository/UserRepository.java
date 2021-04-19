package com.lk.fishblog.repository;

import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByPhone(String phone);
    User findFirstByUsername(String username);
}
