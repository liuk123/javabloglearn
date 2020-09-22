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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    public final static String UPLOAD_PATH_PREFIX = "uploadFile/";

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addByJson(@RequestParam(value = "uploadFile") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "文件不可为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String realPath = UPLOAD_PATH_PREFIX;
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File realFile = new File(realPath + format);

        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + fileSuffix;
        File newFile = new File(realFile.getAbsolutePath() + File.separator + fileName);

        if(!newFile.isDirectory()){
            newFile.mkdirs();
        }
        try{
            file.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + fileName;
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "上传成功", filePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        return new ResultSet(ResultSet.RESULT_CODE_FALSE, "上传失败");
    }
}
