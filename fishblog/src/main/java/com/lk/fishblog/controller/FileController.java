package com.lk.fishblog.controller;

import com.lk.fishblog.common.utils.FileUtil;
import com.lk.fishblog.common.utils.ResultSet;
import com.lk.fishblog.controller.request.NewFileResponse;
import com.lk.fishblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    final
    ArticleService articleService;
    @Autowired
    FileUtil fileUtil;

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.temPath}")
    private String uploadTemPath;

    public FileController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addFile(@RequestParam(value = "uploadFile") MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "文件不可为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String realPath = uploadTemPath;
        String format = sdf.format(new Date());
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
            String os = System.getProperty("os.name");
            String filePath;
            if (os.toLowerCase().startsWith("win")){
                filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"+ uploadTemPath + format + fileName;
            }else{
                filePath = request.getScheme() + "://www.cicode.cn/api/"+ uploadTemPath + format + fileName;
            }
            NewFileResponse f = new NewFileResponse();
            f.setName(file.getOriginalFilename());
            f.setUrl(filePath);
            return new ResultSet(ResultSet.RESULT_CODE_TRUE, "上传成功", f);
        }catch (IOException e){
            e.printStackTrace();
        }

        return new ResultSet(ResultSet.RESULT_CODE_FALSE, "上传失败");
    }

    @PostMapping(path = "/uploadMultipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultSet addFiles(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("uploadFile");
        if(files.isEmpty()){
            return new ResultSet(ResultSet.RESULT_CODE_FALSE, "文件不可为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String realPath = uploadTemPath;
        String format = sdf.format(new Date());
        File realFile = new File(realPath + format);
        List<NewFileResponse> fileList = new ArrayList<>();
        for(MultipartFile file:files){
            if(file.isEmpty()){
                return new ResultSet(ResultSet.RESULT_CODE_FALSE, "文件不可为空");
            }else{
                String fileName = file.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
                fileName = UUID.randomUUID() + fileSuffix;
                File newFile = new File(realFile.getAbsolutePath() + File.separator + fileName);
                if(!newFile.isDirectory()){
                    newFile.mkdirs();
                }
                try{
                    file.transferTo(newFile);
                    String os = System.getProperty("os.name");
                    String filePath;
                    if (os.toLowerCase().startsWith("win")){
                        filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"+ uploadTemPath + format + fileName;
                    }else{
                        filePath = request.getScheme() + "://www.cicode.cn/api/"+ uploadTemPath + format + fileName;
                    }
//                    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"+uploadTemPath + format + fileName;
                    NewFileResponse f = new NewFileResponse();
                    f.setName(file.getOriginalFilename());
                    f.setUrl(filePath);
                    fileList.add(f);
                }catch (IOException e){
                    e.printStackTrace();
                    return new ResultSet(ResultSet.RESULT_CODE_FALSE, "上传失败");
                }
            }
        }
        return new ResultSet(ResultSet.RESULT_CODE_TRUE, "上传成功", fileList);
    }

    @GetMapping(path = "/download")
    public String downLoad(HttpServletResponse response, @RequestParam String filePath) throws UnsupportedEncodingException {
        String realPath = uploadPath;
        File file = new File(realPath + "/" + filePath);
        if(file.exists()){ //判断文件父目录是否存在
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/")),"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 删除文件(需要优化)
     * @param urls 文件路径
     */
    @DeleteMapping(path = "/")
    public ResultSet delById(@RequestParam String[] urls){
        List<String> r = new ArrayList<>();
        for(String url:urls){
            fileUtil.delFile(url);
        }
        return  new ResultSet(ResultSet.RESULT_CODE_TRUE, "删除成功");
    }
}
