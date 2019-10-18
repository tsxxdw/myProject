package cn.tsxxdw.service.pachong;

import cn.tsxxdw.service.mylog.MyLogUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * 专门用于百度图片下载
 */
public class BaiDuPicDownUtil {
    public static void main(String[] args) {
        String wordStr = "青椒炒肉 高清图片";
        String picJsonUrl = "http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&z=3&tn=baiduimage&ipn=r&word=" + wordStr + "&istype=2&ie=utf-8&oe=utf-8&cl=2&lm=-1&st=-1&fr=&fmq=1571205852647_R&ic=0&se=&sme=&width=0&height=0&face=0&hd=1&latest=0&copyright=0";
        String savePath = "D:\\duan\\file\\美菜图\\常见菜式\\热菜";
        downPic(picJsonUrl, savePath, 10);
    }


    /**
     * 百度图片所在的url,
     *
     * @param downFilePath
     * @param suffix
     * @param number
     */
    public static void downPic(String downFilePath, String saveFolderPath, int number) {
        Document document = null;
        try {
            document = Jsoup.connect(downFilePath).data("query", "Java")//请求参数
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                    .timeout(5000)
                    .get();
            Element element = document.body();
            String bodyStr = element.text();
            JSONObject jsonObject = (JSONObject) JSONObject.parse(bodyStr);
            JSONArray jsonArray = (JSONArray) jsonObject.get("imgs");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String picUrlPath = (String) jsonObject1.get("objURL");
                String filename = picUrlPath.substring(picUrlPath.lastIndexOf('/') + 1);
                String newFilePaht = saveFolderPath + File.separator + filename;
                if(i>=number)break;
               try {
                   download(picUrlPath, newFilePaht);
               }catch (Exception e){
                   number++;
                   MyLogUtil.logError(BaiDuPicDownUtil.class,downFilePath+",i:"+i);
               }
            }
        } catch (Exception e) {
            //MyLogUtil.logError(BaiDuPicDownUtil.class, e);
          //  MyLogUtil.logError(BaiDuPicDownUtil.class,downFilePath);
        }
    }

    private static void download(String urlList, String path) throws Exception {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            //String encode = encoder.encode(buffer);//返回Base64编码过的字节数组字符串
            //System.out.println(encode);
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
           // MyLogUtil.logError(BaiDuPicDownUtil.class,e);
            throw new Exception("下载异常");
        }
    }
}


