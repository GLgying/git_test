package com.xxx.mvn.data.structure.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 数据结构-数组
 * @author:TuoTuo
 * @createDate:2022/10/27 15:19
 * @description:
 */
@Slf4j
public class ArrayTest {

    /**
     * 求数组第二最小的元素
     */
    @Test
    public void Test1(){
        int[] array = new int[]{10,3,5,7};
        print(array);
        int minNum0 = Integer.MAX_VALUE;
        int minNum1 = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(minNum0>array[i]){
                minNum1 = minNum0;
                minNum0 = array[i];
            }else if(minNum1>array[i]){
                minNum1 = array[i];
            }
            System.out.println(minNum0+"--"+minNum1);
        }
        System.out.println(minNum0);
        System.out.println(minNum1);

    }


    public void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ",array[i]);
        }
        System.out.println("");
    }

    /**
     * 找到数组中第一个不重复出现的整数
     */
    @Test
    public void Test2(){
        int[] array = new int[]{10,3,5,7,7,10,3};
        print(array);

        HashMap<Integer,Integer> map = new HashMap(array.length);
        int index = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(map.containsKey(array[i])){
                map.remove(array[i]);
            }else {
                map.put(array[i],i);
            }
        }
        Collection<Integer> values = map.values();
        for(Integer c: values){
            if(c<index){
                index = c;
            }
        }
        System.out.println(array[index]);

    }

    @Test
    public void Test3(){
        int[] array1 = new int[]{3,5,8,1,4};
        int[] array2 = new int[]{31,15,8,11,42};
        Arrays.sort(array1);
        Arrays.sort(array2);
        int[] array3 = new int[array1.length+array2.length];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = array1[i];

        }



    }

}
