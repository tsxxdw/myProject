package cn.tsxxdw.dto;

import lombok.Data;

@Data
public class PageQueryDto {
    private Integer page;
    private Integer limit;
    private Integer count;//总条数
}
