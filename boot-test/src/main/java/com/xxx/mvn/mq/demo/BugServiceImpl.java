package com.xxx.mvn.mq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author:TuoTuo
 * @createDate:2022/6/29 15:12
 * @description:
 */
@Slf4j
@Service
public class BugServiceImpl implements BugService{
    @Override
    public void save(Bug bug) {
        log.info("mq 执行代码...");
    }
    
}
