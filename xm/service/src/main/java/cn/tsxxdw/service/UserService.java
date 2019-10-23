package cn.tsxxdw.service;

import cn.tsxxdw.entity.UserEntity;
import cn.tsxxdw.entity.UserFa;
import cn.tsxxdw.mybese.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户表(TUser)表服务接口
 *
 * @author makejava
 * @since 2019-08-09 23:02:38
 */

@Slf4j
@Service
//这里 WxUserEntity 修改为  UserEntity就没报错了e
public class UserService extends BaseService<UserEntity, BaseMapper<UserEntity>> {
//    Supplier<UserEntity> userEntitySupplier = UserEntity::new;
//
//
//    private String appsecret = "af14dce8b373b6cd9b6c9c45b780d645";
//
//    private String appid = "wx875e3bd02183d25c";
//    @Autowired
//    private RestTemplate restTemplate;
//
//    /**
//     * 小程序登录
//     *
//     * @return
//     */
//    public ResultVo smallProcedureLogin(WxDto wxDto) {
//        try {
//            wxDto.setAppid(appid);
//            wxDto.setAppSecret(appsecret);
//            wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
//            //判断是否存在openid
//            UserEntity userEntity = selectOne(Where.useNullSafe(UserEntity.class).eq("openid", wxDto.getOpenid()));
//            if (userEntity != null) {
//                ResultVo resultVo = new ResultVo().setSuccess(MyBeanUtils.copyPropertiesAndResTarget(userEntity, UserDto::new));
//                return resultVo;
//            } else {//如果数据库没有该用户，则创建
//                return ResultVo.createSimpleFailResult();
//            }
//
//        } catch (Exception e) {
//            MyLogUtil.logError(this.getClass(), e);
//            return ResultVo.createSystemErrorResult();
//        }
//    }
//
//    /**
//     * 注册
//     *
//     * @param wxDto
//     * @return
//     */
//    public ResultVo smallProcedureRegister(WxDto wxDto) throws Exception {
//        wxDto.setAppid(appid);
//        wxDto.setAppSecret(appsecret);
//        wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
//        WxUserDto wxUserDto = WeiXinUtil.getDecryptData(wxDto).getData();
//        UserEntity userEntity = MyBeanUtils.copyPropertiesAndResTarget(wxUserDto, UserEntity::new, o -> o.setId(MyStrUtils.getIdDateStr()));
//        //判断是否注册过
//        UserEntity userEntity1 = selectOne(Where.useNullSafe(UserEntity.class).eq("openid", userEntity.getOpenid()));
//        if (userEntity1 == null) {
//            //如果不存在，则添加
//            super.insert(userEntity);
//        }
//        Optional.ofNullable(userEntity1).ifPresent(o->{
//            BeanUtils.copyProperties(o,wxUserDto);
//        });
//        return new ResultVo().setSuccess(wxUserDto);
//    }
//
//    /**
//     * 修改信息
//     *
//     * @param wxDto
//     * @return
//     */
//    public ResultVo smallProcedureUpdateInfo(WxDto wxDto) throws Exception {
//        wxDto.setAppid(appid);
//        wxDto.setAppSecret(appsecret);
//        wxDto = WeiXinUtil.getJscode2session(restTemplate, wxDto);
//        WxUserDto wxUserDto = WeiXinUtil.getDecryptData(wxDto).getData();
//        UserEntity userEntity = MyBeanUtils.copyPropertiesAndResTarget(wxUserDto, UserEntity::new, o -> o.setId(MyStrUtils.getIdDateStr()));
//        super.update(userEntity, Where.useNullSafe(UserEntity.class).eq("openid", wxDto.getOpenid()));
//        return new ResultVo().setSuccess(wxUserDto);
//    }
}