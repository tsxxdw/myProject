package cn.tsxxdw.mapper;

import cn.tsxxdw.entity.UserEntityt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(TUser)表数据库访问层
 *
 * @author makejava
 * @since 2019-08-09 23:02:37
 */
@Mapper
public interface UserMapper  extends BaseMapper<UserEntityt>{


}