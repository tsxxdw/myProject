package cn.tsxxdw.service.myexcel;

import cn.tsxxdw.bean.vo.excel.ExcelReadVo;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import java.util.List;

/**
 * @Author created by dsj
 * @Date 2019/4/22 15:18
 * @Description 我的excel工具类
 */
public class MyExcelUtil {
    public static void read(ExcelReadVo excelReadVo) {
        EasyExcelFactory.readBySax(excelReadVo.getInputStream(), new Sheet(excelReadVo.getSheetNo(), excelReadVo.getHeadLineMun()), new MyExcelListener(excelReadVo));
    }


    public static void write(String fileName,Class myclass, List list){
        // 写法1
        //String fileName = "/data/4_uploadfile/pachong/20191014_sz" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, myclass).sheet("模板").doWrite(list);
    }
}
