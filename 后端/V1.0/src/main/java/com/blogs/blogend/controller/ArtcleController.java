package com.blogs.blogend.controller;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.service.ArtcleService;
import com.blogs.blogend.tool.CommonUtil;
import com.blogs.blogend.tool.ErrorEnum;
import com.blogs.blogend.tool.MinioUtil;
import com.blogs.blogend.tool.ParameterChecker;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("artcle")
public class ArtcleController {
    @Autowired
    private ArtcleService artcleService;

    // 上传文章
    @RequestMapping(value = "uploadData", method = RequestMethod.POST)
    public JSONObject uploadData(@RequestBody JSONObject requestJson) {
        ParameterChecker.artcleuploadData(requestJson);
        return artcleService.uploadData(requestJson);
    }

    // 删除文章
    @RequestMapping(value = "deleteArtcleData", method= RequestMethod.POST)
    public JSONObject deleteArtcleData(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return artcleService.deleteArtcleData(id);
    }

    // 根据ID获取文章
    @RequestMapping(value = "getDataofID", method= RequestMethod.POST)
    public JSONObject getDataofID(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return artcleService.getDataofID(id);
    }

    // 编辑文章
    @RequestMapping(value = "updateData", method = RequestMethod.POST)
    public JSONObject updateData(@RequestBody JSONObject requestJson) {
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        ParameterChecker.artcleuploadData(requestJson);
        return artcleService.updateData(requestJson);
    }

    // 获取数据表数据量
    @RequestMapping(value = "tablesValue", method = RequestMethod.GET)
    public JSONObject tablesValue() {
        return artcleService.tablesValue();
    }

    // 获取限定范围内的数据
    @RequestMapping(value = "getALlArtcleDataofPage", method = RequestMethod.POST)
    public JSONObject getALlArtcleDataofPage(@RequestBody JSONObject requestJson) {
        String page = requestJson.get("page").toString();
        ParameterChecker.getALlArtcleDataofPage(requestJson);
        return artcleService.getALlArtcleDataofPage(page);
    }

    // 前台获取限定范围内的数据
    @RequestMapping(value = "getALlFrontArtcleDataofPage", method = RequestMethod.POST)
    public JSONObject getALlFrontArtcleDataofPage(@RequestBody JSONObject requestJson) {
        String page = requestJson.get("page").toString();
        ParameterChecker.getALlArtcleDataofPage(requestJson);
        return artcleService.getALlFrontArtcleDataofPage(page);
    }

    // 修改Isshow
    @RequestMapping(value = "updataArtcleIsShow", method= RequestMethod.POST)
    public JSONObject updataArtcleIsShow(@RequestBody JSONObject requestJson){
        String id = requestJson.get("id").toString();
        ParameterChecker.idCheck(requestJson);
        return artcleService.updataArtcleIsShow(id);
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
            uploadUrl = minioUtil.uploadURL("artcles", objectName, 60 * 60 * 24);
        } catch (InvalidPortException | InvalidEndpointException e) {
            throw new CommonJsonException(ErrorEnum.E_00000);
        }
        return CommonUtil.successJson(uploadUrl);
    }
}
