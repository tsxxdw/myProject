package cn.tsxxdw.service.myMap;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author created by dsj
 * @Date 2019/4/24 17:29
 * @Description ...
 */
public class MyMapUtil {
    /**
     * 往errorInfoMap里面添加错误信息
     *
     * @param errorInfoMap
     * @param index
     * @param errorInfoStr
     */
    public static void addErrorInfo(Map<Integer, String> errorInfoMap, Integer index, String errorInfoStr) {
        if (errorInfoMap instanceof ConcurrentHashMap) {
            throw new NullPointerException("Class must ConcurrentHashMap");
        }
        if (StringUtils.isBlank(errorInfoStr) || errorInfoMap == null || index == null) {
            throw new NullPointerException("errorInfoStr or errorInfoMap must not null");
        }
        StringBuilder sb = new StringBuilder();
        String sourceStr = errorInfoMap.get(index);
        if (StringUtils.isBlank(sourceStr)) {
            sb.append(errorInfoStr);
        } else {
            sb.append(sourceStr);
            sb.append(",");
            sb.append(errorInfoStr);
        }
        errorInfoMap.put(index, sb.toString());
    }
}
