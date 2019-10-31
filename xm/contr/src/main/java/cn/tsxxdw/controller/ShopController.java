package cn.tsxxdw.controller;

import cn.tsxxdw.service.ShopService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/order")
public class ShopController {
    @Autowired
    private ShopService orderService;
    /**
     * 微信登录
     *
     * @param orderDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo<UserDto> add(@RequestBody OrderDto orderDto) {
        MyLogUtil.logInfo(this.getClass(), orderDto);
        ResultVo resultVo = null;
        try {
            resultVo = orderService.add(orderDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;

    }
    /**
     * 微信登录
     *
     * @param orderQueryDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultVo<UserDto> get(OrderQueryDto orderQueryDto) {
        MyLogUtil.logInfo(this.getClass(), orderQueryDto);
        ResultVo resultVo = null;
        try {
            resultVo = orderService.get(orderQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }
}
