package com.lk.fishblog.service;

import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Collect;
import com.lk.fishblog.model.Tag;
import com.lk.fishblog.model.User;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.CollectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CollectRepository collectRepository;

    public Article findById(Long id){
        return articleRepository.getOne(id);
    }
//    @Cacheable
    public Page<Article> findByAuthor(Long uerId, int pageNum, int pageSize){
        return this.articleRepository.findByAuthor_Id(uerId, PageRequest.of(pageNum, pageSize));
    }
    public Page<Article> findByAuthorAndCategory(Long uerId, Long categoryId, int pageNum, int pageSize){
        return this.articleRepository.findByAuthor_IdAndCategory_Id(uerId, categoryId, PageRequest.of(pageNum, pageSize));
    }
//    @Cacheable
    public Page<Article> findByTaglist(int pageNum, int pageSize, List<Tag> tagList){
        return this.articleRepository.findDistinctByTagListIn(tagList, PageRequest.of(pageNum, pageSize));
    }
    public Page<Article> findAll(int pageNum, int pageSize){
        return this.articleRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
    public Article save(Long id, String title, String content, String descItem, List<Tag> tagList, User auther, String postImage){
        return articleRepository.save(
            Article
                .builder()
                .title(title)
                .id(id)
                .content(content)
                .descItem(descItem)
                .tagList(tagList)
                .author(auther)
                .postImage(postImage)
                .build()
        );
    }
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

    /**
     * 保存收藏
     * @param user
     * @param article
     * @return
     */
    public Collect saveCollect(User user, Article article){
        return collectRepository.save(
                Collect.builder().user(user).article(article).build()
        );
    }

    /**
     * 获取收藏
     * @param id
     * @return
     */
    public Page<Collect> findCollectList(Long id, int pageNum, int pageSize){
        return collectRepository.findByUser_Id(id, PageRequest.of(pageNum, pageSize));
    }
    /**
     * 根据id查询收藏
     * @param
     * @return
     */
    public Collect findCollectById(Long userId, Long articleId){
        return collectRepository.findByUser_IdAndArticle_Id(userId, articleId);
    }

    /**
     * 删除收藏
     * @param collect
     */
    public void deleteCollect(Collect collect){
        collectRepository.delete(collect);
    }
}
