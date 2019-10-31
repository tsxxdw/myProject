package cn.tsxxdw.entity;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private String id;
    private Date createDate;
    private Date updateDate;
}
