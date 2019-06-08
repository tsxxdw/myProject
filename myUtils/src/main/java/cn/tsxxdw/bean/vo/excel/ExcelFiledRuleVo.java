package cn.tsxxdw.bean.vo.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author created by dsj
 * @Date 2019/4/25 16:15
 * @Description excel 字段的校验规则Vo
 */
@Setter
@Getter
@AllArgsConstructor
public class ExcelFiledRuleVo {
    private String fieldName;//字段名
    Function<String, String> customConsumer; //自定义校验

}
