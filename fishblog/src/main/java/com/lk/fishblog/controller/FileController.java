package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.service.ArticleService;
import com.lk.fishblog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestParam MultipartFile file, HttpServletRequest request){
        Date date = new Date();
        String fileName = file.getOriginalFilename();
        int pointIndex = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(pointIndex+1);

        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "添加成功");
    }

}
