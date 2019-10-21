package cn.tsxxdw.service.myfile;

import cn.tsxxdw.service.mylog.MyLogUtil;
import cn.tsxxdw.vo.ResultVo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.nio.charset.Charset.forName;

public class MyFileUtil {
    /**
     * @param path
     * @param number   代表要获取几层文件夹
     * @param function
     * @return
     */
    public static ResultVo ergodicFloder(String path, int number, Function function) {

        return null;
    }

    private static ResultVo<List<String>> getFloder(){
        return null;
    }


    /**
     * 得到txt的数据
     * @return
     */
    public static  List<String> getTxtInfo(String url,String separator) {
        String fileName = "C:\\Users\\Lenovo\\Desktop\\hello.txt";
        //读取文件
        List<String> lineLists = null;
        try {
            System.out.println(url);
            lineLists = Files
                    .lines(Paths.get(url), forName("UTF-8"))
                    .flatMap(line -> Arrays.stream(line.split(separator)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            try {
                lineLists = Files
                        .lines(Paths.get(url), forName("GBK"))
                        .flatMap(line -> Arrays.stream(line.split(separator)))
                        .collect(Collectors.toList());
            } catch (Exception ex) {
                MyLogUtil.logError(MyFileUtil.class,e);
            }
        }
        MyLogUtil.logInfo(MyFileUtil.class,lineLists);
        return lineLists;

    }

    public static void main(String[] args) {
      //   String url="D:\\duan\\file\\mct\\中式菜系\\上海菜\\2019-9-30-14-11-30-1744012613799-上海菜_菜谱_美食天下-采集的数据-后羿采集器.txt";
        String url="D:\\duan\\file\\mct\\传统美食\\传统美食\\新建文本文档.txt";
         getTxtInfo(url,"\n\t");
    }
}
