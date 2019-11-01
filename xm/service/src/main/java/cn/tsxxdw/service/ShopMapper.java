package cn.tsxxdw.service;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.dto.ShopQueryDto;
import cn.tsxxdw.entity.ShopEntity;
import cn.tsxxdw.mybese.BaseService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.myspringbean.MyBeanUtils;
import cn.tsxxdw.vo.ResultVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户下单信息(TOrder)表服务接口
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */

@Mapper
public interface ShopMapper extends BaseMapper<ShopEntity> {

}