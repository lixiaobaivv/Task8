package com.jnshu.Test;

import org.junit.Test;

public class TestE {
    public static int yang(){
        try {
            System.out.println("执行try");
        }catch (Exception e){
        System.out.println("执行catch");
    }
    finally {
            System.out.println("执行Finally");
        }
        return 4;
        }
        public static void main(String []args){
        System.out.println(yang());
        }
}


