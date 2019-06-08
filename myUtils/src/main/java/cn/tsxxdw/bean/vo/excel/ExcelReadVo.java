package cn.tsxxdw.bean.vo.excel;

import cn.tsxxdw.myJava.Consumer2;
import cn.tsxxdw.myJava.Consumer3;
import cn.tsxxdw.myJava.Function3_1;
import cn.tsxxdw.service.myExcel.MyExcelListener;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * excel 读取的Vo
 *
 * @Author created by dsj
 * @Date 2019/4/22 15:41
 * @Description excelReadVo
 */
@Setter
@Getter
public class ExcelReadVo<T> {
    // Sheet 值
    private int sheetNo = 1;
    private int headLineMun = 1;
    private InputStream inputStream;//excel 所在输入流
    private Integer minNumberOfBars = 1;//最小的行数
    private Integer maxNumberOfBars = null;//最大的行数
    private Map<Integer, ExcelFiledRuleVo> excelFiledRuleVoMap;
    private Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction;//跟业务相关的字段规则校验
    private Consumer2<List<ExcelEntityVo<T>>, Map> servceRepeatRuleFunction;//跟业务相关的重复规则校验
    private Optional<T> tOptional;
    //跟excel 相关输入规则校验
    private Function3_1<List<String>, Integer, Map, ExcelEntityVo<T>> excelFieldRuleAndFillEntityFunction = (list, index, map) -> {

        ExcelFiledRuleVo excelFiledRuleVo = excelFiledRuleVoMap.get(index);
        Optional<ExcelFiledRuleVo> excelFiledRuleVoOptional = Optional.ofNullable(excelFiledRuleVo);
        excelFiledRuleVoOptional.ifPresent(o -> {
           /* String fileName = o.getFieldName();
            Field field = a.getClass().getDeclaredField("x");
            field.setAccessible(true);
            field.set(a, 1);*/
        });
        return null;
    };


}
