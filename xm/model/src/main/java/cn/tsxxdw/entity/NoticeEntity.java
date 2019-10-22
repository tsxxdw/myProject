package cn.tsxxdw.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 公告表(TNotice)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:16
 */
@Data
public class NoticeEntity implements Serializable {
    private static final long serialVersionUID = -21755748568175189L;
    
    private String id;
    //类型
    private String type;
    
    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}