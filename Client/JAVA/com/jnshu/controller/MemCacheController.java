/*
package com.jnshu.controller;

import com.jnshu.entity.User;
import com.jnshu.service.UserQvservice;
import com.jnshu.tools.MemcacheUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

*/
/**
 * @program: taskTwo
 * @description: MemCache 测试类
 * @author:
 * @create:
 **//*


@Controller
@RequestMapping("/memcache")
public class MemCacheController {
    @Autowired
    UserQvservice userQvservice;
    private final Logger logger = LoggerFactory.getLogger(MemCacheController.class);

    //获取参数的时候 要写给一个参数进去给他
    @RequestMapping(value = "/findSessionByKey/{key}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object findBykey(@PathVariable String key) {
        System.out.println("MemcacheUtils测试:" + key);
        logger.info("MemCacheControoler.findByKey param:key=" + key);
        if (StringUtils.isEmpty(key)) {
            return "key must not be empty or null";
        }
        return MemcacheUtils.get(key);
    }

    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object findByKey(@RequestBody @PathVariable("id") String key) {
        if (StringUtils.isEmpty(key)){
            return false;
        }
        return MemcacheUtils.get("userA" + key);
    }

    */
/**
     * @param key  键
     * @param user 值
     * @return boolean
     * @Description 缓存更新接口 当key不存在时取消更新
     *//*


    @RequestMapping(value = "/api/{id}", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    @ResponseBody
    public boolean updateByKey(@PathVariable("id") String key, @RequestBody User user) {
        user.setId(Integer.valueOf(key));
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return MemcacheUtils.replace("user" + key, user);
    }

    */
/**
     * @param key  键
     * @param user 值
     * @return boolean
     * @Description:增加缓存数据 当键存在时取消存入
     *//*


    @RequestMapping(value = "/api/{id}", method = RequestMethod.PUT, produces = "application/json;charset=utf8")
    @ResponseBody
    public boolean insert(@PathVariable("id") String key, @RequestBody User user) {
        System.out.println(user.toString());
        user.setId(Integer.valueOf(key));
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return MemcacheUtils.add("user" + key, user);
    }

    */
/**
     * @param key
     * @return java.lang.Boolean
     * @Description: 删除指定key
     *//*


    @RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteByKey(@PathVariable("id") String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return MemcacheUtils.delete("user" + key);
    }

    */
/**
     * @return
     * @Description: 清除缓存中的所有键值对
     * @Param: []
     *//*


*/
/*    @RequestMapping(value = "/api/all", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean flashAll() throws Exception {
        return MemcacheUtils.flash();
    }*//*



}
*/
