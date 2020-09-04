package com.lk.fishblog.service;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    public Reply save(Reply r){
        return replyRepository.save(r);
    }
    public Optional<Reply> findById(Long id){
        return replyRepository.findById(id);
    }

}
