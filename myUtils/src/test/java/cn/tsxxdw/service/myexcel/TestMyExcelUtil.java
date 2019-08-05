package cn.tsxxdw.service.myexcel;

import cn.tsxxdw.bean.vo.excel.ExcelReadVo;
import cn.tsxxdw.service.mydate.MytimerUtil;
import org.junit.jupiter.api.Test;

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
        ExcelReadVo<ProductEntity> excelReadVo = new ExcelReadVo();
        excelReadVo.setInputStream(inputStream);
        excelReadVo.setExcelFieldRuleAndFillEntityFunction((o1,o2,o3)->{

            return null;
        });
        MyExcelUtil.read(excelReadVo);
        MytimerUtil.get().add("导入100%");
        MytimerUtil.get().getInfo();
    }
}
