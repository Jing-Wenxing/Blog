package com.blogs.blogend.controller;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.service.TimelineService;
import com.blogs.blogend.tool.CommonUtil;
import com.blogs.blogend.tool.ErrorEnum;
import com.blogs.blogend.tool.MinioUtil;
import com.blogs.blogend.tool.ParameterChecker;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("timeline")
public class TimelineController {
    @Autowired
    private TimelineService timelineService;

    // 后端接口，上传数据
    @RequestMapping(value = "uploadData", method = RequestMethod.POST)
    public JSONObject uploadData(@RequestBody JSONObject requestJson) {
        ParameterChecker.timelineuploadData(requestJson);
        return timelineService.uploadData(requestJson);
    }

    // 后端接口，获取数据表数据量
    @RequestMapping(value = "tablesValue", method = RequestMethod.GET)
    public JSONObject tablesValue() {
        return timelineService.tablesValue();
    }

    // 前端接口，获取所有的timeline数据
    @RequestMapping(value = "getALlTimelineData", method = RequestMethod.GET)
    public JSONObject getALlTimelineData(){
        return timelineService.getALlTimelineData();
    }

    // 后端接口，获取限定范围的timeline数据
    @RequestMapping(value = "getALlTimelineDataofPage", method = RequestMethod.POST)
    public JSONObject getALlTimelineDataofPage(@RequestBody JSONObject requestJson) {
        String page = requestJson.get("page").toString();
        ParameterChecker.getALlTimelineDataofPage(requestJson);
        return timelineService.getALlTimelineDataofPage(page);
    }

    // 后端接口，删除一条轮播图
    @RequestMapping(value = "deleteTimelineData", method= RequestMethod.POST)
    public JSONObject deleteTimelineData(@RequestBody JSONObject requestJson) throws InvalidPortException, InvalidEndpointException {
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return timelineService.deleteTimelineData(id);
    }

    // 后端接口，获得上传链接
    @RequestMapping(value = "getUploadTempUrl", method = RequestMethod.GET)
    public JSONObject getUploadTempUrl(String objectName) {
        if (objectName == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        String uploadUrl = "";
        try {
            MinioUtil minioUtil = new MinioUtil();
            uploadUrl = minioUtil.uploadURL("timelines", objectName, 60 * 60 * 24);
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return CommonUtil.successJson(uploadUrl);
    }

    // 后端接口，获取图片
    @RequestMapping(value = "getImage", method = RequestMethod.POST)
    public JSONObject getImage(@RequestBody JSONObject requestJson){
        String img = requestJson.get("img").toString();
        ParameterChecker.getTimelineImage(requestJson);
        try {
            JSONObject result = new JSONObject();
            MinioUtil minioUtil = new MinioUtil();
            result.put("url",minioUtil.downloadURL("timelines", img, 6 * 60 * 60 ));
            return CommonUtil.successJson(result);
        }catch (InvalidPortException | InvalidEndpointException e){
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
    }
}
