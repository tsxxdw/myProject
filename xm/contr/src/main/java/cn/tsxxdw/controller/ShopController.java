package cn.tsxxdw.controller;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.dto.ShopQueryDto;
import cn.tsxxdw.mybese.wx.WxUserService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.ShopService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import cn.tsxxdw.wechatbean.entity.WxUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private WxUserService wxUserService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productCategoryManagement(Model model) {
        return "shop/shop_add";
    }


    /**
     * 微信登录
     *
     * @param shopDtoList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo add(@RequestBody List<ShopDto> shopDtoList) {
        MyLogUtil.logInfo(this.getClass(), shopDtoList);
        try {
            if (wxUserService.query(Where.useNullSafe(WxUserEntity.class).eq("openid", shopDtoList.get(0).getOpenid())) == null) {
              return new ResultVo().setFail("用户编号错误");
            }

            shopDtoList.forEach(o -> {
                try {
                    shopService.add(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return ResultVo.createSimpleSuccessResult();
        } catch (Exception e) {
            MyLogUtil.logError(this.getClass(), e);
            return ResultVo.createSimpleFailResult();
        }

    }

    /**
     * 微信登录
     *
     * @param shopQueryDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultVo<ShopDto> get(ShopQueryDto shopQueryDto) {
        MyLogUtil.logInfo(this.getClass(), shopQueryDto);
        try {
            ResultVo resultVo = shopService.query(shopQueryDto);
            return resultVo;
        } catch (Exception e) {
            MyLogUtil.logError(this.getClass(), e);
            return ResultVo.createSimpleFailResult();
        }
    }

    /**
     * 更新
     *
     * @param shopDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResultVo<ShopDto> update(@RequestBody ShopDto shopDto) {
        MyLogUtil.logInfo(this.getClass(), shopDto);
        try {
            ResultVo resultVo = shopService.update(shopDto);
            return resultVo;
        } catch (Exception e) {
            MyLogUtil.logError(this.getClass(), e);
            return ResultVo.createSimpleFailResult();
        }
    }
}
