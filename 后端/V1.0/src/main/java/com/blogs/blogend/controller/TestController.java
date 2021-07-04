package com.blogs.blogend.controller;

import com.blogs.blogend.exception.CommonJsonException;
import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.tool.*;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {
    // 后台接口，获取下载
    @RequestMapping(value = "TestGetMinIODownloadTempUrl", method = RequestMethod.GET)
    public JSONObject TestGetMinIODownloadTempUrl() throws InvalidPortException, InvalidEndpointException{
        try {
            JSONObject result = new JSONObject();
            MinioUtil minioUtil = new MinioUtil();
            //return CommonUtil.successJson(result);
            result.put("url",minioUtil.downloadURL("timelines", "3333.jpg", 6 * 60 * 60 ));
            return CommonUtil.successJson(result);
        }catch (InvalidPortException | InvalidEndpointException e){
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    // 后台接口，获取上传链接
    @RequestMapping(value = "TestGetMinIOUploadTempUrl", method = RequestMethod.GET)
    public JSONObject TestGetMinIOUploadTempUrl(String objectName){
        if(objectName == null){
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        String uploadUrl = "";
        try {
            MinioUtil minioUtil = new MinioUtil();
            uploadUrl = minioUtil.uploadURL("timelines",objectName, 60*60*24);
        }catch (InvalidPortException | InvalidEndpointException e){
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return CommonUtil.successJson(uploadUrl);
    }

    @RequestMapping(value = "getBucketPolicy", method = RequestMethod.GET)
    public JSONObject getBucketPolicy(){
        try {
            MinioUtil minioUtil = new MinioUtil();
            PolicyType result = minioUtil.getBucketPolicy("artcles","downloads");
            return CommonUtil.successJson(result);
        }catch (InvalidPortException | InvalidEndpointException e){
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }

    @RequestMapping(value = "setBucketPolicy", method = RequestMethod.GET)
    public JSONObject setBucketPolicy(){
        try {
            MinioUtil minioUtil = new MinioUtil();
            minioUtil.setBucketPolicy("artcles","downloads", PolicyType.READ_WRITE);
        }catch (InvalidPortException | InvalidEndpointException e){
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return CommonUtil.successJson();
    }
}
