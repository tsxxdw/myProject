package cn.tsxxdw.service;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.dto.ShopQueryDto;
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

        return new ResultVo().setSuccess(shopEntityList);
    }


    public ResultVo update(ShopDto shopDto) {
        ShopEntity shopEntity = new ShopEntity();
       // shopEntity.setId(shopDto.getId());
        shopEntity.setCallStatus(shopDto.getCallStatus());
       // shopEntity.setOpenid(shopDto.getOpenid());
        super.update(shopEntity, Where.useNullSafe(shopEntity.getClass()).eq("openid", shopDto.getOpenid()).eq("id", shopDto.getId()));
        return ResultVo.createSimpleSuccessResult();
    }

}