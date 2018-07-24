package com.jnshu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ServiceOSS {

    boolean uploadPicture(MultipartFile file,Long id);

 /*   //上传
    Boolean updateFile(Long id, MultipartFile multipartFile,String fileName);
    //InputStream 格式上传
    Boolean updateFile(Long id, InputStream inputStream,String fileName,String fileType);
    //删除
    Boolean deleteFile(String flieName);

    Boolean updateFile(Long id, byte[] bytes,String fileName,String fileType);

    //图片上传部分
    boolean uploadPicture(MultipartFile file, Long id);*/




}
