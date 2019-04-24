package cn.tsxxdw.service.myExcel;

import java.util.List;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/24 10:34
 * @Description excel校验接口
 */
public interface IExcelCheckService<T> {
    //校验数据库中重复的数据
    void checkExcelRepeat(Map<Integer, String> errorInfoMap, List<T> data);

    //校验
    void checkDataRule(Map<Integer, String> errorInfoMap, List<T> data);

    void checkFieldRule(Map<Integer, String> errorInfoMap, List<T> data);

    /**
     * 1 判断是否填写数据
     * 2 判断是否超出数据最大row
     * @param errorInfoMap
     * @param data
     */
    void checkNumber(Map<Integer, String> errorInfoMap, List<T> data);

    default void check(Map<Integer, String> errorInfoMap, List<T> data) {


    }

}
