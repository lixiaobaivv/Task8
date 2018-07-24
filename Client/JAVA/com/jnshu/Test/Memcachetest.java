package com.jnshu.Test;

import com.jnshu.entity.UserBean;


import com.jnshu.tools.MemcachedUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-memcache.xml")
public class Memcachetest {

@Test
    public void testmemcached(){
    MemcachedUtil.put("hello","word",60);
    String hello = (String )MemcachedUtil.get("hello");
    TestCase.assertEquals("word",hello);

    for (int i = 0; i < 100; i++){
        UserBean userBean = new UserBean("lixiaobai" + i, "123456-" + i);
        MemcachedUtil.put("user" + i,userBean,60);
        Object obj = MemcachedUtil.get("user" + i);
        TestCase.assertEquals(userBean,obj);
        System.out.println(userBean);
    }
}


}
