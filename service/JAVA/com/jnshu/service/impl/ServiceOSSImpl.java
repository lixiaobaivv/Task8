package com.jnshu.service.impl;

import com.jnshu.entity.User;
import com.jnshu.service.ServiceOSS;
import com.jnshu.service.UserService;
import com.jnshu.util.AliyunOSSAPI;
import com.jnshu.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service("serviceOSSImpl")
@Component
public class ServiceOSSImpl implements ServiceOSS {
    Logger logger = LoggerFactory.getLogger(ServiceOSSImpl.class);
    @Autowired
    UserService userService;
    @Autowired
    AliyunOSSAPI aliyunOSSAPI;

/*    @Override
    public Boolean updateFile(Long id, MultipartFile multipartFile,String fileName){
        return aliyunOSSAPI.updateFile(id, multipartFile, fileName);
    }

    @Override
    public Boolean updateFile(Long id, InputStream inputStream,String fileName,String fileType){
        return aliyunOSSAPI.updateFile(id, inputStream, fileName, fileType);
    }
    @Override
    public Boolean deleteFile(String fileName){
        return aliyunOSSAPI.deleteFile(fileName);
    }
    public Boolean updateFile(Long id, byte[] bytes,String fileName,String fileType){
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return updateFile(id,inputStream,fileName,fileType);
    }*/

    @Override
    public boolean uploadPicture(MultipartFile file,Long id){
        String filName = MD5Util.getMultipartFileMd5(file);
        Boolean a = aliyunOSSAPI.updateFile(id,file,filName);
        if (a){
            logger.info("成功上传阿里云");
            String url = aliyunOSSAPI.geturl1(filName);
            logger.info("返回云url:" + url);
            User user = new User(id,url);
            userService.updatetx(user);
            return true;
        }
        return false;
    }

}
