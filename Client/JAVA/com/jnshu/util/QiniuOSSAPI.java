package com.jnshu.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qiniu.storage.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class QiniuOSSAPI {

    private static Logger logger = LoggerFactory.getLogger(QiniuOSSAPI.class);
    private static String accessKey;
    private static String secretKey;
    private static String bucket;

    /**
     * 上传
     * @param inputStream
     * @param fileName
     */

    public static void updateload(InputStream inputStream,String fileName){
        Auth auth = Auth.create(accessKey,secretKey);
        String token = auth.uploadToken(bucket,fileName);
        boolean isException = false;
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        UploadManager uploadManager = new UploadManager(c);
        try {
            Response res = uploadManager.put(inputStream, fileName, token, null, null);
        } catch (QiniuException e) {
            Response r = e.response;
            isException = true;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException el) {
            }
        } finally {
            if (!isException) {
                logger.info("Uploaded successfully");
            }
        }
    }

    /**
     * 下载
     * @param fileName
     * @return
     */
    public static InputStream downLoad(String fileName) {
        try {
            /*String fileName = "七牛/云存储/qiniu.jpg";*/
            String domainOfBucket = "http://img.summerwaves.cn";
            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
            String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
            System.out.println(finalUrl);
            return new URL(finalUrl).openStream();
        } catch (MalformedURLException urle) {
            logger.error("QiNiuYun downLoad throw a MalformedURLException");
        } catch (IOException IOe) {
            logger.error("QiNiuYun downLoad throw a IOException");
        }
        return null;
    }

    public static List<String> getAllFileName() {
        List<String> keys = new ArrayList<>();
        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";

        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                keys.add(item.key);
//                System.out.println(item.key);
//                System.out.println(item.hash);
//                System.out.println(item.fsize);
//                System.out.println(item.mimeType);
//                System.out.println(item.putTime);
//                System.out.println(item.endUser);
            }
        }
        return keys;
    }

    public static void deleteFile(String fileName) {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            logger.error("deleteFile throw a QiniuException,code is " + ex.error() + ",reason is " + ex.response.toString());
        }
    }

    public static void deleteAllFile() {
        List<String> keys = QiniuOSSAPI .getAllFileName();
        for (String key : keys) {
            deleteFile(key);
        }
    }

    public static String getThumbFileUrl(String fileName) {
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("%s/%s", "http://img.summerwaves.cn", encodedFileName+"!thumb");
    }


    public void setAccessKeyId(String accessKeyId) {
        QiniuOSSAPI .accessKey = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        QiniuOSSAPI .secretKey = accessKeySecret;
    }

    public void setBucketName(String bucketName) {
        QiniuOSSAPI.bucket = bucketName;
    }


}