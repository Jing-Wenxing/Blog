package com.blogs.blogend.controller;

import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.service.SwiperService;
import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.tool.CommonUtil;
import com.blogs.blogend.tool.ErrorEnum;
import com.blogs.blogend.tool.MinioUtil;
import com.blogs.blogend.tool.ParameterChecker;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("swiper")
public class SwiperController {
    @Autowired
    private SwiperService swiperService;

    // 获取数据表数据量
    @RequestMapping(value = "tablesValue", method = RequestMethod.GET)
    public JSONObject tablesValue() {
        return swiperService.tablesValue();
    }

    // 获取限定范围内的数据
    @RequestMapping(value = "getALlClassDataofPage", method = RequestMethod.POST)
    
    public JSONObject getALlSwiperDataofPage(@RequestBody JSONObject requestJson) {
        String page = requestJson.get("page").toString();
        ParameterChecker.getALlSwiperDataofPage(requestJson);
        return swiperService.getALlSwiperDataofPage(page);
    }

    // 后台接口，获取所有的swiper数据
    @RequestMapping(value = "getALlSwiperData", method = RequestMethod.GET)
    public JSONObject getALlSwiperData(){
        return swiperService.getALlSwiperData();
    }

    // 前台接口，获取所有可展示的swiper数据
    @RequestMapping(value = "getALlShowSwiperData", method = RequestMethod.GET)
    public JSONObject getALlShowSwiperData(){
        return swiperService.getALlShowSwiperData();
    }

    // 后端接口，删除一条轮播图
    @RequestMapping(value = "deleteSwiperData", method= RequestMethod.POST)
    public JSONObject deleteSwiperData(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return swiperService.deleteSwiperData(id);
    }

    // 后端接口，添加一条轮播图
    @RequestMapping(value = "addSwiperData", method= RequestMethod.POST)
    public JSONObject addSwiperData(@RequestBody JSONObject requestJson){
        ParameterChecker.addSwiperData(requestJson);
        return swiperService.addSwiperData(requestJson);
    }

    // 后端接口，展示顺序交换
    @RequestMapping(value = "changeSwiperSwquence", method= RequestMethod.POST)
    public JSONObject changeSwiperSwquence(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        String status = requestJson.get("status").toString();
        ParameterChecker.changeSwiperSwquence(requestJson);
        return swiperService.changeSwiperSwquence(id, status);
    }

    // 后端接口，调整展示状态
    @RequestMapping(value = "updataSwiperIsShow", method= RequestMethod.POST)
    public JSONObject updataSwiperIsShow(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return swiperService.updataSwiperIsShow(id);
    }

    // 后端接口，修改参数值
    @RequestMapping(value = "updataSwiperAttributes", method= RequestMethod.POST)
    public JSONObject updataSwiperAttributes(@RequestBody JSONObject requestJson){
        ParameterChecker.updataSwiperAttributes(requestJson);
        return swiperService.updataSwiperAttributes(requestJson);
    }

    @RequestMapping(value = "getUploadTempUrl", method = RequestMethod.GET)
    public JSONObject getUploadTempUrl(String objectName) {
        if (objectName == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        String uploadUrl = "";
        try {
            MinioUtil minioUtil = new MinioUtil();
            uploadUrl = minioUtil.uploadURL("swipers", objectName, 60 * 60 * 24);
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return CommonUtil.successJson(uploadUrl);
    }
}