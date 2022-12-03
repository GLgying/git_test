package com.mvm.boot.service;

import com.mvm.boot.entity.TestVo;

import java.util.List;

/**
 * @author Ging
 */
public interface TestService {

    /**
     * 查询条数
     * @return
     */
    int count();

    /**
     * 查询全部
     * @return
     */
    List<TestVo> selectAll();

}
