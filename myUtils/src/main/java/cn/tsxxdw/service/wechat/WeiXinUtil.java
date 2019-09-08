package cn.tsxxdw.service.wechat;


import cn.tsxxdw.service.myjson.MyJsonUtil;
import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;
import cn.tsxxdw.wechatbean.dto.WxDto;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * 认证
 */
@Slf4j
public class WeiXinUtil {
    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    private static String encodingFormat = "utf-8";

    /**
     * 认证
     *
     * @param restTemplate
     * @param reqWxDto
     * @return openid, session_key
     */
    public static WxDto getJscode2session(RestTemplate restTemplate, WxDto reqWxDto) {
        WxDto resWxDto = new WxDto();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID" +
                "&secret=APPSECRET" + "&js_code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", reqWxDto.getAppid());
        url = url.replace("APPSECRET", reqWxDto.getAppSecret());
        url = url.replace("CODE", reqWxDto.getCode());

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            String json = responseEntity.getBody();
            resWxDto =  MyJsonUtil.toPojo(json,WxDto.class);
            //获得session_key和openid

        } catch (Exception e) {
            log.error("getAccess_token.error:{}", e);
        }
        return resWxDto.copyData(reqWxDto);
    }


    /**
     * @return
     */
    public static ResultVo<WxDto> getDecryptData(WxDto wxDto) {
        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(wxDto.getEncryptedData());
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(wxDto.getSessionKey());
        //偏移量
        byte[] ivByte = Base64.decodeBase64(wxDto.getIv());


        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                WxDto wxDto1=     MyJsonUtil.toPojo(result,WxDto.class);
                return new ResultVo<WxDto>().setSuccess(wxDto1.copyData(wxDto));
            }
        } catch (Exception e) {
            MyLogUtil.logError(WeiXinUtil.class,e);
            return ResultVo.createSystemErrorResult();
        }
        return new ResultVo<>();//创建一个默认的结果

    }


}
