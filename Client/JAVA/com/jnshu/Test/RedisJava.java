package com.jnshu.Test;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisJava {
    public static void main(String [] args){
        //连接本地的Redis服务
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行:" + jedis.ping());
        //设置 redis字符串数据
        jedis.set("runoobkey","www.baidu.com");
        //获取存储的数据并数据
        System.out.println("redis 存储的字符串为:" + jedis.get("runoobkey"));
        //存储数据到列表中
        jedis.lpush("site-list","Runoob");
        jedis.lpush("site-list","baidu");
        jedis.lpush("site-list","albaba");
        //获取存储的数据并输出
        List<String > list = jedis.lrange("site-list",0,2);
        for (int i=0; i<list.size();i++){
            System.out.println("列表项为:" + list.get(i));
        }
        //获取数据并输出
        Set<String > keys = jedis.keys("*");
        Iterator<String > it = keys.iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
        System.out.println("之前在服务器测试set的数据" + jedis.get("foo"));
    }
}
