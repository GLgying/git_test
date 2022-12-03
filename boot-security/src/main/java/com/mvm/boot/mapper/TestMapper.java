package com.mvm.boot.mapper;


import com.mvm.boot.entity.TestVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ging
 */
public interface TestMapper {


    /**
     * 查询条数
     * @return
     */
    @Select("select count(*) from test.test01")
    int count();

    /**
     * 查询全部
     * @return
     */
    @Select("select * from test01")
    List<TestVo> selectAll();
}
