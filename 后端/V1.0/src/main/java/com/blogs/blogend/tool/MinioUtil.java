package com.blogs.blogend.tool;

import com.blogs.blogend.exception.CommonJsonException;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import io.minio.policy.PolicyType;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MinioUtil {
    private final String URL = "";
    private final String DOMAIN = "";
    private final int PORT = ;
    private final String accessKey = "";
    private final String secretKey = "";
    private final boolean secure = false;

    MinioClient minioClient = new MinioClient(URL, PORT, accessKey, secretKey, secure);

    public MinioUtil() throws InvalidPortException, InvalidEndpointException {
    }

    // ==== 存储桶操作 ==== //

    // 存储桶存在
    public Boolean bucketExists(String bucketName) {
        Boolean exist;
        try {
            exist = minioClient.bucketExists(bucketName);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return exist;
    }

    // 创建存储桶
    public Boolean makeBucket(String bucketName) {
        Boolean exist;
        try {
            exist = this.bucketExists(bucketName);
            if (exist) {
                return false;
            }
            else {
                minioClient.makeBucket(bucketName);
                return true;
            }
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    // 列出所有存储桶
    public List<Bucket> listBuckets() {
        try {
            List<Bucket> bucketList = minioClient.listBuckets();
            return bucketList;
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    // 列出存储桶内的所有文件
    public Iterable<Result<Item>> listObjects(String bucketName) {
//        Iterable<Result<Item>> myObjects = minioClient.listObjects("mybucket",null,true,true);
//        return myObjects;
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects("mybucket",null,true,true);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
            }
            return myObjects;
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }


    // ======== //

    // 获取临时下载链接
    public String downloadURL(String bucketName, String objectName, Integer expires) {
        String url = "";
        try {
            url = minioClient.presignedGetObject(bucketName, objectName, expires);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return url;
    }

    // 获取临时上传链接
    public String uploadURL(String bucketName, String objectName, Integer expires) {
        String url = "";
        try {
            url = minioClient.presignedPutObject(bucketName, objectName, expires);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return url;
    }

    // 删除文件
    public void removeObject(String bucketName, String objectName) {
        try {
            minioClient.removeObject(bucketName, objectName);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    // 获取桶策略
    public PolicyType getBucketPolicy(String bucketName, String objectPrefix) {
        try {
            return minioClient.getBucketPolicy(bucketName, objectPrefix);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    // 设置桶策略
    public void setBucketPolicy(String bucketName, String objectPrefix, PolicyType policy) {
        try {
            minioClient.setBucketPolicy(bucketName, objectPrefix, policy);
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

}
