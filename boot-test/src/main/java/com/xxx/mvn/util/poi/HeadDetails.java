package com.xxx.mvn.util.poi;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Liruilong
 * @Date 2021-01-20 15:37
 * @Description:
 */
public class HeadDetails {

    private List<HeadDetail> headDetails = new LinkedList<>();


    public static class HeadDetail{
        private String key;
        private String title;
        private int width = 50;
        //列数据单元格是否对齐
        private boolean  center = true;

        public String getKey() {
            return key;
        }

        public HeadDetail setKey(String key) {
            this.key = key;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public HeadDetail setTitle(String title) {
            this.title = title;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public HeadDetail setWidth(int width) {
            this.width = width;
            return this;
        }

        public boolean isCenter() {
            return center;
        }

        public HeadDetail setCenter(boolean center) {
            this.center = center;
            return this;
        }
    }

    public HeadDetails add(String key,String title,int width){
        this.headDetails.add(new HeadDetail().setTitle(title).setKey(key).setWidth(width));
        return this;
    }
    public HeadDetails add(String key,String title,int width,boolean center){
        this.headDetails.add(new HeadDetail().setTitle(title).setKey(key).setWidth(width).setCenter(center));
        return this;
    }
    public HeadDetails add(String key,String title){
        this.headDetails.add(new HeadDetail().setTitle(title).setKey(key));
        return this;
    }


    public List<HeadDetail>  builder(){
        return this.headDetails;
    }

    public int headSize(){
        return this.headDetails.size();
    }


    public static void main(String[] args) {
        //BiFunction
        BiFunction<Integer,Integer,Integer> biFunction = (x, y) -> x+y;
        Integer apply2 = biFunction.apply(1, 2);
        System.out.println(apply2);

    }


}