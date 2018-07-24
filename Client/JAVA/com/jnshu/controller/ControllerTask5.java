package com.jnshu.controller;


import com.aliyuncs.exceptions.ClientException;
import com.google.gson.Gson;
import com.jnshu.entity.Profession;
import com.jnshu.entity.Student;
import com.jnshu.entity.User;
import com.jnshu.service.*;
import com.jnshu.util.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("")
public class ControllerTask5 {

    private static final Charset CHARSET = Charset.forName("gb2312");
    private static Logger logger = LoggerFactory.getLogger(ControllerTask5.class);


    @Qualifier("rmiServer")
    @Autowired
    StudentService studentService;
    @Qualifier("professionService")
    @Autowired
    ProfessionService professionService;
    @Qualifier("userService")
    @Autowired
    UserService userService;
    @Qualifier("emailService")
    @Autowired
    EmailService sendMailSDK;
    @Qualifier("serviceSMS")
    @Autowired
    ServiceSMS serviceSMS;
    @Qualifier("serviceOSS")
    @Autowired
    ServiceOSS serviceOSS;
/*
    @Autowired
    SMSUtil smsUtil;
    @Autowired
    SendMailSDK sendMailSDK;
    @Autowired
    AliyunOSSAPI aliyunOSSAPI;
*/


    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String logins() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/ajax")
    public String get(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        List<Student> students = studentService.findAll();
        String str = gson.toJson(students);
        System.out.println(str + "++++++=");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(str.toString());
        return null;
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage() {
        System.out.println("--------------+++++++++--------------");
        return "login";
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> stu = studentService.findAll();
        modelAndView.addObject("stu", stu);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * 职业表
     *
     * @return
     */
    @RequestMapping("/u/profession")
    public ModelAndView home2() {
        ModelAndView modelAndView = new ModelAndView();
        List<Profession> pro = professionService.findAlls();
        modelAndView.addObject("pro", pro);
        modelAndView.setViewName("profession");
        return modelAndView;
    }

    /**
     * 登录验证
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("进入controller方法");
        //获取数据姓名参数
        String name = httpServletRequest.getParameter("name");
        //获取数据里密码参数
        String password = httpServletRequest.getParameter("password");
        logger.info(name + password + "密码");
        //使用MD5工具类对密码进行加密
        String password1 = MD5Util.createPassword(password);
        logger.info(password1 + "加密后密码");
        //查询数据表里的姓名 密码 进行比对 与这个jsp页面里输入的是否一致
        User user = userService.selectByuser(name, password1);
        logger.info(user + "123123");
        // 如果 这个数据表里 没有这些信息 则返回登录页面，否则就进入
        if (user == null) {
            logger.info("登陆失败");
            httpServletRequest.getSession().setAttribute("errorInfo", "用户名错误");
            return "redirect:login";
        } else {
            //加入登陆时间
            userService.updateTimeByid(user.getId());
            //重新读数据 ，拿出这个数据
            User user1 = userService.selectByuser(name, password1);

            logger.info(TokenUtil.genToken(user1.getId(), user1.getLandtime()));
            // 使用Token工具类来写入这个数据的id 和 登录时间
            String s_token = TokenUtil.genToken(user1.getId(), user1.getLandtime());
            logger.info(s_token + "token+++++++++");
            //创建一个新的token
            Cookie token = new Cookie("token", s_token);
            //响应写入token
            httpServletResponse.addCookie(token);
            logger.info("token");
            return "redirect:all";
        }
    }

    /**
     * 注销
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpServletRequest.getCookies();
        try {
            for (int i = 0; i < cookies.length; i++) {
                //System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                httpServletResponse.addCookie(cookie);
            }
        } catch (Exception ex) {
            logger.info("清空Cookies发生异常！");
        }
        return "redirect:/login";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "test";
    }

    /**
     * 注册
     * @param name
     * @param password
     * @param user
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String Register(@Param("name") String name, @Param("password") String password, User user, Model model) throws Exception {

        logger.info("进入注册页面");
        logger.info("前台传来信息:" + user.toString());
        User user1 = userService.findUserByname(user.getName());
        if (user1 != null) {
            logger.info("已经存在用户");
            return "test";
        } else {
            logger.info("开始注册");
            //给用户名加盐
            String salt = user.getName();
            //对密码加盐和MD5加密
            String password1 = MD5Util.createPassword(password);
            logger.info("加密加盐后的密码" + password1);
            user.setPassword(password1);
            user.setSalt(salt);
            userService.regist(user);
            logger.info("注册信息是:" + user);
            return "redirect:/login";
        }
    }

    /**
     * 手机注册页面
     * @return
     */
    @RequestMapping(value = "/iphone", method = RequestMethod.GET)
    public String iphone() {
        return "iphonelogin";
    }

    /**
     * 手机登陆
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/iphoneLogin", method = RequestMethod.POST)
    public String iphoneLogin(HttpServletRequest request, HttpServletResponse response) {
        logger.info("手机登陆" );
        String userIphone = request.getParameter("userIphone");
        String code = request.getParameter("code");
        if (serviceSMS.iphonelogin(userIphone,code)){
            logger.info("登陆成功");
            return "redirect:/home";
        }
        logger.info("登陆失败返回登陆页面");
        return "login";
    }
    @RequestMapping(value = "/SMS",method = RequestMethod.POST)
    @ResponseBody
    public Boolean SMS(String userIphone,HttpServletRequest request)throws ClientException{
        logger.info("接收的号码:" + userIphone);
        return serviceSMS.iphonev(userIphone);
    }
/*    @RequestMapping(value = "/iphoneLogin", method = RequestMethod.POST)
    public String iphoneLogin(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入手机登录页");
        String userIphone = request.getParameter("userIphone");
        String Code = request.getParameter("code");
        User user = userService.findIphone(userIphone);
        if (user != null && Code.equals(user.getCode())) {
            return "redirect:/home";
        }
        return "/login";
    }

    *//**
     * 手机短信登陆
     * @param userIphone
     * @param request
     * @return
     * @throws ClientException
     *//*
    @RequestMapping(value = "/SMS", method = RequestMethod.POST)
    @ResponseBody
    public Boolean SMS(String userIphone, HttpServletRequest request) throws ClientException {
        logger.info("接收的号码:" + userIphone);
        User user = userService.findIphone(userIphone);
        logger.info("查询的号码:" + userIphone);
        if (user != null) {
            String Code = RandNum.getRandLength(6);
            logger.info("验证码:" + Code);
            user.setCode(Code);
            userService.updateUser(user);
            logger.info("更新的用户数据" + user);
            boolean flag = smsUtil.SMSclient(userIphone,Code);
            logger.info("发送的手机号和验证码" + userIphone + Code);
            logger.info("返回值" + flag);
            return flag;
        } else {
            return false;
        }
    }*/

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/Maillogin", method = RequestMethod.POST)
    public String Malilogin(HttpServletRequest request) {
        logger.info("进入邮箱登录");
        String userEmail = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.findMaiUser(userEmail);
        if (user != null && user.getEmailState() == 1) {
            //对密码进行加密然后和数据库做比较
            String pssword1 = MD5Util.createPassword(password);
            logger.info("加密后的密码" + pssword1);
            if (user.getPassword().equals(pssword1)) {
                logger.info("登录成功");
                return "redirect:home";
            }
        }
        return "redirect:login";
    }

    /**
     * 邮箱
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/verifiEmail/{code}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean verifEmail(@PathVariable(value = "code") String code) {
        User user = userService.findCodeUser(code);
        if (userService.findCodeUser(code) != null && (user.getCode()).equals(code)) {
            user.setEmailState(1);
            userService.updateUser(user);
            logger.info("是否更新成功" + userService);
            return true;
        }
        return false;
    }

    /**
     * 邮箱验证
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    @ResponseBody
    public Boolean sendmail(HttpServletRequest request, User user) {
        String url = String.valueOf(request.getRequestURL());
        logger.info("这里的url是多少" + url);
        String httpurl = url.split("/sendmail")[0] + "/verifiEmail";
        logger.info("这里显示访问的项目网址为" + httpurl);
        String randInt = RandNum.getRandLength(6);
        logger.info("邮箱里生成的6位数验证码为" + randInt);
        user.setEmailState(0);
        user.setCode(randInt);
        userService.updateUser(user);
        return sendMailSDK.sendMail(httpurl,user,randInt);
    }

    /**
     * 图片上传
     * @param model
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String load(Model model) {
        long id = 64;
        User user = userService.selectByid(id);
        model.addAttribute("u", user);
        return "/upload";
    }

    /**
     * 图片上传
     *
     * @param request
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Boolean uploadfile(HttpServletRequest request,MultipartFile file,Long id)throws IOException{
        logger.info("进入图片上传" + file);
        return serviceOSS.uploadPicture(file,id);
    }
/*    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean uploadFile(HttpServletRequest request, MultipartFile file, Long id) throws IOException {
        logger.info("数据库里的id为:" + id);
        logger.info(file.toString());
        System.out.println("fileName===" + file.getOriginalFilename());
        System.out.println("fileSize===" + file.getSize());
        System.out.println("fileContentType===" + file.getContentType());
        System.out.println("fileString===" + file.toString());
        System.out.println("name===" + file.getName());
        System.out.println("fileInputStream===" + file.getInputStream());
        System.out.println("fileBytes===" + Arrays.toString(file.getBytes()));
        String fileName = MD5Util.getMultipartFileMd5(file);
        Boolean a = aliyunOSSAPI.updateFile(id, file, fileName);
        if (a) {
            logger.info("成功上传阿里云");
            String head_tx = aliyunOSSAPI.geturl1(fileName);
            logger.info("返回的阿里云url为  " + head_tx);
            User user = new User(id, head_tx);
            userService.updatetx(user);
            return true;
        }
        return false;
    }*/

    /**
     * 阿里云存储文件全部迁移到七牛云存储
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/alitoqiniu", method = RequestMethod.GET)
    public String aLiToQiNiu(ModelMap modelMap) {

        alitoqiniuUtil.AliToQiNiu();
        modelMap.addAttribute("dataMigration", "数据已从阿里云迁移到七牛云！");
        return "dataMigration";
    }

    /**
     * 七牛云存储文件全部迁移到阿里云存储
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/qiniutoali", method = RequestMethod.GET)
    public String QiNiuToALi(ModelMap modelMap) {
        alitoqiniuUtil.QiNiuToALi();
        modelMap.addAttribute("dataMigration", "数据已从七牛云迁移到阿里云！");
        return "dataMigration";
    }

    /**
     * 用户展示页面
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/us/{id}",method = RequestMethod.GET)
    public String user(Model model,User user){
        logger.info("用户展示页面的数据:" + user);
        User user1 = userService.selectByid(user.getId());
        model.addAttribute("u",user1);
        return "user";
    }
}