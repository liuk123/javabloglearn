package com.lk.fishblog.common.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FileUtil {

    public void copyFile(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("/")+1); //目标文件地址
        System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void moveFile(String srcPathStr, String desPathStr) {
        try{
            File file = new File(srcPathStr);
            File newFile = new File(desPathStr);
            if(!newFile.isDirectory()){
                newFile.mkdirs();
            }
            if(file.renameTo(new File(desPathStr + File.separator + file.getName()))){
                System.out.println("File is moved successful!");//输出移动成功
            }else{
                System.out.println("File is failed to move !");//输出移动失败
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void delFile(String srcPathStr) {
        try{
            File file = new File(srcPathStr);
            if(file.delete()){
                System.out.println("url success" + srcPathStr);
            }else{
                System.out.println("url error" + srcPathStr);
            };
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
