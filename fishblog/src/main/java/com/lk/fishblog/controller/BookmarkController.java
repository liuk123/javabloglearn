package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.PageInfo;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewBookmarkCategoryRequest;
import com.lk.fishblog.controller.request.NewBookmarkRequest;
import com.lk.fishblog.model.*;
import com.lk.fishblog.service.BookmarkCategoryService;
import com.lk.fishblog.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookmark")
@Slf4j
@CacheConfig(cacheNames = "BookmarkCateCache")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    BookmarkCategoryService bookmarkCategoryService;

    /**
     * 获取书签分类
     * @param
     * @return
     */
    @Cacheable(cacheNames = "bookmarkCat")
    @GetMapping(path="/bookmarkCategory/")
    public ResultSet getBookmarkCategory(){
        List<BookmarkCategory> n = bookmarkCategoryService.findBookmarkCategory();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @GetMapping(path="/categoryByPid/")
    @Cacheable(key="#id+'_'+#size", cacheNames = "categoryByPid")
    public ResultSet getBookmarks(@RequestParam Long id,@RequestParam(required = false) Long size){
        List<BookmarkCategory> n = bookmarkCategoryService.findByPid(id);
        if(size==null){
            for(BookmarkCategory b: n){
                b.setBookmarkList(new ArrayList<>(b.getBookmarkList()));
            }
        }else{
            for(BookmarkCategory b: n){
                List<Bookmark> bookmarkList = b.getBookmarkList().stream().limit(size).collect(Collectors.toList());
                b.setBookmarkList(bookmarkList);
            }
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @GetMapping(path="/categoryById/")
    @Cacheable(key="#id+'_'+#size", cacheNames = "categoryById")
    public ResultSet getBookmarksById(@RequestParam Long id,@RequestParam(required = false) Long size){
        List<BookmarkCategory> n = bookmarkCategoryService.findById(id);
        if(size==null){
            for(BookmarkCategory b: n){
                b.setBookmarkList(new ArrayList<>(b.getBookmarkList()));
            }
        }else{
            for(BookmarkCategory b: n){
                List<Bookmark> bookmarkList = b.getBookmarkList().stream().limit(size).collect(Collectors.toList());
                b.setBookmarkList(bookmarkList);
            }
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }
    @GetMapping(path="/random/")
    public ResultSet getRandomBookmark(@RequestParam(required = false) Long total){
        Bookmark n = bookmarkService.random(total);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @Cacheable(key="#ids", cacheNames = "bookmarkIds")
    @GetMapping(path="/bookmarkItem/")
    public PageInfo<Bookmark> getBookmark(@RequestParam List<Long> ids, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Page<Bookmark> n = bookmarkService.findBookmarkByCIds(ids, pageIndex-1, pageSize);
        PageInfo<Bookmark> page = new PageInfo<>(n);
        return page;

    }

    @PostMapping(path = "/bookmarkCategory/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNavCategory(@RequestBody @Valid NewBookmarkCategoryRequest bookmarkCategory){
        BookmarkCategory c = bookmarkCategoryService.save(bookmarkCategory.getId(),bookmarkCategory.getPid(),bookmarkCategory.getSort(),bookmarkCategory.getTitle(), bookmarkCategory.getIcon());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",c);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNav(@RequestBody @Valid NewBookmarkRequest bookmarkRequest){
        BookmarkCategory nc = new BookmarkCategory(bookmarkRequest.getCategoryId());
        Bookmark n = bookmarkService.save(bookmarkRequest.getId(),bookmarkRequest.getIcon(),bookmarkRequest.getTitle(),bookmarkRequest.getLink(), bookmarkRequest.getType(),nc, bookmarkRequest.getDescItem());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",n);
    }

    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam Long id){
        bookmarkService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
    @DeleteMapping(path = "/bookmarkCategory/")
    public ResultSet delNavCategoryById(@RequestParam Long id){
        bookmarkCategoryService.delOne(id);
        List<BookmarkCategory> ncList = bookmarkCategoryService.findByPid(id);
        for(BookmarkCategory item: ncList){
            item.setPid(null);
        }
        bookmarkCategoryService.saveAll(ncList);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
