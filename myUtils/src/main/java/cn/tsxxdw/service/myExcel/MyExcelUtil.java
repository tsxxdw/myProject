package cn.tsxxdw.service.myExcel;

import cn.tsxxdw.bean.vo.excel.ExcelReadVo;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import java.io.IOException;

/**
 * @Author created by dsj
 * @Date 2019/4/22 15:18
 * @Description 我的excel工具类
 */
public class MyExcelUtil {
    public static void read(ExcelReadVo excelReadVo) {
        EasyExcelFactory.readBySax(excelReadVo.getInputStream(), new Sheet(excelReadVo.getSheetNo(), excelReadVo.getHeadLineMun()), new MyExcelListener(excelReadVo));
    }
}
