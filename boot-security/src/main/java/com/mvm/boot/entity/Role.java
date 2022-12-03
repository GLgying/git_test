package com.mvm.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:58
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private long id;
    private String name;
}
