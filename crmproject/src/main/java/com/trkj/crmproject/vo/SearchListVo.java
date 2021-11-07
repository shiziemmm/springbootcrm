package com.trkj.crmproject.vo;

import lombok.Data;

@Data
public class SearchListVo {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页尺寸
     */
    private Integer pageSize;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 分类
     */
    private String classify;

}
