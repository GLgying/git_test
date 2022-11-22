package com.xxx.mvn.mq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:TuoTuo
 * @createDate:2022/6/29 15:09
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bug {
    private String url;
    private String desc;
    private Integer code;
}
