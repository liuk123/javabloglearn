package com.lk.fishblog.common.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageInfo<T> extends ResultSet{

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 显示数量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 总条数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<T> list;

    public PageInfo(Page<T> page){
        super.resultCode = ResultSet.RESULT_CODE_TRUE;
        super.resultMsg = "查询成功";
        if(!page.isEmpty()){
            this.pageNum = page.getNumber();
            this.pageSize = page.getSize();
            this.pages = page.getTotalPages();
            this.total = page.getTotalElements();
            this.list = page.getContent();
        }
    }
}
