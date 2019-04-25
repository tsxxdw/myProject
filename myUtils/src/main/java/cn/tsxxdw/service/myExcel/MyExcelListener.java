package cn.tsxxdw.service.myExcel;

import cn.tsxxdw.bean.vo.excel.ExcelEntityVo;
import cn.tsxxdw.bean.vo.excel.ExcelReadTipVo;
import cn.tsxxdw.bean.vo.excel.ExcelReadVo;
import cn.tsxxdw.myJava.CommonUtil;
import cn.tsxxdw.myJava.Consumer2;
import cn.tsxxdw.myJava.Consumer3;
import cn.tsxxdw.myJava.Function3_1;
import cn.tsxxdw.service.myFuture.MyfutureUtil;
import cn.tsxxdw.service.myMap.MyMapUtil;
import cn.tsxxdw.vo.ResultVo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExcelListener<T> extends AnalysisEventListener implements IExcel {
    private ExcelReadVo<T> excelReadVo;
    private List<Future<ExcelEntityVo<T>>> futureList = new ArrayList<>();
    private List<ExcelEntityVo<T>> dataList = null;
    Map<Integer, String> errorInfoMap = new HashMap(16);
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MyExcelListener(ExcelReadVo excelReadVo) {
        this.excelReadVo = excelReadVo;
    }

    /**
     * 获取数据到集合
     *
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        checkExcelFieldRuleAndFillEntityFunction(object, context); //校验Excel 填充数据的基本规则，且把数据转为 entity
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        checkServceFieldRule();
        checkServceRepeat();
        try {
            releaseResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    /**
     * 校验重复数据
     */
    @Override
    public void checkServceRepeat() {
        Consumer2<List<ExcelEntityVo<T>>, Map> servceRepeatRuleFunction = excelReadVo.getServceRepeatRuleFunction();
        if (servceRepeatRuleFunction != null) {
            servceRepeatRuleFunction.accept(dataList, errorInfoMap);
        }
    }

    @Override
    public void checkServceFieldRule() {
        int number = 5000;
        Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction = excelReadVo.getServceFieldRuleFunction();
        if (servceFieldRuleFunction == null) return;
        List<List<Future<ExcelEntityVo<T>>>> listList = Lists.partition(futureList, number);
        if (dataList == null) dataList = new ArrayList<>(futureList.size());//TODO 这里动态生成的数组大小是否合适为测试
        CommonUtil.foreach(listList, 0, number, (index, o) -> {
            executorService.submit(() -> {
                CommonUtil.foreach(o, index, 1, (index1, t) -> {
                    try {
                        ExcelEntityVo<T> excelEntityVo = MyfutureUtil.getFutureDate(t);
                        servceFieldRuleFunction.accept(excelEntityVo, index1, errorInfoMap);//业务字段校验
                        dataList.add(excelEntityVo);
                    } catch (Exception e) {
                        MyMapUtil.addErrorInfo(errorInfoMap, -1001, "");//默认-1001 为future 等待错误
                    }
                });
            });
        });
    }

    /**
     * check ExcelFieldRule And FillEntity
     *
     * @param object
     * @param context
     */
    @Override
    public void checkExcelFieldRuleAndFillEntityFunction(Object object, AnalysisContext context) {
        Function3_1<List<String>, Integer, Map, ExcelEntityVo<T>> excelFieldRuleAndFillEntityFunction = excelReadVo.getExcelFieldRuleAndFillEntityFunction();
        Objects.requireNonNull(excelFieldRuleAndFillEntityFunction, "servceFieldRuleFunction function not is null");
        Integer currentRowNum = context.getCurrentRowNum();
        Future<ExcelEntityVo<T>> future = executorService.submit(() -> {
            List<String> row = (List<String>) object;
            //这行代码可以封装到excel专有的Function中
            ExcelEntityVo<T> excelEntityVo = excelFieldRuleAndFillEntityFunction.apply(row, currentRowNum - 1, errorInfoMap);//excel 字段校验，以及塞值
            return excelEntityVo;
        });
        futureList.add(future);
    }


    /**
     * 释放资源
     */
    public void releaseResources() throws IOException {
        futureList = null;//释放资源
        dataList = null;
        excelReadVo.getInputStream().close();
    }
}


