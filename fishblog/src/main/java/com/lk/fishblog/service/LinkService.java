package com.lk.fishblog.service;

import com.lk.fishblog.model.Link;
import com.lk.fishblog.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class LinkService {

    @Autowired
    LinkRepository linkRepository;

    public Link save(Long id, String icon, String title, String link, String category, String descItem){
        return linkRepository.save(
                Link.builder().id(id).icon(icon).title(title).link(link).category(category).descItem(descItem).build()
        );
    }
    public List<Link> findLink(){
        return linkRepository.findAllByOrderByCreateTimeDesc();
    }
    public void delOne(Long id){
        linkRepository.deleteById(id);
    }
}
