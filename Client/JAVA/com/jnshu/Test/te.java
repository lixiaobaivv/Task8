package com.jnshu.Test;


import com.jnshu.util.RedisUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class te {

    public static void main(String [] args)throws Exception{
        @SuppressWarnings("resource")
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:redis-context.xml");
        RedisUtil redisUtil=(RedisUtil) context.getBean("redisUtil");
        //=================testString============
        redisUtil.set("name","白晨松");
        redisUtil.set("age",18);
        redisUtil.set("address","不详");
        System.out.println(redisUtil.set("address","不详",50));
        System.out.println(redisUtil.get("age"));
        redisUtil.set("age",100);
        Object o = redisUtil.get("user2");
        System.out.println(o);
        redisUtil.del("address");
        redisUtil.set("class",15);
        long incr = redisUtil.incr("a",1);
        System.out.println(incr);
        Thread.sleep(5000);
        Map<String ,Object> map = new HashMap<>();
        map.put("name","白白晨松");
        map.put("age",18);
        map.put("address","不详22");
        redisUtil.hmset("1003410156",map,1000);
        redisUtil.del("1003410156");
        redisUtil.hset("1003410156","address","不详",1000);
        redisUtil.hdel("1003410156","name");
        System.out.println(redisUtil.sSetAndTime("1003410156",1000,"hhh"));
        System.out.println(redisUtil.sGet("1003410156"));
        System.out.println(redisUtil.sHasKey("1003410156","name"));
        System.out.println(redisUtil.lRemove("1003410156",1,2));
        System.out.println(redisUtil.lGet("1003410156",0,-1));
        System.out.println(redisUtil.lGetListSize("1003410156"));
        System.out.println(redisUtil.lGetIndex("1003410156",1));

        System.out.println(redisUtil.getExpire("1003410156"));
        System.out.println(redisUtil.hget("1003410156","name"));
        System.out.println(redisUtil.hmget("1003410156"));

    }
}
class User{
    private String name;
    private Integer age;
    private String address;
    private Double classz;
    private Float classz2;
    public User(){
        super();
    }
    public User(String name,Integer age,String address,Double classz,Float classz2){
        super();
        this.name = name;
        this.age = age;
        this.address = address;
        this.classz = classz;
        this.classz2 = classz2;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Double getClassz() {
        return classz;
    }
    public void setClassz(Double classz) {
        this.classz = classz;
    }
    public Float getClassz2() {
        return classz2;
    }
    public void setClassz2(Float classz2) {
        this.classz2 = classz2;
    }

}