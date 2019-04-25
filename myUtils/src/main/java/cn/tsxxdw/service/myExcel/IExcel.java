package cn.tsxxdw.service.myExcel;

import cn.tsxxdw.bean.vo.excel.ExcelEntityVo;
import cn.tsxxdw.myJava.Consumer2;
import cn.tsxxdw.myJava.Consumer3;
import com.alibaba.excel.context.AnalysisContext;

import java.util.List;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/25 10:42
 * @Description ...
 */
public interface IExcel<T> {
    //校验数据库中重复的数据
    void checkServceRepeat();


    //校验excel 字段规则
    void checkExcelFieldRuleAndFillEntityFunction(Object object, AnalysisContext context);

    void checkServceFieldRule();

 /*   *//**
     * 1 判断是否填写数据
     * 2 判断是否超出数据最大row
     * @param errorInfoMap
     * @param data
     *//*
    void checkNumber(Map<Integer, String> errorInfoMap, List<T> data);
*/


  /*  Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction;//跟业务相关的字段规则校验
    Consumer2<List<ExcelEntityVo<T>>, Map> servceRepeatRuleFunction;//跟业务相关的重复规则校验*/
}
