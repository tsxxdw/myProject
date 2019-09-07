package cn.tsxxdw.dto;

import lombok.Data;

@Data
public class BaseQueryDto extends PageQueryDto {
    private String searchContent;//搜索的内容
    private String orderByAsc;//升序排行的字段
    private String orderByDesc;//降序排行的字段
}
