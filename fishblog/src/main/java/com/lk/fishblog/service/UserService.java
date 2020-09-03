package com.lk.fishblog.service;

import com.lk.fishblog.model.Reply;
import com.lk.fishblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    ReplyRepository replyRepository;

    public Reply insertOneReply(Reply r){
        return replyRepository.save(r);
    }

    public List<Reply> findAllByCommentId(Long cid){
        return replyRepository.findByTorridAndTorr(cid,1);
    }
    public List<Reply> findAllByReplyid(Long rid){
        return replyRepository.findByTorridAndTorr(rid,0);
    }
}
