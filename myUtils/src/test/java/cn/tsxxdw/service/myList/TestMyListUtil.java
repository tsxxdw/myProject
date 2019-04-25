package cn.tsxxdw.service.myList;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author created by dsj
 * @Date 2019/4/22 11:38
 * @Description List相关的工具类
 */
public class TestMyListUtil {

    @Test
    public void testCheckIsContain(){
        System.out.println(MyListUtil.checkIsContain(Lists.newArrayList(),"a"));
        //System.out.println(MyListUtil.checkIsContain(null,"a"));
        //System.out.println(MyListUtil.checkIsContain(Lists.newArrayList(),null));
    }






}
