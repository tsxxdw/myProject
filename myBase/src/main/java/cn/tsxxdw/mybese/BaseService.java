package cn.tsxxdw.mybese;


import cn.tsxxdw.myJava.MyFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

/**
 * @param <E> 插入数据库对应的entity
 * @param <M> 所需的Mapper类
 * @param <R> 操作成功返回的类型
 */
@Setter
public class BaseService<E, M extends BaseMapper<E>, R> {
    @Autowired
    private M m;

    MyFunction<E, Boolean> addFunction = o -> {
        try {
            m.insert(o);
            return true;
        } catch (Exception e) {
            throw e;
        }
    };

    public R add(E e, Function<Boolean, R> thenFunction) throws Exception {

        return addFunction.andThen_boolean(thenFunction).apply(e);

    }


}
