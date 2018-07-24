package com.jnshu.service;

import com.aliyuncs.exceptions.ClientException;

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
