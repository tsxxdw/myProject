package cn.tsxxdw.other;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class NullSafeWrapper<T> extends QueryWrapper<T> {

    private static final long serialVersionUID = -3136609040116768649L;

    @Override
    public QueryWrapper<T> eq(String column, Object params) {
        if (!isEmpty(params)) {
            return super.eq(column, params);
        }
        return this;
    }
    @Override
    public QueryWrapper<T> in(String column, Collection coll) {
        if (!isEmpty(coll)) {
            return super.in(column, coll);
        }
        return this;
    }

    @Override
    public QueryWrapper<T> ge(String column, Object params) {
        if (!isEmpty(params)) {
            return super.ge(column, params);
        }
        return this;
    }

    @Override
    public QueryWrapper<T> gt(String column, Object params) {
        if (!isEmpty(params)) {
            return super.gt(column, params);
        }
        return this;
    }

    @Override
    public QueryWrapper<T> lt(String column, Object params) {
        if (!isEmpty(params)) {
            return super.lt(column, params);
        }
        return this;
    }

    @Override
    public QueryWrapper<T> le(String column, Object params) {
        if (!isEmpty(params)) {
            return super.le(column, params);
        }
        return this;
    }

    @Override
    public QueryWrapper<T> like(String column, Object params) {
        if (!isEmpty(params)) {
            return super.like(column, params);
        }
        return this;
    }

    private boolean isEmpty(Object params) {
        if (params == null) {
            return true;
        }
        if (params instanceof String) {
            String p = (String) params;
            return StringUtils.isBlank(p);
        }
        return false;
    }
}