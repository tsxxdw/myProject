package cn.tsxxdw.dto;

import cn.tsxxdw.wechatbean.dto.WxDto;
import lombok.Data;

/**
 * 用户表(TUser)实体类
 *
 * @author makejava
 * @since 2019-08-09 22:56:53
 */
@Data
public class UserDto extends WxDto {
    private String identity;
}
