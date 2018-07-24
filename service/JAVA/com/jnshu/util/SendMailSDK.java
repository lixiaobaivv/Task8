package com.jnshu.util;

import com.jnshu.entity.User;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SendMailSDK {
    private Logger logger = LoggerFactory.getLogger(SendMailSDK.class);
    //认证
    private String apiUser = "lixiaobai_test_JhQvSo";
    private String apiKey = "tQBfmb4oQiX9XiKF";
    /*邮件发送口*/
    private String apiUrl = "http://api.sendcloud.net/apiv2/mail/send";
    //发件人邮箱
    private String from = "sendcloud@sendcloud.org";
    //收件人邮箱
    /*private String rcpt_to = "1003410156@qq.com";*/

    //邮件标题
    private String subject = "测试";
    //邮件内容
    // private String html = "这个是一个测试";
    private String sendBodyBegin = "<html><H1><a href=\"";
    private String sendBodyEnd = "\">点击验证邮箱,五分钟后失效</a></H1></html>";
    //发件人名称
    private String fromName = "李小白";

    //外部调用的方法
    //只提供发送邮箱
    public Boolean sendMail(User user,String subject,String httpUrl,String fromName,String randInt){
        return sendMailReal(user,subject,httpUrl,fromName,randInt);
    }
    public Boolean sendMail(String httpUrl, User user,String randInt ){

        return sendMailReal(user,subject,httpUrl,fromName,randInt);
    }
    private Boolean sendMailReal(User user,String subject,String httpUrl,String fromName,String randInt){
        //构建http请求
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //拼接验证url httpurl 当前项目的目录
        String sendBody = sendBodyBegin + httpUrl + "/" + randInt + sendBodyEnd;
        logger.info("拼接发送的内容"+sendBody);
        //http发送的内容
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser",apiUser));
        params.add(new BasicNameValuePair("apiKey",apiKey));
        params.add(new BasicNameValuePair("to",user.getEmali()));
        //该值是我们的发件邮箱，只能设置sendcloud上绑定
        params.add(new BasicNameValuePair("from",from));
        logger.info("这里的地址为？？"+from+fromName);
        params.add(new BasicNameValuePair("fromName",fromName));
        params.add(new BasicNameValuePair("subject",subject));
        params.add(new BasicNameValuePair("html",sendBody));

        try{
            //发送的请求将数据转换为json格式
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            //接收请求
            HttpResponse response = httpClient.execute(httpPost);
            //判断请求是否发送成功
            if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                //正常返回  解析返回的数据
                response.getEntity();
                System.out.println(EntityUtils.toString(response.getEntity()));
                return true;
            }else {
                logger.info("请求发送失败");
                return false;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            logger.info("UrlEncodedFormEntity 转换失败导致发送失败的请求");
            return false;
        }catch (ClientProtocolException e){
            e.printStackTrace();
            logger.info("httpClient.execute(httpPost)请求接收失败");
            return false;
        }catch (IOException e){
            e.printStackTrace();
            logger.info("EntityUtils.toString(response.getEntity()) 转换失败");
            return false;
        }finally {
            httpPost.releaseConnection();
        }
    }
}
