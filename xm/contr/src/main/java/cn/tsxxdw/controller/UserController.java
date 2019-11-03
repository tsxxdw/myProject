package cn.tsxxdw.controller;

import cn.tsxxdw.mybese.wx.WxUserService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.service.mymd5.MyMd5Util;
import cn.tsxxdw.vo.ResultVo;
import cn.tsxxdw.wechatbean.dto.WxDto;
import cn.tsxxdw.wechatbean.dto.WxUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private WxUserService wxUserService;



    /**
     * 微信登录
     *
     * @param wxDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/smallProcedure", method = RequestMethod.GET)
    public ResultVo<WxUserDto> smallProcedureLogin(WxDto wxDto) {
        MyLogUtil.logInfo(this.getClass(), wxDto);

        ResultVo resultVo = wxUserService.smallProcedureLogin(wxDto);
        return resultVo;

    }
    /**
     * 微信登录
     *
     * @param wxDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public ResultVo<String> get(WxDto wxDto) {
        MyLogUtil.logInfo(this.getClass(), wxDto);
       String md5Str= MyMd5Util.getMD5Str(wxDto.getOpenid());
        return new ResultVo<String>().setSuccess(md5Str);

    }
    @ResponseBody
    @RequestMapping(value = "/smallProcedure", method = RequestMethod.POST)
    public ResultVo<WxUserDto> smallProcedureRegister(@RequestBody WxDto wxDto) {
      try {
          MyLogUtil.logInfo(this.getClass(), wxDto);
          ResultVo resultVo = wxUserService.smallProcedureRegister(wxDto);
          return resultVo;

      }catch (Exception e){
          MyLogUtil.logError(this.getClass(),e);
          return  ResultVo.createSystemErrorResult();
      }
    }

    @ResponseBody
    @RequestMapping(value = "/smallProcedure", method = RequestMethod.PUT)
    public ResultVo<WxUserDto> smallProcedureUpdateInfo(@RequestBody WxDto wxDto) {
        try {
            MyLogUtil.logInfo(this.getClass(), wxDto);
            ResultVo resultVo = wxUserService.smallProcedureUpdateInfo(wxDto);
            return resultVo;

        }catch (Exception e){
            MyLogUtil.logError(this.getClass(),e);
            return  ResultVo.createSystemErrorResult();
        }
    }
}
