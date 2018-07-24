package com.jnshu.service;

import com.jnshu.entity.User;

public interface EmailService {
    Boolean sendMail(String httpUrl, User user,String randInt);
    Boolean sendMailReal(String httpUrl,User user,String subject,String fromName,String randInt);

}
