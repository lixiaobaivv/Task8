package com.jnshu.service;

import com.jnshu.entity.User;

public interface EmailService {
    boolean sendMail(String httpUrl, User user,String randInt);
    boolean sendMailReal(String httpUrl,User user,String subject,String fromName,String randInt);

}
