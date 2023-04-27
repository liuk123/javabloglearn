package com.lk.fishblog.service;

import com.lk.fishblog.model.BookmarkCategory;
import com.lk.fishblog.repository.BookmarkCategoryRepository;
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
public class BookmarkCategoryService {
    @Autowired
    BookmarkCategoryRepository bookmarkCategoryRepository;

    public BookmarkCategory save(Long id, Long pid, Long sort, String title, String icon){
        return bookmarkCategoryRepository.save(
                BookmarkCategory.builder().id(id).pid(pid).sort(sort).title(title).icon(icon).build()
        );
    }
    public List<BookmarkCategory> findBookmarkCategory(){
        return bookmarkCategoryRepository.findByOrderBySort();
    }
    public List<BookmarkCategory> findByPid(long pid){
        return bookmarkCategoryRepository.findByPidOrderBySort(pid);
    }
    public List<BookmarkCategory> findById(long id){
        return bookmarkCategoryRepository.findByIdOrderBySort(id);
    }
    public void delOne(Long id){
        bookmarkCategoryRepository.deleteFirstById(id);
    }
    public void saveAll(List<BookmarkCategory> bkList){
        bookmarkCategoryRepository.saveAll(bkList);
    }
}
