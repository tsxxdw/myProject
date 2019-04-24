package cn.tsxxdw.bean.vo.excel;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel 读取的Vo
 *
 * @Author created by dsj
 * @Date 2019/4/22 15:41
 * @Description excelReadVo
 */
@Setter
@Getter
public class ExcelEntityVo<T> {
    private int index;//位置
    private T entity;

    public ExcelEntityVo(int index, T entity) {
        this.index = index;
        this.entity = entity;
    }


}
