package cn.tsxxdw.mybese.wx;

import cn.tsxxdw.mybese.BaseService;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.service.myspringbean.MyBeanUtils;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.service.wechat.WeiXinUtil;
import cn.tsxxdw.vo.ResultVo;
import cn.tsxxdw.wechatbean.dto.WxDto;
import cn.tsxxdw.wechatbean.dto.WxUserDto;
import cn.tsxxdw.wechatbean.entity.WxUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 用户表(TUser)表服务接口
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */

@Slf4j
@Service
//这里 WxWxUserEntity 修改为  WxUserEntity就没报错了e
public class WxUserService extends BaseService<WxUserEntity, WxUserMapper> {
    Supplier<WxUserEntity> WxUserEntitySupplier = WxUserEntity::new;


    private String appsecret = "0faa4e76e804c6dbf124cbf6782d5e31";

    private String appid = "wx836984aa6540b580";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 小程序登录
     *
     * @return
     */
    public ResultVo smallProcedureLogin(WxDto wxDto) {
        try {
            wxDto.setAppid(appid);
            wxDto.setAppSecret(appsecret);
            wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
            //判断是否存在openid
            WxUserEntity WxUserEntity = selectOne(Where.useNullSafe(WxUserEntity.class).eq("openid", wxDto.getOpenid()));
            if (WxUserEntity != null) {
                ResultVo resultVo = new ResultVo().setSuccess(MyBeanUtils.copyPropertiesAndResTarget(WxUserEntity, WxUserDto::new));
                return resultVo;
            } else {//如果数据库没有该用户，则创建
                return ResultVo.createSimpleFailResult();
            }

        } catch (Exception e) {
            MyLogUtil.logError(this.getClass(), e);
            return ResultVo.createSystemErrorResult();
        }
    }

    /**
     * 注册
     *
     * @param wxDto
     * @return
     */
    public ResultVo smallProcedureRegister(WxDto wxDto) throws Exception {
        wxDto.setAppid(appid);
        wxDto.setAppSecret(appsecret);
        wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
        WxUserDto wxUserDto = WeiXinUtil.getDecryptData(wxDto).getData();
        WxUserEntity WxUserEntity = MyBeanUtils.copyPropertiesAndResTarget(wxUserDto, WxUserEntity::new, o -> o.setId(MyStrUtils.getIdDateStr()));
        //判断是否注册过
        WxUserEntity WxUserEntity1 = selectOne(Where.useNullSafe(WxUserEntity.class).eq("openid", WxUserEntity.getOpenid()));
        if (WxUserEntity1 == null) {
            //如果不存在，则添加
            super.insert(WxUserEntity);
        }
        Optional.ofNullable(WxUserEntity1).ifPresent(o->{
            BeanUtils.copyProperties(o,wxUserDto);
        });
        return new ResultVo().setSuccess(wxUserDto);
    }

    /**
     * 修改信息
     *
     * @param wxDto
     * @return
     */
    public ResultVo smallProcedureUpdateInfo(WxDto wxDto) throws Exception {
        wxDto.setAppid(appid);
        wxDto.setAppSecret(appsecret);
        wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
        WxUserDto wxUserDto = WeiXinUtil.getDecryptData(wxDto).getData();
        WxUserEntity WxUserEntity = MyBeanUtils.copyPropertiesAndResTarget(wxUserDto, WxUserEntity::new, o -> o.setId(MyStrUtils.getIdDateStr()));
        super.update(WxUserEntity, Where.useNullSafe(WxUserEntity.class).eq("openid", wxDto.getOpenid()));
        return new ResultVo().setSuccess(wxUserDto);
    }
}