package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewBookmarkCategoryRequest;
import com.lk.fishblog.controller.request.NewBookmarkRequest;
import com.lk.fishblog.model.*;
import com.lk.fishblog.service.BookmarkCategoryService;
import com.lk.fishblog.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
@Slf4j
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
    @GetMapping(path="/bookmarkCategory/")
    public ResultSet getBookmarkCategory(){
        List<BookmarkCategory> n = bookmarkCategoryService.findBookmarkCategory();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @GetMapping(path="/")
    public ResultSet getBookmarks(@RequestParam Long id){
        List<BookmarkCategory> n = bookmarkCategoryService.findByPid(id);
        for(BookmarkCategory b: n){
            b.setBookmarkList(new ArrayList<>(b.getBookmarkList()));
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @GetMapping(path="/bookmarkItem/")
    public ResultSet getBookmark(@RequestParam List<Long> ids){
        List<Bookmark> n = bookmarkService.findBookmarkByCIds(ids);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
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
