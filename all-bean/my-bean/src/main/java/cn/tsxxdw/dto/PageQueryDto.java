package cn.tsxxdw.dto;

import lombok.Data;

@Data
public class PageQueryDto {
    private Integer page; //当前页数
    private Integer limit; //每次查询的条数
    private Integer count;//总条数
}
