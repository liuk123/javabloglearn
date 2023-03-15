package com.lk.fishblog.service;

import com.lk.fishblog.model.Bookmark;
import com.lk.fishblog.model.Friend;
import com.lk.fishblog.repository.FriendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class FriendService {

    @Autowired
    FriendRepository friendRepository;

    public Friend save(Long id, String icon, String title, String link, String category, String descItem, Long sort){
        return friendRepository.save(
                Friend.builder().id(id).icon(icon).title(title).link(link).category(category).descItem(descItem).sort(sort).build()
        );
    }
    public List<Friend> findAll(){
        return friendRepository.findAllByOrderBySortAsc();
    }
    public void delOne(Long id){
        friendRepository.deleteById(id);
    }

    public Friend random(Long n){
        Long total = null;
        if(n==null){
            total = n;
        }else{
            total = friendRepository.count();
        }
        int idx = (int)(Math.random() * total);
        Page<Friend> page = friendRepository.findAll(PageRequest.of(idx, 1));
        Friend q = null;
        if (page.hasContent()) {
            q = page.getContent().get(0);
        }
        return q;
    }
}
