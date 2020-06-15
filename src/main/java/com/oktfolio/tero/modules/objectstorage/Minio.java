package com.oktfolio.tero.modules.objectstorage;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.ServerSideEncryption;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class Minio {

    @Autowired
    private MinioClient minioClient;

    public void putObject(String bucketName,
                          String objectName,
                          String filename,
                          PutObjectOptions options) {
        try {
            minioClient.putObject(bucketName, objectName, filename, options);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void putObject(String bucketName,
                          String objectName,
                          InputStream inputStream,
                          PutObjectOptions options) {
        try {
            minioClient.putObject(bucketName, objectName, inputStream, options);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName) {
        try {
            minioClient.getObject(bucketName, objectName);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName, ServerSideEncryption sse) {
        try {
            minioClient.getObject(bucketName, objectName, sse);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName, long offset) {
        try {
            minioClient.getObject(bucketName, objectName, offset);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName, Long offset, Long length, ServerSideEncryption sse) {
        try {
            minioClient.getObject(bucketName, objectName, offset, length, sse);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName, String fileName) {
        try {
            minioClient.getObject(bucketName, objectName, fileName);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public void getObject(String bucketName, String objectName, ServerSideEncryption sse, String fileName) {
        try {
            minioClient.getObject(bucketName, objectName, sse, fileName);
        } catch (ErrorResponseException
                | InsufficientDataException
                | InternalException
                | InvalidBucketNameException
                | InvalidKeyException
                | InvalidResponseException
                | IOException
                | NoSuchAlgorithmException
                | XmlParserException e) {
            e.printStackTrace();
        }
    }
}
