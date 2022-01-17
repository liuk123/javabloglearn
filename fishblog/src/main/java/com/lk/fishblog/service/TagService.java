package com.lk.fishblog.service;

import com.lk.fishblog.model.Tag;
import com.lk.fishblog.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Tag save(String title, Long pid, Long sort){
        return tagRepository.save(
            Tag.builder().title(title).pid(pid).sort(sort).build()
        );
    }
    public void deleteById(Long id){
        tagRepository.deleteById(id);
    }
    public List<Tag> findAll(){ return tagRepository.findAll(); }
}
