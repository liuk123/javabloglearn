package com.lk.fishblog.service;

import com.lk.fishblog.model.Comment;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    public Reply findById(Long id){
        return replyRepository.getOne(id);
    }

    public Reply save(String content, Comment comment, User fromUser, User toUser){
        return replyRepository.save(
                Reply
                        .builder()
                        .content(content)
                        .comment(comment)
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );
    }
    public void deleteById(Long id){
        replyRepository.deleteById(id);
    }

}