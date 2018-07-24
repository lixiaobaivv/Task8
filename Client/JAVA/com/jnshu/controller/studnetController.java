package com.jnshu.controller;


import com.jnshu.entity.UserQv;
import com.jnshu.service.UserQvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class studnetController {
    //日志
    private Logger logger = LoggerFactory.getLogger(studnetController.class);

    @Autowired
    UserQvService userQvService;

    /**
     * 查询所有学生
     * @param userQv
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String userList(UserQv userQv, Model model) throws Exception {
        logger.info("查询所有的学生:" + userQv);
        List<UserQv> user1 = userQvService.queryUser(userQv);
        model.addAttribute("itemsList", user1);
        return "student";
    }

    /**
     * 增加学生信息
     * @param userQv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user1",method = RequestMethod.PUT, produces = "application/test;charset=utf-8")
    public String addUser(UserQv userQv)throws Exception{
        System.out.println(userQv);
        userQvService.addUser(userQv);
        return "redirect:/all";
    }

    /**
     * 删除学员信息
     * @param userQv
     * @return
     */

    @RequestMapping(value = "/student/{id}",method = RequestMethod.DELETE)
    public String deleteUser(UserQv userQv){
        logger.info("删除学员信息:"+ userQv);
        userQvService.deleteUser(userQv.getId());
        return "redirect:/user";
    }

    /**
     * 根据学生姓名查找数据
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public ModelAndView queryName(String name) throws Exception {
        System.out.println("yang");
        logger.info("根据学生姓名查找数据:" + name);
        List<UserQv> user1 = userQvService.queryName(name);
        System.out.println(user1.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", user1);
        modelAndView.setViewName("student");
        return modelAndView;
    }

    /**
     * 更新学生信息
     * @param id
     * @param userQv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/up/{id}",method = RequestMethod.POST,produces = "application/test;charset=utf-8")
    public String updateUser(@PathVariable Long id, UserQv userQv) throws Exception {
        System.out.println(userQv.toString());
        logger.info("更新学生的信息:" + userQv);
        userQvService.updateId(id, userQv);
        return "redirect:/all";
    }

    /**
     * 更新的跳转页面
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public String queryId(@PathVariable Long  id, Model model) throws Exception {
        logger.info("更新跳转的页面"+ id);
        UserQv userQv = userQvService.queryId(id);
        model.addAttribute("itemsList", userQv);
        return "studentUp";
    }

}
