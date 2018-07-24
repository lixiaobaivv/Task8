package com.jnshu.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

public class TestUtil {

    @Value("${product}")
    private String product;
    @Value("${domain}")
    private String domain;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${signName}")
    private String signName;
    @Value("${templateCode}")
    private String templateCode;

    private String generateCheckCode() { //生成开头不为0的6位随机验证码
        Random random = new Random();
        StringBuffer checkCode = new StringBuffer();
        //生成1-9的随机整数
        checkCode.append(random.nextInt(9) + 1);
        //循环添上5个0-9的随机整数
        for (int i = 1; i < 6; i++) {
            checkCode.append(random.nextInt(10));
        }
        //返回6位随机验证码
        return checkCode.toString();
    }

    public String sendShortMessage(String phoneNumber) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
//        String checkCode = generateCheckCode();//此处是生成6位数验证码工具
            String checkCode = generateCheckCode();
            //request.setTemplateParam("{\"code\":\"123\"}");//测试用,此处json一定要严格按照json格式书写
            request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");

            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//            request.setOutId("yourOutId");

            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                System.out.println(sendSmsResponse.getCode());
                return checkCode;
            } else {
                return null;
            }
        } catch (ClientException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
