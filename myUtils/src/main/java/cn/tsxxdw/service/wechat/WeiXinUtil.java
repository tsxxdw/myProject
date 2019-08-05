package cn.tsxxdw.service.wechat;


import cn.tsxxdw.wechatbean.dto.WxDto;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class WeiXinUtil {

  /*  public static String getStartURLToGetCode() {

        String takenUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        takenUrl = takenUrl.replace("APPID", APPID);
        takenUrl = takenUrl.replace("REDIRECT_URI", "www.baidu,com");//重定向url
        //FIXME ： snsapi_userinfo
        takenUrl = takenUrl.replace("SCOPE", "snsapi_userinfo");
        System.out.println(takenUrl);
        return takenUrl;
    }*/

   /* public static OAuthInfoVo getAccess_token(RestTemplate restTemplate, String code) {


        String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
        authUrl = authUrl.replace("APPID", "wxdd6d991e583e72c6");
        authUrl = authUrl.replace("SECRET", "a8090183822029781624acd7c676e382");
        authUrl = authUrl.replace("CODE", code);
        OAuthInfoVo auth = null;
        try {
            ResponseEntity<OAuthInfoVo> res=    restTemplate.getForEntity(authUrl, OAuthInfoVo.class);

        } catch (Exception e) {
           log.error("getAccess_token.error:{}",e);
        }
        return auth;
    }
*/

    /**
     * 认证
     * @param restTemplate
     * @param reqWxDto
     * @return openid,session_key
     */
    public static WxDto getJscode2session(RestTemplate restTemplate,WxDto reqWxDto) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID" +
                "&secret=SECRET" + "&js_code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", "wxdd6d991e583e72c6");
        url = url.replace("SECRET", "a8090183822029781624acd7c676e382");
        url = url.replace("CODE", reqWxDto.getCode());
        WxDto resWxDto = null;

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            String json = responseEntity.getBody();
            resWxDto= JSON.parseObject(json,WxDto.class);
        } catch (Exception e) {
            log.error("getAccess_token.error:{}", e);
        }
        return resWxDto;

    }
}
