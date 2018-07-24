package com.jnshu.tools;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtil {
    /**
     * memcached 客户端
     */
    //创建全局唯一的实例
    private static MemCachedClient cachedClient = new MemCachedClient();

    protected static MemcachedUtil memCached = new MemcachedUtil();

    /**
     * 初始化连接池
     */
    static {
        //获取连接池的实例
        SockIOPool pool = SockIOPool.getInstance();

        //服务器列表及其权重
        String [] servers = {"127.0.0.1:11211"};
        Integer[] weights = {3};

        //设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);

        //设置初始化连接数，最小连接数，最大连接数，最大处理时间
        pool.setInitConn(10);
        pool.setMinConn(10);
        pool.setMaxConn(1000);
        pool.setMaxIdle(1000*60*60);

        //设置连接池守护线程的睡眠时间
        pool.setMaintSleep(60);

        //设置Tcp参数，连接超时
        pool.setNagle(false);
        pool.setSocketTO(60);
        pool.setSocketConnectTO(0);

        //初始化并启动连接池
        pool.initialize();
    }

    /**
     * 保护构造方法，不允许实例化
     */
    private MemcachedUtil(){
    }

    /**
     * 添加一个指定的值到缓存中
     * @param key
     * @param value
     * @return
     */

    public static boolean add(String key,Object value){
        return cachedClient.add(key,value);
    }

    public static boolean add(String key,Object value,Integer expire){
        return cachedClient.add(key,value,expire);
    }

    /**
     * 新建？一个指定的值到缓存中
     * @param key
     * @param value
     * @return
     */
    public static boolean put(String key,Object value){
        return cachedClient.set(key,value);
    }

    public static boolean put(String key,Object value,Integer expire){
        return cachedClient.set(key,value,expire);
    }

    /**
     *  替换一个指定的值到缓存中？
     * @param key
     * @param value
     * @return
     */
    public static boolean replace(String key,Object value){
        return cachedClient.replace(key,value);
    }

    public static boolean replace(String key,Object value,Integer expire){
        return cachedClient.replace(key,value,expire);
    }

    /**
     * 删除一个指定的值到缓存中
     * @param key
     * @return
     */

    public static boolean delete(String key){
        return cachedClient.delete(key);
    }

    /**
     * 根据指定的关键字获取对象
     * @param key
     * @return
     */

    public static Object get(String key){
        return cachedClient.get(key);
    }
}
