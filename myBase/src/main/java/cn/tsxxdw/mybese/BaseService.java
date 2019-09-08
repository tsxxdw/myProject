package cn.tsxxdw.mybese;


import cn.tsxxdw.dto.PageQueryDto;
import cn.tsxxdw.other.NullSafeWrapper;
import cn.tsxxdw.other.Where;
import cn.tsxxdw.service.mystr.MyStrUtils;
import cn.tsxxdw.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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

    //  create,remove,search,alter

    public int insert(E e) throws Exception {
        return m.insert(e);
    }

    public int insert(E e, Consumer<E> consumer) throws Exception {
        consumer.accept(e);
        return m.insert(e);
    }

    public int delete(Wrapper wrapper) throws Exception {
        int res = m.delete(wrapper);
        return res;
    }

    public E selectOne(Wrapper wrapper) {
        E e = (E) m.selectOne(wrapper);
        return e;
    }


    private NullSafeWrapper select(Object object, NullSafeWrapper where, PageQueryDto pageQueryDto) throws Exception {
        List<Field> fieldList= Lists.newArrayList();
        boolean parentClassIsObject=false;//父类是object?
        Class parmClass=object.getClass();//获取当前类
        Class currentClass=object.getClass();//获取当前类
        do{
            Field[] field = currentClass.getDeclaredFields(); //获取实体类的所有属性，返回Field数组
            List<Field> tempList=  Arrays.asList(field);
            fieldList.addAll(tempList);
            currentClass=  currentClass.getSuperclass();
           String currentClassName= currentClass.getName();
           if("java.lang.Object".equals(currentClassName))parentClassIsObject=true;
        }while (!parentClassIsObject);


        for (int j = 0; j < fieldList.size(); j++) { //遍历所有属性
            String camelFiledName = fieldList.get(j).getName(); //获取属性的名字
            String name = camelFiledName.substring(0, 1).toUpperCase() + camelFiledName.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = fieldList.get(j).getGenericType().toString(); //获取属性的类型
            //如果type是类类型，则前面包含"class "，后面跟类名
            Method m = object.getClass().getMethod("get" + name);
            String value = (String) m.invoke(object); //调用getter方法获取属性值
            String underlineFiledName = MyStrUtils.camelToUnderline(camelFiledName, 1);
            if (StringUtils.isBlank(value)) continue;

            else if("limit".equals(camelFiledName)){
                 pageQueryDto.setLimit((Integer) getValue(parmClass,"Limit"));
             }
            else   if("page".equals(camelFiledName)){

                pageQueryDto.setPage((Integer) getValue(parmClass, "Page"));
            }
            else  if("orderByAsc".equals(camelFiledName)){

                where.orderByAsc((String)getValue(parmClass,  "OrderByAsc"));
            }
            else  if("orderByDesc".equals(camelFiledName)){
                where.orderByDesc((String)getValue(parmClass, "OrderByDesc"));
            }else {
                where.eq(underlineFiledName, value);
            }
        }


        return where;
    }

    private Object getValue(Class c, String methodName) throws Exception{
        Method method = c.getMethod("get" + methodName);
        if (null == method) {
            return null;
        } else {
            return method.invoke(c);
        }
    }

    public E selectOne(Object object, NullSafeWrapper where) throws Exception {
        return selectOne(select(object, where, null));

    }

    public List<E> selectList(Wrapper wrapper) {
        List<E> list = m.selectList(wrapper);
        return list;
    }

    public ResultVo<List<E>> selectList(Object object, NullSafeWrapper where) throws Exception {
        List<E> list = null;
        PageQueryDto pageQueryDto = new PageQueryDto();
        NullSafeWrapper where1 = select(object, where, pageQueryDto);
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
