package com.mvm.boot.service.impl;

import com.mvm.boot.entity.TestVo;
import com.mvm.boot.mapper.TestMapper;
import com.mvm.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:TuoTuo
 * @createDate:2022/12/3 12:00
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public int count() {
        return testMapper.count();
    }

    @Override
    public List<TestVo> selectAll() {
        return testMapper.selectAll();
    }
}
