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
    @Select("select count(*) from security.user")
    int count();

    /**
     * 查询全部
     * @return
     */
    @Select("select * from security.user")
    List<TestVo> selectAll();
}
