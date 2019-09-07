package demo.demo20190906;

import cn.tsxxdw.vo.ResultVo;

public class Main {
    public static void main(String[] args) throws Exception {
        ResultVo resultVo=   new ResultVo<>().setFail("fail");

        PageClass pageClass=   new PageClass();
        pageClass.testReflect(resultVo);
    }



}
