package com.jnshu.util;

import java.util.List;

public class alitoqiniuUtil {
    public static void AliToQiNiu() {
        //获取阿里云所有文件名字
        List<String> keys = AliyunOSSAPI.getAllfileName();
        //逐个下载并上传到七牛云
        for (String key : keys) {
            QiniuOSSAPI.updateload(AliyunOSSAPI.downLoad(key), key);
        }
    }

    public static void QiNiuToALi() {
        //获取七牛云所有文件名字
        List<String> keys = QiniuOSSAPI.getAllFileName();
        //逐个下载上传到阿里云
        for (String key : keys) {
            AliyunOSSAPI.upLoad(QiniuOSSAPI.downLoad(key), key);
        }
    }
}