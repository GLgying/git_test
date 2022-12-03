package com.mvm.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author:TuoTuo
 * @createDate:2022/12/3 12:58
 * @description:
 */
@Entity
/*name 对应数据库表名  schema 实际 使用 别名*/
@Table(name="user",schema = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestVo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)      /*自增主键*/
    @Column(name = "id")
    private Integer id;
    private String name;
    private String nickname;
    private String password;
    private int sex;
    private int age;
    private String address;
    private String email;
    private String hobby;
    private String enabled;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


}
