package cn.tsxxdw.service.mylist;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author created by dsj
 * @Date 2019/4/22 11:38
 * @Description List相关的工具类
 */
public class MyListUtil {
    /**
     * 参数不能为null，否则报空指针异常。
     *
     * @param list
     * @param str
     * @return
     */
    public static boolean checkIsContain(List<String> list, String str) {
        Objects.requireNonNull(list);
        Objects.requireNonNull(str);
        if (StringUtils.isNotBlank(str) && !list.isEmpty()) {
            list = Lists.transform(list, String::toUpperCase);
            if (list.contains(str.trim().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取重复的数据
     * @param list
     * @param <T>
     * @return
     */
    private static <T> List<T> getDuplicateElements(List<T> list) {
        return list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
    }
}
