package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Reply;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public Article insertOneReply(Reply r){
        return articleRepository.save(r);
    }

    public List<Article> findAllByCommentId(Long cid){
        return articleRepository.findByTorridAndTorr(cid,1);
    }
    public List<Article> findAllByReplyid(Long rid){
        return articleRepository.findByTorridAndTorr(rid,0);
    }
}
