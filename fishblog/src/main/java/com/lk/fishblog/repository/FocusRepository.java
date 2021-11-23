package com.lk.fishblog.repository;

import com.lk.fishblog.model.Focus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FocusRepository extends JpaRepository<Focus, Focus.FocusEmbeddable> {
    Page<Focus> findByUser_Id(Long userId, Pageable pageable);
    Focus findByUser_IdAndFocus_Id(Long userId, Long focusUserId);
}
