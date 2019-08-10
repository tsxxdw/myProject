package cn.tsxxdw.mybese;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <E> 插入数据库对应的entity
 * @param <M> 所需的Mapper类
 */
@Setter
public class BaseService<E, M extends BaseMapper<E>> {
    @Autowired
    private M m;

  //  create,remove,search,alter

    public int insert(E e) throws Exception {
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

    public List<E> selectList(Wrapper wrapper) {
        List<E> list = m.selectList(wrapper);
        return list;
    }

    public int update(E e,Wrapper wrapper){
       return m.update(e,wrapper);
    }


}
