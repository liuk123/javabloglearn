package com.lk.fishblog.repository;

import com.lk.fishblog.model.Article;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ArticleRepository extends BaseRepository<Article, Long> {

}