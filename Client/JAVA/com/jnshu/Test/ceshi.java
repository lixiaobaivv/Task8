package com.jnshu.Test;

public class ceshi {
    static int i=0;
    public static int ceshi(){
        try{
            i=i%1;
            System.out.println("执行try");
        }catch (Exception e){
            System.out.println("执行catch");
        }
        finally {
            System.out.println("执行finally");
        }
        return 4;
    }
    public static void main(String []args){
        System.out.println(ceshi());
    }
}
