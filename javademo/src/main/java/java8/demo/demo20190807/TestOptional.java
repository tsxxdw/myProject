package java8.demo.demo20190807;

import cn.tsxxdw.mybean.vo.ResultVo;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    @Test
    public void test001(){
        System.out.println("1");
        Optional<ResultVo> optional1=Optional.ofNullable(ResultVo.createSystemErrorResult());
        optional1.ifPresent(o->{
            System.out.println(o.toString());
        });
        System.out.println("2");

        Optional<ResultVo> optional2=Optional.ofNullable(null);
        optional2.ifPresent(o->{
            System.out.println(o.toString());
        });
        System.out.println("3");



        Optional<ResultVo> optional3=Optional.ofNullable(null);

        optional3.ifPresent(o->{
            System.out.println(o.toString());
        });


    }
}
