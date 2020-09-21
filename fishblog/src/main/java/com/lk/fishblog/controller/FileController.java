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
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
        String filePath = "D://temp-rainy//";
        fileName = UUID.randomUUID() + fileSuffix;

        File dest = new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "上传成功", filename);
    }

}
