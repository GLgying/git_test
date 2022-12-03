package com.mvm.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:TuoTuo
 * @createDate:2022/12/3 12:58
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestVo {
    private int id;
    private String name;
    private int age;
    private String hobby;

}
