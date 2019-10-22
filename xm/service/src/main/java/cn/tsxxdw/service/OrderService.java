package cn.tsxxdw.service;

import cn.tsxxdw.dto.OrderDto;
import cn.tsxxdw.dto.OrderQueryDto;
import cn.tsxxdw.entity.OrderEntity;
import cn.tsxxdw.mapper.OrderMapper;
import cn.tsxxdw.mybese.BaseService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.myspringbean.MyBeanUtils;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.vo.ResultVo;
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
public class OrderService extends BaseService<OrderEntity, OrderMapper> {
    private final String unPaid="unPaid";
    public ResultVo add(OrderDto orderDto) throws Exception {
        OrderEntity orderEntity = MyBeanUtils.copyPropertiesAndResTarget(orderDto, OrderEntity::new,o->{
            o.setId(MyStrUtils.getIdDateStr("order"));
            o.setPayMoneyStatus(unPaid);//未付款
            o.setCreateDate(new Date());
        });
        insert(orderEntity);
        return ResultVo.createSimpleFailResult();
    }

    public ResultVo get(OrderQueryDto orderQueryDto) throws Exception {
        //根据openid 查询订单号

        List<OrderEntity> orderEntityList = super.selectList(orderQueryDto,Where.useNullSafe(OrderEntity.class)).getData();
        return new ResultVo().setSuccess(orderEntityList);
    }


}