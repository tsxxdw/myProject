package cn.tsxxdw.service;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.dto.ShopQueryDto;
import cn.tsxxdw.entity.ShopEntity;
import cn.tsxxdw.mybese.BaseService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.mydate.MyDateUtil;
import cn.tsxxdw.service.myspringbean.MyBeanUtils;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.vo.ResultVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户下单信息(TOrder)表服务接口
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */

@Slf4j
@Service
public class ShopService extends BaseService<ShopEntity, ShopMapper> {
    public ResultVo add(ShopDto shopDto) throws Exception {
        ShopEntity shopEntity = MyBeanUtils.copyPropertiesAndResTarget(shopDto, ShopEntity::new, o -> {
            o.setCallStatus("false");
        });
        add("shop", shopEntity);
        return ResultVo.createSimpleFailResult();
    }

    public ResultVo query(ShopQueryDto shopQueryDto) throws Exception {
        //根据openid 查询订单号
        List<ShopEntity> shopEntityList = super.queryListByPage(shopQueryDto, Where.useNullSafe(ShopEntity.class)).getData();
        List<ShopDto> shopDtoList= shopEntityList.stream().map(o->{
            ShopDto shopDto=  MyBeanUtils.copyPropertiesAndResTarget(o,ShopDto::new);
            shopDto.setCreateDate(MyDateUtil.date2String(o.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
            return shopDto;
        }).collect(Collectors.toList());
        return new ResultVo().setSuccess(shopDtoList);
    }


    public ResultVo update(ShopDto shopDto) {
       // shopEntity.setId(shopDto.getId());
        ShopEntity shopEntity =    MyBeanUtils.copyPropertiesAndResTarget(shopDto,ShopEntity::new);
       // shopEntity.setOpenid(shopDto.getOpenid());
        super.update(shopEntity, Where.useNullSafe(shopEntity.getClass()).eq("id", shopDto.getId()));
        return ResultVo.createSimpleSuccessResult();
    }

}