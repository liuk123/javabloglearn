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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param authentication
     * @return
     */
    @GetMapping(path="/bookmarkCategory/")
    public ResultSet getBookmarkCategory(Authentication authentication){
        User u = (User) authentication.getPrincipal();
        List<BookmarkCategory> n = bookmarkCategoryService.findBookmarkCategory();
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @GetMapping(path="/")
    public ResultSet getBookmarks(Authentication authentication, @RequestParam Long id){
        User u = (User) authentication.getPrincipal();
        List<BookmarkCategory> n = bookmarkCategoryService.findByPid(id);
        for(BookmarkCategory b: n){
            b.setBookmarkList(new ArrayList<>(b.getBookmarkList()));
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }

    @GetMapping(path="/bookmarkItem/")
    public ResultSet getBookmark(Authentication authentication, @RequestParam List<Long> ids){
        List<Bookmark> n = bookmarkService.findBookmarkByCIds(ids);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "查询成功", n);
    }


    @PostMapping(path = "/bookmarkCategory/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNavCategory(@RequestBody @Valid NewBookmarkCategoryRequest bookmarkCategory, Authentication authentication){
        if(authentication == null){
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "登录",null);
        }
        User u = (User) authentication.getPrincipal();
        BookmarkCategory c = bookmarkCategoryService.save(bookmarkCategory.getId(),bookmarkCategory.getPid(),bookmarkCategory.getSort(),bookmarkCategory.getTitle(), bookmarkCategory.getIcon());
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",c);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet saveNav(@RequestBody @Valid NewBookmarkRequest bookmarkRequest){
        BookmarkCategory nc = new BookmarkCategory(bookmarkRequest.getCategoryId());
        Bookmark n = bookmarkService.save(bookmarkRequest.getId(),bookmarkRequest.getIcon(),bookmarkRequest.getTitle(),bookmarkRequest.getLink(), bookmarkRequest.getType(),nc);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功",n);
    }

    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam Long id){
        bookmarkService.delOne(id);
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
    @DeleteMapping(path = "/navCategory/")
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
