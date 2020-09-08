package com.lk.fishblog.common.utils;

import lombok.Data;

@Data
public class PageInfo {
    /**
     * 当前页
     */
    private Long current;
    /**
     * 显示数量
     */
    private Long size;
}
