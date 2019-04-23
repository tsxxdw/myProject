package cn.tsxxdw.service.excel;

import cn.tsxxdw.vo.ResultVo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExcelListener extends AnalysisEventListener {
    Function<List,ResultVo> checkFunction=null;

    private List<Object> data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        System.out.println(context.getCurrentSheet());
        data.add(object);
        if (data.size() >= 100) {
            doSomething();
            data = new ArrayList<Object>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }

    public void doSomething() {
        for (Object o : data) {
            System.out.println(o);
        }
    }


    public ResultVo check(List list) {
       /* list.
        checkFunction.apply()*/
        return null;
    }

    public ExcelListener(List<Object> data) {
        this.data = data;
    }
}
