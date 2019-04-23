package cn.tsxxdw.service.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.InputStream;

/**
 * @Author created by dsj
 * @Date 2019/4/22 15:55
 * @Description test excel
 */
public class TestMyExcelUtil {
    @Test
    public void test(){
        String fileName="templates/demo/less.xlsx";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream( fileName);
       // EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), new ExcelListener());
    }
}
