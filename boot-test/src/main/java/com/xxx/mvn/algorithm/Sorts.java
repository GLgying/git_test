package com.xxx.mvn.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 1.冒泡
 * 2.选择
 * 3.插入
 * 4.快速
 */
public class Sorts {
    public static int[] arr = {18,3,6,8,2,41,36,25,99,16};

    @Test
    public void test(){
//        bubbling();
//        choose();
//        select();
        quick(arr,0,arr.length-1);
        print(arr);
    }


    /**
     * 冒泡排序
     *  交换位置
     * 相邻两个逐个比较
     * 可双排（从两端开始排）
     * 平均时间复杂度也是 O(n^2) 空间复杂度为常数阶 O(1)
     * 注意临界
     */
    public static void bubbling(){
        //1. 健壮性 判断  1. 数组是否为空
        print(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                //相邻两个比较  如果后一个小于前一个 交换
                if(arr[j]>arr[j+1]){
                    swap(arr, j,j+1);
                }
            }
        }
        print(arr);
    }

    /**
     * 选择排序
     *  交换位置
     * 先拍出来一个 最大值 或者 最小值  相当于 冒泡排序的优化
     *  可双排（从两端开始排）
     *  平均时间复杂度也是 O(n^2) 空间复杂度为常数阶 O(1)
     *  注意临界
     */
    public static void choose(){
        print(arr);
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                //先排最值（小/大）
                if(arr[i]>arr[j]){
                    swap(arr, i,j);
                }
            }
        }
        print(arr);
    }

    /**
     * 插入排序
     *  扑克牌
     *
     *  注意临界
     *  平均时间复杂度也是 O(n^2) 空间复杂度为常数阶 O(1)
     */
    public static void select(){
        print(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >0; j--) {
               // 与前一个相比   小于前一个则交换
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
//            print(arr);
        }
        print(arr);
    }


    /**
     * 快速排序
     *  来源于冒泡排序   不稳定
     *
     *  注意临界
     *  平均时间复杂度也是 O(n^2) 空间复杂度为常数阶 O(1)
     */
    public static void quick(int[] arr,int left,int right){
        //1. arr 为空  2. right<left return;
        if(left>right){
            return;
        }
        int base = arr[left];
        int i = left;
        int j = right;
        //相等表示检索完成
        while (i != j){
            // 右侧大于 继续左移
           while (arr[j]>=base && i<j){
               j--;
           }
            // 左侧小于 继续右移
            while (arr[i]<=base && i<j){
                i++;
            }
            // 左侧大于  右侧小于  交换
            swap(arr,i,j);

        }
        //相等 时  交换
        arr[left] = arr[i];
        arr[i] = base;

        //递归
        quick(arr,left,i-1);
        quick(arr,i+1,right);
    }


    public static void print(int[] arr){
        for (int i:arr) {
            System.out.print(i + "  ");
        }
            System.out.println( "  ");
    }

    public static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
