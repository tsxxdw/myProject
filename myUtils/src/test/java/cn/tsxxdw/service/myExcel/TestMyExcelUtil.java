package cn.tsxxdw.service.myExcel;

import cn.tsxxdw.bean.vo.excel.ExcelReadConditionAndRuleVo;
import cn.tsxxdw.service.myDate.MytimerUtil;
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
    public void test() {
        MytimerUtil.get().add("导入0%");
        String fileName = "templates/demo/many.xlsx";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), new MyExcelListener(new ExcelReadConditionAndRuleVo().setMinNumberOfBars(1)));
        MytimerUtil.get().add("导入100%");
        MytimerUtil.get().getInfo();
    }
}
