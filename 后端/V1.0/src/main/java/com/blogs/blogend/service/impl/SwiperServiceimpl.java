package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Classes;
import com.blogs.blogend.entity.Swiper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.blogend.service.SwiperService;
import com.blogs.blogend.mapper.SwiperMapper;
import com.blogs.blogend.tool.*;

import java.util.List;

@Service
public class SwiperServiceimpl implements SwiperService{
    @Autowired
    SwiperMapper swiperMapper;

    // 获取全部Swiper数据
    @Override
    public JSONObject getALlSwiperData() {
        List<Swiper> result = swiperMapper.getALlSwiperData();
        return CommonUtil.successJson(result);
    }

    // 获取所有可展示的swiper数据
    @Override
    public JSONObject getALlShowSwiperData() {
        List<Swiper> result = swiperMapper.getALlShowSwiperData();

        return CommonUtil.successJson(result);
    }

    // 删除一条轮播图
    @Override
    public JSONObject deleteSwiperData(String id) {
        swiperMapper.updataSwiperSwquenceNumerous(swiperMapper.getSwiperSwquenceOfID(id));
        if(swiperMapper.deleteSwiperData(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    // 添加一条轮播图
    @Override
    public JSONObject addSwiperData(JSONObject requestjson) {
        Swiper swiper = new Swiper();

        swiper.setId(swiperMapper.getMaxID()+1+"");
        swiper.setTitle(requestjson.getString("title"));
        swiper.setText(requestjson.getString("text"));
        swiper.setImg(requestjson.getString("img"));
        swiper.setSwquence(swiperMapper.getMaxSwquence()+1+"");
        swiper.setIsshow(true);

        if(swiperMapper.insertSwiperUnlessImg(swiper) == 1){
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    // 展示顺序交换
    @Override
    public JSONObject changeSwiperSwquence(String id, String status) {
        if(status.equals("up")) {
            swiperMapper.updataSwiperSwquence(swiperMapper.getSwiperIDOfSwquence(Integer.valueOf(swiperMapper.getSwiperSwquenceOfID(id))-1+""), swiperMapper.getSwiperSwquenceOfID(id));
            swiperMapper.updataSwiperSwquence(id, Integer.valueOf(swiperMapper.getSwiperSwquenceOfID(id))-1+"");
            return CommonUtil.successJson();
        }
        else if(status.equals("down")) {
            swiperMapper.updataSwiperSwquence(swiperMapper.getSwiperIDOfSwquence(Integer.valueOf(swiperMapper.getSwiperSwquenceOfID(id))+1+""), swiperMapper.getSwiperSwquenceOfID(id));
            swiperMapper.updataSwiperSwquence(id, Integer.valueOf(swiperMapper.getSwiperSwquenceOfID(id))+  1+"");
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_90004);
    }

    // 调整展示状态
    @Override
    public JSONObject updataSwiperIsShow(String id) {
        if(swiperMapper.updataSwiperIsshow(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    // 修改参数值
    @Override
    public JSONObject updataSwiperAttributes(JSONObject requestjson) {
        Swiper swiper = new Swiper();
        swiper.setId(requestjson.getString("id"));
        swiper.setTitle(requestjson.getString("title"));
        swiper.setText(requestjson.getString("text"));

        if(swiperMapper.updataSwiperAttributes(swiper) == 1){
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    @Override
    public JSONObject getALlSwiperDataofPage(String page) {
        List<Swiper> result = swiperMapper.getALlSwiperDataofPage((Integer.valueOf(page) - 1) * 10);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject tablesValue() {
        return CommonUtil.successJson(swiperMapper.tablesValue());
    }
}
