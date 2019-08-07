package cn.tsxxdw.service.mylog;

import cn.tsxxdw.mybean.vo.ResultVo;
import org.junit.Test;

public class TestMyLogUtil {
    @Test
    public void testLogInfo(){
        MyLogUtil.logInfo(this.getClass(), ResultVo.createServerErrorResult());
    }
}
