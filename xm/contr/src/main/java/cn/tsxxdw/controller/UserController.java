package cn.tsxxdw.controller;

import cn.tsxxdw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


//
//    /**
//     * 微信登录
//     *
//     * @param wxDto
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/smallProcedure", method = RequestMethod.GET)
//    public ResultVo<UserDto> smallProcedureLogin( WxDto wxDto) {
//        MyLogUtil.logInfo(this.getClass(), wxDto);
//        ResultVo resultVo = userService.smallProcedureLogin(wxDto);
//        return resultVo;
//
//    }
//    @ResponseBody
//    @RequestMapping(value = "/smallProcedure", method = RequestMethod.POST)
//    public ResultVo<UserDto> smallProcedureRegister(@RequestBody WxDto wxDto) {
//      try {
//          MyLogUtil.logInfo(this.getClass(), wxDto);
//          ResultVo resultVo = userService.smallProcedureRegister(wxDto);
//          return resultVo;
//
//      }catch (Exception e){
//          MyLogUtil.logError(this.getClass(),e);
//          return  ResultVo.createSystemErrorResult();
//      }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/smallProcedure", method = RequestMethod.PUT)
//    public ResultVo<UserDto> smallProcedureUpdateInfo(@RequestBody WxDto wxDto) {
//        try {
//            MyLogUtil.logInfo(this.getClass(), wxDto);
//            ResultVo resultVo = userService.smallProcedureUpdateInfo(wxDto);
//            return resultVo;
//
//        }catch (Exception e){
//            MyLogUtil.logError(this.getClass(),e);
//            return  ResultVo.createSystemErrorResult();
//        }
//    }
}
