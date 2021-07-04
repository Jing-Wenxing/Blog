package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;

public interface ClassService {
    // 获取分类列表
    JSONObject getALlClassDataofPage(String page);

    // 新增分类
    JSONObject uploadData(JSONObject requestjson);

    // 获取全部数据
    JSONObject getALlClassData();

    // 删除分类
    JSONObject deleteClassData(String classification);

    // 获取数据表数据量
    JSONObject tablesValue();
}
