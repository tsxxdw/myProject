package cn.tsxxdw.service.wechat;

import cn.tsxxdw.mybean.vo.ResultVo;
import cn.tsxxdw.wechatbean.dto.WxDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * <p>User: qrn
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 * 描述: 解密
 */

@Slf4j
public class AesCbcUtil {
    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }
    private static String encodingFormat = "utf-8";

    /**
     * AES解密(获取小程序用户普通信息)
     */
    public static ResultVo<String> decrypt(WxDto wxDto) throws Exception {


        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(wxDto.getEncryptedData());
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(wxDto.getSession_key());
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
                return new ResultVo<String>().setSuccess(result);
            }
        } catch (Exception e) {
            log.error("AesCbcUtil.decrypt.error:{}", e);
            return ResultVo.createSystemErrorResult();
        }
        return new ResultVo<>();//创建一个默认的结果
    }

}
