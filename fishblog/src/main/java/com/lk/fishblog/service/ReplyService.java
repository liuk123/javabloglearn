package com.lk.fishblog.service;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    public Reply insertOneReply(Reply r){
        return replyRepository.save(r);
    }

}
