package cn.tsxxdw.controller;

import cn.tsxxdw.dto.ShopDto;
import cn.tsxxdw.dto.ShopQueryDto;
import cn.tsxxdw.service.ShopService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productCategoryManagement(Model model) {
        return "shop/shop_add";
    }



    /**
     * 微信登录
     *
     * @param shopDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo add(@RequestBody ShopDto shopDto) {
        MyLogUtil.logInfo(this.getClass(), shopDto);
        try {
            ResultVo resultVo = shopService.add(shopDto);
            return resultVo;
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
}
