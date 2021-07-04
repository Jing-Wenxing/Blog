package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

public interface TimelineService {
    // 添加一条轮播图
    JSONObject uploadData(JSONObject requestjson);

    // 删除一条轮播图
    JSONObject deleteTimelineData(String id) throws InvalidPortException, InvalidEndpointException;

    // 获取数据表数据量
    JSONObject tablesValue();

    // 获取限定范围的timeline数据
    JSONObject getALlTimelineDataofPage(String page);

    // 获取所有的timeline数据
    JSONObject getALlTimelineData();
}