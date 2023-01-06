package com.lk.fishblog.service;

import com.lk.fishblog.model.Bookmark;
import com.lk.fishblog.model.BookmarkCategory;
import com.lk.fishblog.repository.BookmarkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class BookmarkService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public Bookmark save(Long id, String icon, String title, String link, String type, BookmarkCategory bookmarkCategory, String descItem){
        return bookmarkRepository.save(
                Bookmark.builder().id(id).icon(icon).title(title).link(link).type(type).bookmarkCategory(bookmarkCategory).descItem(descItem).build()
        );
    }
    public List<Bookmark> findBookmarkByCIds(List<Long> ids){
        return bookmarkRepository.findByBookmarkCategory_IdIn(ids);
    }
    @Transactional
    public void delOne(Long id){
        bookmarkRepository.deleteById(id);
    }
}
