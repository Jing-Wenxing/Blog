package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Image;
import com.blogs.blogend.entity.Swiper;
import com.blogs.blogend.exception.CommonJsonException;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.blogend.service.ImageService;
// import com.blogs.blogend.mapper.ImageMapper;
import com.blogs.blogend.tool.*;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

import java.util.List;

@Service
public class ImageServiceimpl implements ImageService {
    @Override
    public JSONObject createBucket(String bucketName) {
        Boolean exist;
        try {
            MinioUtil minioUtil = new MinioUtil();
            exist = minioUtil.makeBucket(bucketName);
            if (exist)
                return CommonUtil.successJson("创建成功");
            else
                return CommonUtil.successJson("存储桶已经存在");
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    @Override
    public JSONObject getBucketList() {
        try {
            MinioUtil minioUtil = new MinioUtil();
            List<Bucket> result = minioUtil.listBuckets();
            return CommonUtil.successJson(result);
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    @Override
    public JSONObject getFilelistOfBucket(String bucketName) {
        try {
            MinioUtil minioUtil = new MinioUtil();
            minioUtil.listObjects(bucketName);
            return CommonUtil.successJson();
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }
}
