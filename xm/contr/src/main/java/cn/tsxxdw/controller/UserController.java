package cn.tsxxdw.controller;

import cn.tsxxdw.dto.UserDto;
import cn.tsxxdw.mybese.wx.WxUserService;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import cn.tsxxdw.wechatbean.dto.WxDto;
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
    public ResultVo<UserDto> smallProcedureLogin(WxDto wxDto) {
        MyLogUtil.logInfo(this.getClass(), wxDto);

        ResultVo resultVo = wxUserService.smallProcedureLogin(wxDto);
        return resultVo;

    }
    @ResponseBody
    @RequestMapping(value = "/smallProcedure", method = RequestMethod.POST)
    public ResultVo<UserDto> smallProcedureRegister(@RequestBody WxDto wxDto) {
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
    public ResultVo<UserDto> smallProcedureUpdateInfo(@RequestBody WxDto wxDto) {
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
