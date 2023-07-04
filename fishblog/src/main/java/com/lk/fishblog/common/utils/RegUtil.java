package com.lk.fishblog.common.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegUtil {

    /**
     * 获取 markdown 中的图片
     * @param content 输入的文本
     * @return url数组
     */
    public List<String> extractUrls(String content) {
        List<String> result = new ArrayList<String>();
//        Pattern pattern = Pattern.compile("!\\[.*?]\\(([a-z\\-\\.0-9]+?)[\\?|\\)]");
        Pattern pattern = Pattern.compile("!\\[.*?]\\((https?://.*?)[\\?|\\)]");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
    /**
     * 正则表达式字符串替换
     * @param content 字符串
     * @param pattern 正则表达式
     * @param newString 新的替换字符串
     * @return 返回替换后的字符串
     */
    public String regReplace(String content,String pattern,String newString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        return m.replaceAll(newString);
    }
}
