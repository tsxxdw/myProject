package cn.tsxxdw.bean.vo.excel;

import cn.tsxxdw.myJava.Consumer2;
import cn.tsxxdw.myJava.Consumer3;
import cn.tsxxdw.myJava.Function3_1;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 1 下面属性如果为null，则默认无限制
 *
 * @Author created by dsj
 * @Date 2019/4/24 11:28
 * @Description excel条件
 */
@Setter
@Getter
public class ExcelReadConditionAndRuleVo<T> {
    public Integer minNumberOfBars = 1;//最小的行数
    public Integer maxNumberOfBars = null;//最大的行数
    Consumer3<ExcelEntityVo<T>, Integer, Map> servceFieldRuleFunction;//跟业务相关的字段规则校验
    Consumer2<List<ExcelEntityVo<T>>, Map> servceRepeatRuleFunction;//跟业务相关的重复规则校验
    //跟excel 相关输入规则校验
    Function3_1<List<String>, Integer, Map, ExcelEntityVo<T>> excelFieldRuleFunction = (t, index, map) -> {
        return null;
    };


    public Integer getMinNumberOfBars() {
        return minNumberOfBars;
    }

    public ExcelReadConditionAndRuleVo setMinNumberOfBars(Integer minNumberOfBars) {
        this.minNumberOfBars = minNumberOfBars;
        return this;
    }

    public Integer getMaxNumberOfBars() {
        return maxNumberOfBars;
    }

    public ExcelReadConditionAndRuleVo setMaxNumberOfBars(Integer maxNumberOfBars) {
        this.maxNumberOfBars = maxNumberOfBars;
        return this;
    }
}
