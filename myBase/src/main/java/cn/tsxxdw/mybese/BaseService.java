package cn.tsxxdw.mybese;


import cn.tsxxdw.dto.BaseQueryDto;
import cn.tsxxdw.dto.PageQueryDto;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.service.reflex.ReflexUtil;
import cn.tsxxdw.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @param <E> 插入数据库对应的entity
 * @param <M> 所需的Mapper类
 */
@Setter
public class BaseService<E, M extends BaseMapper<E>> {
    @Autowired
    private M m;

    public M getM() {
        return m;
    }

    public void add(E e) throws Exception {
        m.insert(e);
    }

    public void add(E e, Consumer<E> consumer) throws Exception {
        consumer.accept(e);
        m.insert(e);
    }

    public int delete(Object object, QueryWrapper where) throws Exception {
        getWhere(object, where, null);
        int res = m.delete(where);
        return res;
    }


    public E query(Wrapper wrapper) {
        E e = (E) m.selectOne(wrapper);
        return e;
    }

    /**
     * 根据传入的javabean，将Wrapper设置查询条件
     *
     * @param object
     * @param where
     * @throws Exception
     */
    private void changeWhere(Object object, QueryWrapper where) throws Exception {
        getBaseQueryValueSaveToWrapper(object,where);
        Class currentClass = object.getClass();//获取当前类
        Field[] fields = currentClass.getDeclaredFields(); //获取实体类的所有属性，返回Field数组
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String typeStr = ReflexUtil.getFieldType(field); //获取属性的类型
            //1 获取属性的名字
            String fieldName = ReflexUtil.getFieldName(field);
            //2 获取转下划线的字段名称
            String underlineFiledName = MyStrUtils.camelToUnderline(fieldName, 1);
            // 获取字段名的值
            Object value = ReflexUtil.getValue(object, field);
            if (null == value) continue;
            if ("class java.lang.String".equals(typeStr)) {
                String valueStr = (String) value;
                if (StringUtils.isBlank(valueStr)) continue;
                where.eq(underlineFiledName, value);
            }
        }
    }

    public ResultVo<List<E>> queryList(Object object, QueryWrapper where) throws Exception {
        Objects.requireNonNull(object);
        Objects.requireNonNull(where);
        PageQueryDto pageQueryDto = (PageQueryDto) object;
        Objects.requireNonNull(pageQueryDto.getPage());
        Objects.requireNonNull(pageQueryDto.getLimit());
        changeWhere(object, where);
        List<E> list = getM().selectList(where);
        return new ResultVo<List<E>>().setSuccess(list);
    }

    public ResultVo<List<E>> queryListByPage(Object object, QueryWrapper where) throws Exception {
        Objects.requireNonNull(object);
        Objects.requireNonNull(where);
        PageQueryDto pageQueryDto = (PageQueryDto) object;
        Objects.requireNonNull(pageQueryDto.getPage());
        Objects.requireNonNull(pageQueryDto.getLimit());
        changeWhere(object, where);
        Page<E> myPage = new Page<>(pageQueryDto.getPage(), pageQueryDto.getLimit());
        IPage<E> iPage = getM().selectPage(myPage, where);
        List<E> list = iPage.getRecords();
        return new ResultVo<List<E>>().setPageSuccess(list, iPage.getTotal());
    }

    /**
     * BaseQuery
     *
     * @param object
     * @param wrapper
     */
    private void getBaseQueryValueSaveToWrapper(Object object, QueryWrapper wrapper) {
        BaseQueryDto baseQueryDto = (BaseQueryDto) object;
        String orderByAsc = baseQueryDto.getOrderByAsc();
        String orderByDesc = baseQueryDto.getOrderByDesc();
        wrapper.orderByAsc(MyStrUtils.camelToUnderline(orderByAsc, 1));
        wrapper.orderByDesc(MyStrUtils.camelToUnderline(orderByDesc, 1));
    }



    /*旧版本的代码再下面----------------------------------------------------------*/


    public int insert(E e) throws Exception {
        return m.insert(e);
    }

    public int insert(E e, Consumer<E> consumer) throws Exception {
        consumer.accept(e);
        return m.insert(e);
    }


    public E selectOne(Wrapper wrapper) {
        E e = (E) m.selectOne(wrapper);
        return e;
    }


    private QueryWrapper getWhere(Object object, QueryWrapper where, PageQueryDto pageQueryDto) throws Exception {
        List<Field> fieldList = Lists.newArrayList();
        boolean parentClassIsObject = false;//父类是object?
        Class parmClass = object.getClass();//获取当前类
        Class currentClass = object.getClass();//获取当前类
        do {
            Field[] field = currentClass.getDeclaredFields(); //获取实体类的所有属性，返回Field数组
            List<Field> tempList = Arrays.asList(field);
            fieldList.addAll(tempList);
            currentClass = currentClass.getSuperclass();
            String currentClassName = currentClass.getName();
            if ("java.lang.Object".equals(currentClassName)) parentClassIsObject = true;
        } while (!parentClassIsObject);


        for (int j = 0; j < fieldList.size(); j++) { //遍历所有属性
            String camelFiledName = fieldList.get(j).getName(); //获取属性的名字
            String name = camelFiledName.substring(0, 1).toUpperCase() + camelFiledName.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = fieldList.get(j).getGenericType().toString(); //获取属性的类型
            //如果type是类类型，则前面包含"class "，后面跟类名
            Method m = object.getClass().getMethod("get" + name);
            String underlineFiledName = MyStrUtils.camelToUnderline(camelFiledName, 1);
            Object value = m.invoke(object); //调用getter方法获取属性值

            if ("class java.lang.String".equals(type)) {
                String valueStr = (String) value;
                if (StringUtils.isBlank(valueStr)) continue;


                else if ("orderByAsc".equals(camelFiledName)) {

                    where.orderByAsc(MyStrUtils.camelToUnderline(valueStr, 1));
                } else if ("orderByDesc".equals(camelFiledName)) {
                    where.orderByDesc(MyStrUtils.camelToUnderline(valueStr, 1));
                } else {
                    where.eq(underlineFiledName, value);
                }
            } else if ("class java.lang.Integer".equals(type)) {

                Integer valueInteger = (Integer) value;
                if (valueInteger == null) continue;
                if ("limit".equals(camelFiledName)) {
                    pageQueryDto.setLimit(valueInteger);
                } else if ("page".equals(camelFiledName)) {
                    pageQueryDto.setPage(valueInteger);
                } else {
                    where.eq(underlineFiledName, valueInteger);
                }
            }

        }


        return where;
    }

    /**
     * 该方法有问题，没找到原因
     *
     * @param c
     * @param methodName
     * @return
     * @throws Exception
     */
    private Object getValue(Class c, String methodName) throws Exception {
        Method method = c.getMethod("get" + methodName);
        if (null == method) {
            return null;
        } else {
            return method.invoke(c);
        }
    }

    public E selectOne(Object object, QueryWrapper where) throws Exception {
        return selectOne(getWhere(object, where, null));

    }

    public List<E> selectList(Wrapper wrapper) {
        List<E> list = m.selectList(wrapper);
        return list;
    }

    public ResultVo<List<E>> selectList(Object object, QueryWrapper where) throws Exception {
        List<E> list = null;
        PageQueryDto pageQueryDto = new PageQueryDto();
        QueryWrapper where1 = getWhere(object, where, pageQueryDto);
        if (pageQueryDto.getPage() == null || pageQueryDto.getLimit() == null) {
            list = selectList(where);
            return new ResultVo().setSuccess(list);
        } else {
            Page<E> myPage = new Page<>(pageQueryDto.getPage(), pageQueryDto.getLimit());
            IPage<E> iPage = getM().selectPage(myPage, where);
            list = iPage.getRecords();
            return new ResultVo<List<E>>().setPageSuccess(list, iPage.getTotal());
        }

    }

    public int update(E e, Wrapper wrapper) {
        return m.update(e, wrapper);
    }


}
