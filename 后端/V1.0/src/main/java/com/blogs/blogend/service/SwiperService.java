package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;

public interface SwiperService {
    // 获取全部Swiper数据
    JSONObject getALlSwiperData();

    // 获取所有可展示的swiper数据
    JSONObject getALlShowSwiperData();

    // 删除一条轮播图
    JSONObject deleteSwiperData(String id);

    // 添加一条轮播图
    JSONObject addSwiperData(JSONObject requestjson);

    // 展示顺序交换
    JSONObject changeSwiperSwquence(String id, String status);

    // 调整展示状态
    JSONObject updataSwiperIsShow(String id);

    // 修改参数值
    JSONObject updataSwiperAttributes(JSONObject requestjson);

    // 获取数据表数据量
    JSONObject tablesValue();

    // 获取分类列表
    JSONObject getALlSwiperDataofPage(String page);
}
