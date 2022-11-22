package com.xxx.mvn.mq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:TuoTuo
 * @createDate:2022/6/29 15:13
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Msg {
    int code;
    String message;
    public static Msg createSucMsg() {
        log.info("主业务执行代码...");
        return new Msg(200,"执行成功");
    }
}
