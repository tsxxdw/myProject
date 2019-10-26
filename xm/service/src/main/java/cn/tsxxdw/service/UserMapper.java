package cn.tsxxdw.service;


import cn.tsxxdw.entity.UserEntity;
import cn.tsxxdw.wechatbean.entity.WxUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<WxUserEntity> {












}
