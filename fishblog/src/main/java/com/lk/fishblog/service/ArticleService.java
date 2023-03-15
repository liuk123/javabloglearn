package com.lk.fishblog.service;

import com.lk.fishblog.model.*;
import com.lk.fishblog.repository.ArticleRepository;
import com.lk.fishblog.repository.CollectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@CacheConfig(cacheNames = "ArticleCache")
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CollectRepository collectRepository;

    public Article findById(Long id){
        return articleRepository.findFirstById(id);
    }

    public Page<Article> findByAuthor(Long uerId, int pageNum, int pageSize){
        return this.articleRepository.findByAuthor_IdOrderByCreateTimeDesc(uerId, PageRequest.of(pageNum, pageSize));
    }
    public Page<Article> findByAuthorAndCategory(Long uerId, Long categoryId, int pageNum, int pageSize){
        return this.articleRepository.findByAuthor_IdAndCategory_IdOrderByCreateTimeDesc(uerId, categoryId, PageRequest.of(pageNum, pageSize));
    }
    @Cacheable(key="#tagIds +'_'+ #pageNum+'_'+#pageSize", cacheNames = "art_tagList")
    public Page<Article> findByTagList(int pageNum, int pageSize, List<Long> tagIds){
        return this.articleRepository.findByTag_IdInOrderByCreateTimeDesc(tagIds, PageRequest.of(pageNum, pageSize));
    }

    @Cacheable(key="#tagColumnId +'_'+ #pageNum+'_'+#pageSize", cacheNames = "art_tagCol")
    public Page<Article> findByTagColumn(int pageNum, int pageSize, Long tagColumnId){
        return this.articleRepository.findByTagColumn_IdOrderByCreateTimeDesc(tagColumnId, PageRequest.of(pageNum, pageSize));
    }
    @Cacheable(key="#pageNum+'_'+#pageSize", cacheNames = "art")
    public Page<Article> findAllByPage(int pageNum, int pageSize){
        return this.articleRepository.findByOrderByCreateTimeDesc(PageRequest.of(pageNum, pageSize));
    }
    public Article save(Long id, String title, String content, String descItem, Tag tag, Category category, User author, String postImage, TagColumn tagColumn, String keyword){

        return articleRepository.save(
            Article
                .builder()
                .title(title)
                .id(id)
                .content(content)
                .descItem(descItem)
                .tag(tag)
                .tagColumn(tagColumn)
                .category(category)
                .author(author)
                .postImage(postImage)
                .keyword(keyword)
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
    public Boolean existsCollectById(Long userId, Long articleId){
        return collectRepository.existsByUser_IdAndArticle_Id(userId, articleId);
    }

    /**
     * 删除收藏
     * @param collect
     */
    public void deleteCollect(Collect collect){
        collectRepository.delete(collect);
    }

    public Article random(Long n){
        Long total = null;
        if(n==null){
            total = n;
        }else{
            total = articleRepository.count();
        }
        int idx = (int)(Math.random() * total);
        Page<Article> page = articleRepository.findAll(PageRequest.of(idx, 1));
        Article q = null;
        if (page.hasContent()) {
            q = page.getContent().get(0);
        }
        return q;
    }
}
