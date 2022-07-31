package com.bxbro.sun.common.domain.query;


import lombok.Data;

@Data
public class BasicQuery {
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 页数
     */
    private Integer pageSize;

}
