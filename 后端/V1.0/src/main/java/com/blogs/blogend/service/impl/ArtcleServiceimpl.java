package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Artcle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.blogend.service.ArtcleService;
import com.blogs.blogend.mapper.ArtcleMapper;
import com.blogs.blogend.tool.*;

import java.util.Date;
import java.util.List;

@Service
public class ArtcleServiceimpl implements ArtcleService{
    @Autowired
    ArtcleMapper artcleMapper;

    @Override
    public JSONObject uploadData(JSONObject requestjson) {
        Artcle artcle = new Artcle();

        artcle.setId(artcleMapper.getMaxID() + 1 + "");
        artcle.setTitle(requestjson.getString("title"));
        artcle.setTimeline(String.valueOf(new Date().getTime()));
        artcle.setSummary(requestjson.getString("text").length()>100?requestjson.getString("text").substring(0, 100):requestjson.getString("text"));
        artcle.setText(requestjson.getString("text"));
        artcle.setClassification(requestjson.getString("classification"));
        artcle.setIsshow(requestjson.getBoolean("isshow"));

        if (artcleMapper.uploadData(artcle)==1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    @Override
    public JSONObject updateData(JSONObject requestjson) {
        Artcle artcle = new Artcle();

        artcle.setId(requestjson.getString("id"));
        artcle.setTitle(requestjson.getString("title"));
        artcle.setSummary(requestjson.getString("text").length()>100?requestjson.getString("text").substring(0, 100):requestjson.getString("text"));
        artcle.setText(requestjson.getString("text"));
        artcle.setClassification(requestjson.getString("classification"));
        artcle.setIsshow(requestjson.getBoolean("isshow"));

        if (artcleMapper.updateData(artcle)==1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    @Override
    public JSONObject getDataofID(String id) {
        Artcle result = artcleMapper.getDataofID(id);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getALlArtcleDataofPage(String page) {
        List<Artcle> result = artcleMapper.getALlArtcleDataofPage((Integer.valueOf(page) - 1) * 10);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getALlFrontArtcleDataofPage(String page) {
        List<Artcle> result = artcleMapper.getALlFrontArtcleDataofPage((Integer.valueOf(page) - 1) * 10);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject tablesValue() {
        return CommonUtil.successJson(artcleMapper.tablesValue());
    }

    @Override
    public JSONObject deleteArtcleData(String id)  {
        if (artcleMapper.deleteArtcleData(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    // 调整展示状态
    @Override
    public JSONObject updataArtcleIsShow(String id) {
        if(artcleMapper.updataArtcleIsShow(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }
}
