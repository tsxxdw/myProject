package cn.tsxxdw.mapper;

import cn.tsxxdw.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户下单信息(TOrder)表数据库访问层
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {



}