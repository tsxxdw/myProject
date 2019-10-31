package cn.tsxxdw.service;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.entity.ShopEntity;
import cn.tsxxdw.mybese.BaseService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.myspringbean.MyBeanUtils;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.vo.ResultVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户下单信息(TOrder)表服务接口
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */

@Slf4j
@Service
public class ShopService extends BaseService<ShopEntity, BaseMapper<ShopEntity>>{
    public ResultVo add(ShopDto shopDto) throws Exception {
        ShopEntity shopEntity=  MyBeanUtils.copyPropertiesAndResTarget(shopDto,ShopEntity::new);
        add("shop",shopEntity);
        return ResultVo.createSimpleFailResult();
    }

    public ResultVo get(OrderQueryDto orderQueryDto) throws Exception {
        //根据openid 查询订单号

        List<OrderEntity> orderEntityList = super.selectList(orderQueryDto,Where.useNullSafe(OrderEntity.class)).getData();
        return new ResultVo().setSuccess(orderEntityList);
    }


}