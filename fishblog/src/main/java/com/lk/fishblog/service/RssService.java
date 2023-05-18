package com.lk.fishblog.service;

import com.lk.fishblog.model.Link;
import com.lk.fishblog.model.Rss;
import com.lk.fishblog.repository.LinkRepository;
import com.lk.fishblog.repository.RssRepository;
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
public class RssService {

    @Autowired
    RssRepository rssRepository;

    public Rss save(Long id, String icon, String title, String link, String category, String descItem, Long sort){
        return rssRepository.save(
                Rss.builder().id(id).title(title).link(link).category(category).descItem(descItem).sort(sort).build()
        );
    }
    public Page<Rss> find(int pageNum, int pageSize){
        return rssRepository.findByOrderBySortDesc(PageRequest.of(pageNum, pageSize));
    }
    public List<Rss> findAll(){
        return rssRepository.findAllByOrderBySortDesc();
    }
    public void delOne(Long id){
        rssRepository.deleteById(id);
    }
}
