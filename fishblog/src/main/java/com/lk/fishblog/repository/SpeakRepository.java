package com.lk.fishblog.repository;

import com.lk.fishblog.model.Speak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpeakRepository extends BaseRepository<Speak, Long> {
    Page<Speak> findByOrderByCreateTimeDesc(Pageable pageable);
}
