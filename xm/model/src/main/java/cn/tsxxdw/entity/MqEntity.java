package cn.tsxxdw.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 记录所有mq记录(TMq)实体类
 *
 * @author makejava
 * @since 2019-08-09 22:56:50
 */
@Data
public class MqEntity implements Serializable {
    private static final long serialVersionUID = -16784607467911608L;
    //
    private String id;
    //接收到的json数据
    private String jsonData;
    //这次接口的干什么的
    private String type;
    //描述
    private String message;
    
    private Date createDate;



}