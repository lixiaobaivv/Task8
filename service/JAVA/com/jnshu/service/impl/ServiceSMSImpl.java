package com.jnshu.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.jnshu.entity.User;
import com.jnshu.service.ServiceSMS;
import com.jnshu.service.UserService;
import com.jnshu.util.RandNum;
import com.jnshu.util.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceSMSImpl")
public class ServiceSMSImpl implements ServiceSMS {

    private Logger logger = LoggerFactory.getLogger(ServiceSMSImpl.class);

    @Autowired
    UserService userService;
    @Autowired
    SMSUtil smsUtil;

    @Override
    public boolean iphonev(String userIphone) throws ClientException{
        User user = userService.findIphone(userIphone);
        if (user != null){
            String code = RandNum.getRandLength(6);
            logger.info("用户的验证码为"+ code);
            user.setCode(code);
            userService.updateUser(user);
            boolean flag = smsUtil.SMSclient(userIphone,code);
            logger.info("返回值" + flag);
            return flag;
        }
        return false;
    }

    @Override
    public boolean iphonelogin(String userIphone,String code){
        User user = userService.findIphone(userIphone);
        if (user != null && code.equals(user.getCode())){
            return true;
        }
        return false;
    }
}
