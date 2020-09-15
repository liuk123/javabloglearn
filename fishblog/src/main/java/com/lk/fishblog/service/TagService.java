package com.lk.fishblog.service;

import com.lk.fishblog.model.Tag;
import com.lk.fishblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Tag save(String name){
        return tagRepository.save(
            Tag.builder().name(name).build()
        );
    }
    public void deleteById(Long id){
        tagRepository.deleteById(id);
    }
    public List<Tag> findAll(){ return tagRepository.findAllBy(); }
}
