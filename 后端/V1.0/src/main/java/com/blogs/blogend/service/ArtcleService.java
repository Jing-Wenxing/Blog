package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;

public interface ArtcleService {
    // 上传文章
    JSONObject uploadData(JSONObject requestjson);

    // 删除文章
    JSONObject deleteArtcleData(String id);

    // 根据ID获取文章
    JSONObject getDataofID(String id);

    // 编辑文章
    JSONObject updateData(JSONObject requestjson);

    // 获取数据表数据量
    JSONObject tablesValue();

    // 获取限定范围内的数据
    JSONObject getALlArtcleDataofPage(String page);

    // 获取限定范围内的数据
    JSONObject getALlFrontArtcleDataofPage(String page);

    // 修改Isshow
    JSONObject updataArtcleIsShow(String id);
}
