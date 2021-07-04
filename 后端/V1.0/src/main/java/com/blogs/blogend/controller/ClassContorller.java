package com.blogs.blogend.controller;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.service.ClassService;
import com.blogs.blogend.tool.CommonUtil;
import com.blogs.blogend.tool.ErrorEnum;
import com.blogs.blogend.tool.MinioUtil;
import com.blogs.blogend.tool.ParameterChecker;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classes")
public class ClassContorller {
    @Autowired
    private ClassService classService;

    // 上传分类
    @RequestMapping(value = "uploadData", method = RequestMethod.POST)
    public JSONObject uploadData(@RequestBody JSONObject requestJson) {
        ParameterChecker.classuploadData(requestJson);
        return classService.uploadData(requestJson);
    }

    // 获取数据表数据量
    @RequestMapping(value = "tablesValue", method = RequestMethod.GET)
    public JSONObject tablesValue() {
        return classService.tablesValue();
    }

    // 获取限定范围内的数据
    @RequestMapping(value = "getALlClassDataofPage", method = RequestMethod.POST)
    public JSONObject getALlClassDataofPage(@RequestBody JSONObject requestJson) {
        String page = requestJson.get("page").toString();
        ParameterChecker.getALlClassDataofPage(requestJson);
        return classService.getALlClassDataofPage(page);
    }

    // 获取全部数据
    @RequestMapping(value = "getALlClassData", method = RequestMethod.GET)
    public JSONObject getALlClassData() {
        return classService.getALlClassData();
    }

    // 删除文章
    @RequestMapping(value = "deleteClassData", method= RequestMethod.POST)
    public JSONObject deleteClassData(@RequestBody JSONObject requestJson){
        String classification = requestJson.get("classification").toString();
        if (    requestJson.getString("classification") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
        return classService.deleteClassData(classification);
    }
}
