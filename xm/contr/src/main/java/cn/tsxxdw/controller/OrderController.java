package cn.tsxxdw.controller;

import cn.tsxxdw.dto.OrderDto;
import cn.tsxxdw.dto.OrderQueryDto;
import cn.tsxxdw.dto.UserDto;
import cn.tsxxdw.service.OrderService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
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
            resultVo = orderService.queryList(orderQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }


    /**
     * 导入商品信息
     *
     * @return
     */
    @RequestMapping("/importProduct")
    public String importProduct(@RequestParam(value = "pic") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "file is null";
        }
        try {
            String fileName = file.getOriginalFilename();
            String path = System.getenv("TEMP") + File.separator + "upload";
            File dir = new File(path);
            if (!dir.exists()) {// 如果目录不存在就创建目录
                dir.mkdirs();
            }
            InputStream stream = file.getInputStream();
            // 上传文件
            IOUtils.copy(stream, new FileOutputStream(path + File.separator + fileName));

        } catch (Exception e) {
            log.error("importProduct:", e);
            e.printStackTrace();
        }
        return "product/uploadsuccess";
    }
}
