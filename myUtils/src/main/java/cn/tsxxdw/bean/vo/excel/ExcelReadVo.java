package cn.tsxxdw.bean.vo.excel;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * excel 读取的Vo
 * @Author created by dsj
 * @Date 2019/4/22 15:41
 * @Description excelReadVo
 */
@Setter
@Getter
public class ExcelReadVo {
   private InputStream inputStream;//excel 所在输入流
   private AnalysisEventListener analysisEventListener;
}
