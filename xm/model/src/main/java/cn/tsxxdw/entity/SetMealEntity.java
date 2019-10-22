package cn.tsxxdw.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 搬家套餐表(TSetMeal)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:17
 */
@Data
public class SetMealEntity implements Serializable {
    private static final long serialVersionUID = -52568624603711480L;
    
    private String id;
    //搬家套餐的内容
    private String content;




}