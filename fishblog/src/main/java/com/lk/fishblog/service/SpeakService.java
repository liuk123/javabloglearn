package com.lk.fishblog.service;

import com.lk.fishblog.model.Speak;
import com.lk.fishblog.repository.SpeakRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class SpeakService {

    @Autowired
    SpeakRepository speakRepository;

    public Speak save(Long id, String icon, String title, String link, String category, String descItem, Long sort){
        return speakRepository.save(
                Speak.builder().id(id).icon(icon).title(title).link(link).category(category).descItem(descItem).sort(sort).build()
        );
    }
    public void delOne(Long id){
        speakRepository.deleteById(id);
    }
    public Page<Speak> findByPage(int pageNum, int pageSize){
        return this.speakRepository.findByOrderByCreateTimeDesc(PageRequest.of(pageNum, pageSize));
    }

    public Speak random(Long n){
        Long total = null;
        if(n==null){
            total = speakRepository.count();
        }else{
            total = n;
        }
        int idx = (int)(Math.random() * total);
        Page<Speak> page = speakRepository.findAll(PageRequest.of(idx, 1));
        Speak q = null;
        if (page.hasContent()) {
            q = page.getContent().get(0);
        }
        return q;
    }
}
