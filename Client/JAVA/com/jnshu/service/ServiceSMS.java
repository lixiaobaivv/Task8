package com.jnshu.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface ServiceSMS {

    /**
     * 手机验证
     * @param userIphone
     * @return
     * @throws ClientException
     */
    boolean iphonev(String userIphone)throws ClientException;

    /**
     * 手机登陆
     * @param userIphone
     * @param code
     * @return
     */
    boolean iphonelogin(String userIphone,String code);
}
