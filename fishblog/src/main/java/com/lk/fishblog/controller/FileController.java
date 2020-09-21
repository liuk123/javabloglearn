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
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestParam(value = "uploadFile") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "文件不可为空");
        }
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + fileSuffix;
        String filePath = "D://temp-rainy//";
        File dest = new File(filePath + fileName);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        String path = "/temp-rainy/" + fileName;
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "上传成功", path);
    }
}
