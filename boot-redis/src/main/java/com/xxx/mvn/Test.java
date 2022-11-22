package com.xxx.mvn;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:TuoTuo
 * @createDate:2022/10/17 19:25
 * @description:
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int max = Integer.MAX_VALUE;
        int x = Integer.MAX_VALUE+1;
        System.out.println(max);
        System.out.println(x);
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        Object o1 = hashMap.putIfAbsent("xs","sd");
        Object o2 = hashMap.putIfAbsent("xs", "xs0");
        System.out.println(o1);
        System.out.println(o2);

//        ThreadPoolExecutor executor = new ThreadPoolExecutor();



    }

    public static  int candy (int[] arr) {
        // write code here
        int[] nums = new int[arr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1;
        }
        //从左往右遍历
        for (int i = 1; i < arr.length ; i++) {
            if (arr[i] > arr[i - 1]) {
                nums[i] = nums[i-1] + 1;
            }
        }
        print(nums);

        //从右往左遍历
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i-1] > arr[i] && nums[i-1]<=nums[i]) {
                nums[i-1] = nums[i] + 1;
            }
        }

        print(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
    public static int minNumberDisappeared (int[] nums) {
        // write code here
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
           map.put(nums[i],nums[i]);
        }
        System.out.println(map);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(map.containsKey(i+1));
            if(!map.containsKey(i+1)){
                return i+1;
            }
        }
        return nums.length+1;
    }
    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println("");
    }

    public static int[] FindNumsAppearOnce(int[] array) {
        // write code here

        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }

        for(int i=0;i<array.length;i++){
            if(map.get(array[i]) == 1){
                res.add(array[i]);
            }
        }

        Collections.sort(res);
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
       return  arr;
    }

}
