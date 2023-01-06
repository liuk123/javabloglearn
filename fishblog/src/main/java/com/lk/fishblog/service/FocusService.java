package com.lk.fishblog.service;

import com.lk.fishblog.model.*;
import com.lk.fishblog.repository.FocusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class FocusService {
    @Autowired
    FocusRepository focusRepository;

    /**
     * 保存关注
     * @param user
     * @param focusUser
     * @return
     */
    public Focus saveFocus(User user, User focusUser){
        return focusRepository.save(
                Focus.builder().user(user).focusUser(focusUser).build()
        );
    }

    /**
     * 获取关注
     * @param id
     * @return
     */
    public Page<Focus> findFocusList(Long id, int pageNum, int pageSize){
        return focusRepository.findByUser_Id(id, PageRequest.of(pageNum, pageSize));
    }

    /**
     * 删除关注
     * @param focus
     */
    public void deleteFocus(Focus focus){
        focusRepository.delete(focus);
    }

    /**
     * 获取某个关注
     * @param userId
     * @param FocusUserId
     * @return
     */
    public Boolean existsFocusById(Long userId, Long FocusUserId){
        return focusRepository.existsByUser_IdAndFocusUser_Id(userId, FocusUserId);
    }
}
