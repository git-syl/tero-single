package com.oktfolio.tero.objectstorage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class AliyunOss {

    @Autowired
    private OSS oss;

    public void putObject(String bucketName, String key, InputStream inputStream) {
        oss.putObject(bucketName, key, inputStream);
    }

    public void putObject(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {
        oss.putObject(bucketName, key, inputStream, metadata);
    }

    public void putObject(String bucketName, String key, File file, ObjectMetadata metadata) {
        oss.putObject(bucketName, key, file, metadata);
    }

    public void putObject(String bucketName, String key, File file) {
        oss.putObject(bucketName, key, file);
    }

    public void putObject(PutObjectRequest putObjectRequest) {
        oss.putObject(putObjectRequest);
    }

    public void putObject(URL signedUrl, String filePath, Map<String, String> requestHeaders) {
        oss.putObject(signedUrl, filePath, requestHeaders);
    }

    public void putObject(URL signedUrl, String filePath, Map<String, String> requestHeaders, boolean useChunkEncoding) {
        oss.putObject(signedUrl, filePath, requestHeaders, useChunkEncoding);
    }

    public void putObject(URL signedUrl, InputStream requestContent, long contentLength, Map<String, String> requestHeaders) {
        oss.putObject(signedUrl, requestContent, contentLength, requestHeaders);
    }

    public void putObject(URL signedUrl, InputStream requestContent, long contentLength, Map<String, String> requestHeaders, boolean useChunkEncoding) {
        oss.putObject(signedUrl, requestContent, contentLength, requestHeaders, useChunkEncoding);
    }

    public void getObject(String bucketName, String key) {
        oss.getObject(bucketName, key);
    }

    public void getObject(GetObjectRequest getObjectRequest, File file) {
        oss.getObject(getObjectRequest, file);
    }

    public void getObject(GetObjectRequest getObjectRequest) {
        oss.getObject(getObjectRequest);
    }

    public void getObject(URL signedUrl, Map<String, String> requestHeaders) {
        oss.getObject(signedUrl, requestHeaders);
    }
}
