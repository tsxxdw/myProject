package cn.tsxxdw.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 条件
 *
 * @Author created by limstore.li
 * @Date 2019年4月2日 下午4:16:35
 */
public class Where {

    public static <T> QueryWrapper<T> use(Class<T> clazz) {
        return new QueryWrapper<>();
    }

    public static <T> NullSafeWrapper<T> useNullSafe(Class<T> clazz) {
        return new NullSafeWrapper<T>();
    }

}