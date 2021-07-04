package com.blogs.blogend.controller;

import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.service.ImageService;
import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.tool.ErrorEnum;
import com.blogs.blogend.tool.ParameterChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    // 后端接口，创建一个新的存储桶
    @RequestMapping(value="createBucket", method = RequestMethod.POST)
    public JSONObject createBucket(@RequestBody JSONObject requestJson){
        String bucketName = requestJson.get("bucketName").toString();
        if(requestJson.getString("bucketName") == null ){
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        return imageService.createBucket(bucketName);
    }

    // 后端接口，获得存储桶列表
    @RequestMapping(value = "getBucketList", method = RequestMethod.GET)
    public JSONObject getBucketList(){
        return imageService.getBucketList();
    }

    // 后端接口，获得单个存储桶内的全部文件
    @RequestMapping(value="getFilelistOfBucket", method = RequestMethod.POST)
    public JSONObject getFilelistOfBucket(@RequestBody JSONObject requestJson){
        String bucketName = requestJson.get("bucketName").toString();
        if(requestJson.getString("bucketName") == null ){
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        return imageService.getFilelistOfBucket(bucketName);
    }
}
