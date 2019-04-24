package cn.tsxxdw.service.myExcel;

import cn.tsxxdw.bean.vo.excel.ExcelEntityVo;
import cn.tsxxdw.bean.vo.excel.ExcelReadConditionAndRuleVo;
import cn.tsxxdw.bean.vo.excel.ExcelReadTipVo;
import cn.tsxxdw.myJava.Consumer2;
import cn.tsxxdw.myJava.Consumer3;
import cn.tsxxdw.myJava.Function3_1;
import cn.tsxxdw.myJava.CommonUtil;
import cn.tsxxdw.service.myFuture.MyfutureUtil;
import cn.tsxxdw.service.myMap.MyMapUtil;
import cn.tsxxdw.vo.ResultVo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExcelListener<T> extends AnalysisEventListener {
    private ExcelReadConditionAndRuleVo excelReadConditionAndRuleVo;
    private List<Future<ExcelEntityVo>> futureList = new ArrayList<>();
    private List<ExcelEntityVo<T>> dataList = new ArrayList<>();
    Map<Integer, String> errorInfoMap = new HashMap();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MyExcelListener(ExcelReadConditionAndRuleVo excelReadConditionAndRuleVo) {
        this.excelReadConditionAndRuleVo = excelReadConditionAndRuleVo;
    }

    /**
     * 获取数据到集合
     *
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        Future<ExcelEntityVo> future = executorService.submit(() -> {
            Integer currentRowNum = context.getCurrentRowNum();
            List<String> row = (List<String>) object;
            //这行代码可以封装到excel专有的Function中
            Function3_1<List<String>, Integer, Map, ExcelEntityVo<T>> fieldRuleFunction = excelReadConditionAndRuleVo.getExcelFieldRuleFunction();
            ExcelEntityVo<T> excelEntityVo = fieldRuleFunction.apply(row, currentRowNum - 1, errorInfoMap);
            return excelEntityVo;
        });
        futureList.add(future);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        CommonUtil.foreach(futureList, 0, (index, o) -> {
            try {
                ExcelEntityVo<List<String>> excelListEntityVo = MyfutureUtil.getFutureDate(o);
                Function3_1 servceFieldRuleFunction = excelReadConditionAndRuleVo.getExcelFieldRuleFunction();
                ExcelEntityVo<T> excelEntityVo = (ExcelEntityVo<T>) servceFieldRuleFunction.apply(excelListEntityVo, index, errorInfoMap);//字段校验
                dataList.add(excelEntityVo);
            } catch (Exception e) {
                MyMapUtil.addErrorInfo(errorInfoMap, -1001, "");//默认-1001 为future 等待错误
            }
        });

        Consumer2<List<ExcelEntityVo<T>>, Map> servceRepeatRuleFunction = excelReadConditionAndRuleVo.getServceRepeatRuleFunction();
        if (servceRepeatRuleFunction != null) {
            servceRepeatRuleFunction.accept(dataList, errorInfoMap);
        }
        Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction = excelReadConditionAndRuleVo.getServceFieldRuleFunction();
        servceFieldRuleFunction(servceFieldRuleFunction);
    }


    private void servceFieldRuleFunction(Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction) {
        int number = 5000;
        List<List<ExcelEntityVo<T>>> listList = Lists.partition(dataList, number);
        CommonUtil.foreach(listList, 0, number, (index, o) -> {
            executorService.submit(() -> {
                CommonUtil.foreach(o, index, 1, (index2, t) -> {
                    servceFieldRuleFunction.accept((ExcelEntityVo<T>) t, index2, errorInfoMap);
                });
            });
        });
    }


    /**
     * excel非空校验,以及判断条数是否有多条
     *
     * @param totalCount
     * @return
     */
    private ResultVo<Void> excelNumberOfBarsCheck(Integer totalCount) {
        if (totalCount == null || totalCount < 1) return new ResultVo<Void>().setFail(ExcelReadTipVo.dataIsNull);
        return ResultVo.createSimpleFailResult();
    }


}


