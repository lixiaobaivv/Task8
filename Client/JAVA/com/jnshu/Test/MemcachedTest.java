package com.jnshu.Test;



import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.junit.Test;

public class MemcachedTest {
    @Test
    public void testMemcache(){
        //配置服务端的地址，
        String [] servers = {"127.0.0.1:11211"};
        Integer[] weights = {3};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setWeights(weights);
        //初始化时对每个服务器建立的连接数目
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle( 1000 * 60 * 60 * 6);
        //设置主线程的睡眠时间
        pool.setMaintSleep(30);
        //设置TCP的参数，连接超时等
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketTO(0);
        //初始化连接池
        pool.initialize();
        MemCachedClient mcc = new MemCachedClient();
        boolean success = mcc.set("name","路飞");
        mcc.set("test3", "1234");
        System.out.println(success);
        String name = (String )mcc.get("name");
        String name2 = (String)mcc.get("testkey");
        System.out.println(name);
        System.out.println(name2);
    }


}
