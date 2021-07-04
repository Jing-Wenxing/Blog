package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;

public interface ImageService {
    // 新建存储桶
    JSONObject createBucket(String bucketName);

    // 获得存储桶列表
    JSONObject getBucketList();

    // 获得单个存储桶内的全部文件
    JSONObject getFilelistOfBucket(String bucketName);
}
