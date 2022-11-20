package com.lk.fishblog.service;

import com.lk.fishblog.model.TagColumn;
import com.lk.fishblog.repository.TagColumnRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class TagColumnService {
    @Autowired
    TagColumnRepository tagColumnRepository;

    public TagColumn save(String title, Long sort){
        return tagColumnRepository.save(
            TagColumn.builder().title(title).sort(sort).build()
        );
    }
    public void deleteById(Long id){
        tagColumnRepository.deleteById(id);
    }
    public List<TagColumn> findAll(){ return tagColumnRepository.findAll(); }
}
