package cn.tsxxdw.service.pachong;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:
 * @Date:2018/5/22
 */
public class SpiderPicturesFromBaiduByWord {


    public static void main(String[] args) throws Exception{
        String downloadPath = "D:"+File.separator+"duan"+File.separator+"file"+File.separator+"mct"+File.separator+"节日食俗"+File.separator;
        Map<String,List<String>> map=new HashMap<>();
        map.put("立冬",Arrays.asList("立冬饺子\n" +
                "南瓜饺子\n" +
                "烤山芋\n" +
                "虾仁玉米水饺\n" +
                "韭菜猪肉馅水饺\n" +
                "0技巧高颜值——玫瑰花饺子\n" +
                "西红柿土豆炖牛腩\n" +
                "鲜橙红焖羊肉\n" +
                "#信任之美#孜然羊肉\n" +
                "#信任之美#海米韭菜鹅蛋馅饺子\n" +
                "香菇烧麦\n" +
                "香菇焖菱角\n" +
                "西葫羊肉白菜型饺子\n" +
                "双椒香菇肉末\n" +
                "香菇炖鳊鱼\n" +
                "素得够味——香菇青菜包\n" +
                "栗子煮香菇\n" +
                "百合红薯糖水\n" +
                "香菇烧鲍鱼\n" +
                "迷迭香烤香菇\n" +
                "百合梅酱煎鸡翅\n" +
                "彩椒鲜肉饺子\n" +
                "百合拌香芹\n" +
                "百合南瓜糖水\n" +
                "紫菜虾皮饺子馄饨\n" +
                "猪肉茴香饺子\n" +
                "茴香饺子\n" +
                "枸杞百合绿豆粥\n" +
                "捞汁饺子皮\n" +
                "百合芦笋\n" +
                "丝瓜虾仁饺子\n" +
                "芹菜猪肉饺子\n" +
                "百合炒鱿鱼卷\n" +
                "山药百合银耳羹\n" +
                "百合西芹炒肉片\n" +
                "百合绿豆浆\n" +
                "西芹百合\n" +
                "羊肉灌汤饺子\n" +
                "萝卜馅饺子\n" +
                "翡翠白菜饺子\n" +
                "高汤饺子\n" +
                "饺子\n" +
                "冬至的三菌彩色饺子\n" +
                "油煎饺子\n" +
                "清香鲜甜---韭菜虾仁饺子\n" +
                "茴香猪肉饺子\n" +
                "饺子美味也要美丽-----冰花煎饺\n" +
                "韭菜鸡蛋虾皮饺子\n" +
                "苜蓿猪肉饺子\n" +
                "西葫芦猪肉饺子\n" +
                "冬天里春意盎然的饺子——翡翠藕香鲜肉饺\n" +
                "既要留住菜汁，还要保证饺子馅不出水的秘笈——【芹香水饺】\n" +
                "韭菜猪肉饺子\n" +
                "猪肉茴香饺子\n" +
                "好吃不过饺子----韭黄虾仁蒸饺\n" +
                "压面机花样面食之——猪肉玉米饺子\n" +
                "茴香饺子\n"));



        map.entrySet().forEach(o->{
           String key= o.getKey();
           List<String> valueList=o.getValue();
            valueList.forEach(e->{
                try {
                    String urlStr=downloadPath+key;

                    List<String> keywordList = nameList(e);
                    getPictures(keywordList,5,1,urlStr); //1代表下载一页，一页一般有30张图片
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

        });


    }


    public static void getPictures(List<String> keywordList,int size, int max,String downloadPath) throws Exception{ // key为关键词,max作为爬取的页数
        String gsm=Integer.toHexString(max)+"";
        String finalURL = "";
        String tempPath = "";
        for(String keyword : keywordList){
            tempPath = downloadPath;
            if(!tempPath.endsWith(File.separator)){
                tempPath = downloadPath+File.separator;
            }
            tempPath = tempPath+keyword+File.separator;
            File f = new File(tempPath);
            if(!f.exists()){
                f.mkdirs();
            }
            int picCount = 1;
            for(int page=0;page<=max;page++) {

                sop("正在下载第"+page+"页面");
                Document document = null;
                try {
                    String url ="http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word="+keyword+"&cg=star&pn="+page*30+"&rn=30&itg=0&z=0&fr=&width=&height=&lm=-1&ic=0&s=0&st=-1&gsm="+Integer.toHexString(page*30);
                    sop(url);
                    document = Jsoup.connect(url).data("query", "Java")//请求参数
                            .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                            .timeout(5000)
                            .get();
                    String xmlSource = document.toString();
                    xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);
                    sop(xmlSource);
                    String reg = "objURL\":\"http://.+?\\.jpg";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher m = pattern.matcher(xmlSource);
                    while (m.find()) {
                        finalURL = m.group().substring(9);
                        sop(keyword+picCount+++":"+finalURL);
                        download(finalURL,tempPath);
                        sop("             下载成功");
                        if(picCount>=10){
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sop("下载完毕");
        delMultyFile(downloadPath);
        sop("已经删除所有空图");
    }
    public static void delMultyFile(String path){
        File file = new File(path);
        if(!file.exists())
        { throw new RuntimeException("File \""+path+"\" NotFound when excute the method of delMultyFile()....");}
        File[] fileList = file.listFiles();
        File tempFile=null;
        for(File f : fileList){
            if(f.isDirectory()){
                { delMultyFile(f.getAbsolutePath());}
            }else{
                if(f.length()==0)
                { sop(f.delete()+"---"+f.getName());}
            }
        }
    }
    public static List<String> nameList(String nameList){
        List<String> arr = new ArrayList<String>();
        String[] list;
        if(nameList.contains(","))
        { list= nameList.split(",");}
        else if(nameList.contains("、"))
        { list= nameList.split("、");}
        else if(nameList.contains(" "))
        {list= nameList.split(" ");}
        else if(nameList.contains("\n"))
        {list= nameList.split("\n");}
        else{
            arr.add(nameList);
            return arr;
        }
        for(String s : list){
            arr.add(s);
        }
        return arr;
    }
    public static void sop(Object obj){
        System.out.println(obj);
    }
    //根据图片网络地址下载图片
    public static void download(String url,String path){
        //path = path.substring(0,path.length()-2);
        File file= null;
        File dirFile=null;
        FileOutputStream fos=null;
        HttpURLConnection httpCon = null;
        URLConnection con = null;
        URL urlObj=null;
        InputStream in =null;
        byte[] size = new byte[1024];
        int num=0;
        try {
            String downloadName= url.substring(url.lastIndexOf("/")+1);
            dirFile = new File(path);
            if(!dirFile.exists() && path.length()>0){
                if(dirFile.mkdir()){
                    sop("creat document file \""+path.substring(0,path.length()-1)+"\" success...\n");
                }
            }else{
                file = new File(path+downloadName);
                fos = new FileOutputStream(file);
                if(url.startsWith("http")){
                    urlObj = new URL(url);
                    con = urlObj.openConnection();
                    httpCon =(HttpURLConnection) con;
                    in = httpCon.getInputStream();
                    while((num=in.read(size)) != -1){
                        for(int i=0;i<num;i++)
                        {  fos.write(size[i]);}
                    }
                }
            }
        }catch (FileNotFoundException notFoundE) {
            sop("找不到该网络图片....");
        }catch(NullPointerException nullPointerE){
            sop("找不到该网络图片....");
        }catch(IOException ioE){
            sop("产生IO异常.....");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}