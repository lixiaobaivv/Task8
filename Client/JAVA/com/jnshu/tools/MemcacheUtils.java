package com.jnshu.tools;

import com.whalin.MemCached.MemCachedClient;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class MemcacheUtils {

    private static MemCachedClient cachedClient;

    /* 实例化 MemCachedClient */
    /* memCachedPool 带名字的连接池对象*/
    static {
        if (cachedClient == null){
            System.out.println("初始化中");
            cachedClient = new MemCachedClient("memCachedPool");
        }
    }

    //空的构造函数
    private MemcacheUtils(){
    }

    //set 方法 由于形参不同，方法重载

    /**
     * @Description: 无过期时间，向缓存添加新的键值对，如果键已存在，那么值将被替换
     * @Param: [key,value]键，值
     * @return: boolean
     * @Author: Mr.yang
     * @Date: 2018
     */
    public static boolean set(String hello, String key, Object value){
        return setExp(key,value,null);
    }

    /**
     * @Description: 有过期时间，向缓存添加新的键值对，如果键已存在，那么将被替换
     * @param :[key,value,expire]键，值，过期时间
     * @param value
     * @param expire
     * @return: boolean
     * @Date: 2018
     */

    public static boolean set(String key, Object value, Date expire){
        return setExp(key,value,expire);
    }

    /**
     * @Description: set实际执行方法 向缓存中添加新的键值对，如果键已经存在，则之前的值都将被替换
     * @param key:[key,value,expire]键，值，过期时间
     * @param value
     * @Date: 2018
     * @return
     */

    private static boolean setExp(String key, Object value, Date expire) {
        boolean flag = false;
        try {
            flag = cachedClient.set(key,value,expire);
        }catch (Exception e){
            MemcachedLog.writeLog("Memcached add方法报错，key值:"+ key + "\r\n" + exceptionWrite(e));
        }
        return flag;
    }
    /* set end */

    /**
     * @Description 不带过期时间 仅当缓存中不存在键时，add命令才会向缓存中添加一个键值对
     * @param :[key value]键，值
     * @return boolean
     */

    public static boolean add(String key,Object value){
        return addExp(key,value,null);
    }

    /**
     * @Description 带过期时间 仅当缓存中不存在键时，add命令才会向缓存汇总添加一个键值对
     * @param :[key,value,date]键，值，过期时间
     * @return boolean
     */
    public static boolean add(String key,Object value,Date date){
        return addExp(key,value,date);
    }

    /**
     * @Description add实际执行方法 仅当缓存中不存在键时，add命令才会向缓存中添加键值对
     * @param key 键
     * @param value 值
     * @param expire 过期时间
     * @return
     */
    private static boolean addExp(String key, Object value, Date expire) {
        boolean flag = false;
        try{
            flag = cachedClient.add(key,value,expire);
        }catch (Exception e){
            MemcachedLog.writeLog("Memcached add方法报错,key值:" + key + "\r\n" + exceptionWrite(e));
        }
        return flag;
    }


    /* replace 重载 */

    /**
     * @Description: 无过期时间 仅当键值已经存在时，replace 命令才会替换缓存中的值。
     * @Param: [key,value]键，值
     * @return: boolean
     * @Date: time
     */
    public static boolean replace(String key,Object value){
        return replaceExp(key,value,null);
    }

    /**
     * @Description: 有过期时间 仅当键存在时, replace 命令才会替换缓存中的键
     * @Param: [key, value, expire] 键, 值, 过期时间
     * @return: boolean
     */
    public static boolean replace(String key, Object value, Date expire) {
        return replaceExp(key, value, expire);
    }

    /**
     * @Description: replace实际执行方法，仅当键已经存在时 replace命令才会替换缓存中的键
     * @param: [key,value,expire]键，值，过期时间
     * @return: boolean
     * @Date: time
     */

    private static boolean replaceExp(String key, Object value, Date expire) {
        boolean flag = false;
        try{
            flag = cachedClient.replace(key,value,expire);
        }catch (Exception e){
            MemcachedLog.writeLog("Memcached replace方法报错,key值:" + key + "\r\n" + exceptionWrite(e));
            }
            return flag;
        }

        /* replace end */

    /* get */

    /**
     * @Description: 用于检索之前添加的键值 获取其相对的值
     * @Param: [key] 键
     * @return: java.lang.Object
     */

    public static Object get(String key) {
        Object object = null;
        try {
            System.out.println("get key:" + key);
            object = cachedClient.get(key);
        } catch (Exception e) {
            MemcachedLog.writeLog("Memcached get方法报错,key值:" + key + "\r\n" + exceptionWrite(e));
        }
        return object;
    }
    /* get end */

    /* delete */
    /**
     * @Description: 删除 memcached 中的任何现有值。
     * @Param: [key] 键
     * @Param: @SuppressWarnings("deprecation") 抑制相对于弃用的警告
     * @return: boolean
     * @Author: Mr.Wang
     * @Date: time
     */
    // @SuppressWarnings("deprecation") 这里不使用废弃的方法
    public static boolean delete(String key){
        boolean flag = false;
        try{
            flag = cachedClient.delete(key);
        }catch (Exception e){
            MemcachedLog.writeLog("Memcached delete方法报错, key值:");
        }
        return false;
    }
    /* delete end */


    /* flash */
    /**
     * @Description: 清理缓存中的所有键/值对
     * @Param: []
     * @return: boolean
     * @Author:
     * @Date:
     */
    public static boolean flashAll(){
        boolean flag = false;
        try {
            flag = cachedClient.flushAll();
        }catch (Exception e){
            MemcachedLog.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));
        }
        return flag;
    }

    /**
     * @Description: 返回堆栈信息
     * @Param: [e] 异常信息
     * @return: java.lang.String
     * @Author:
     * @Date:
     */

    private static String exceptionWrite(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}

