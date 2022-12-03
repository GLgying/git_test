package com.xxx.mvn.web.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author:TuoTuo
 * @createDate:2022/11/29 14:40
 * @description:
 */
@Slf4j
public class CopyTest {

    @Test
    public void testCopy(){
        log.info("--");
        T1 t1 = new T1("xsd",2);
        System.out.println(t1);
        T2 t2 = new T2();
        System.out.println(t2);
        /*拷贝  该方法为浅拷贝 只是使用了 getset方法  如果没有getset方法就没有意义*/
        BeanUtils.copyProperties(t1,t2);
        System.out.println(t1);
        System.out.println(t2);
        //深拷贝与浅拷贝
        //深拷贝和浅拷贝的区别：浅拷贝主要是对指针的拷贝，拷贝后两个指针指向同一个内存空间，深拷贝需要不但对指针进行拷贝，并对指针指向的内容进行拷贝，经过深拷贝后的指针是指向两个不同地址的指针
        //浅拷贝只是拷贝了 引入执行  但是执行的内容并没有边
        //深拷贝 不但拷贝了 指向 而且连同指向的地址的内容也一并给拷贝了
    }
    class T1{
        String name;
        int age;

        public T1() {
        }

        public T1(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "T1{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class T2{
        String name;
        int sex;

        public T2() {
        }

        public T2(String name, int sex) {
            this.name = name;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "T2{" +
                    "name='" + name + '\'' +
                    ", sex=" + sex +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
