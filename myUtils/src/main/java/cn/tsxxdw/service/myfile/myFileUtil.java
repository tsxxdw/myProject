package cn.tsxxdw.service.myfile;

import cn.tsxxdw.vo.ResultVo;

import java.io.File;
import java.util.List;
import java.util.function.Function;

public class myFileUtil {
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


    public static void main(String[] args) {
        String path="D:\\duan\\file\\美菜图";
        File file=new File(path);
        if(file.isDirectory()){
            File[]files=file.listFiles();
            for (int i=0;i<files.length;i++){
                System.out.println(files[i].getAbsolutePath());
                System.out.println(files[i].getName());
            }
        }
    }

}
