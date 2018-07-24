package com.jnshu.service.impl;

import com.jnshu.entity.User;
import com.jnshu.service.EmailService;
import com.jnshu.util.SendMailSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailServiceImpl")
public class EmailServiceImpl implements EmailService {
    @Autowired
    SendMailSDK sendMailSDK;

    @Override
    public boolean sendMail(String httpUrl, User user,String randInt){
        return sendMailSDK.sendMail(httpUrl, user, randInt);
    }
    @Override
    public boolean sendMailReal(String httpUrl,User user,String subject,String fromName,String randInt){
        return sendMailSDK.sendMail(user, subject, httpUrl, fromName, randInt);
    }
}
