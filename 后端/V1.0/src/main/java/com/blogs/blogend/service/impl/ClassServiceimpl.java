package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Classes;
import com.blogs.blogend.entity.Artcle;
import org.springframework.beans.factory.annotation.Autowired;

import com.blogs.blogend.service.ClassService;
import com.blogs.blogend.mapper.ClassMapper;
import com.blogs.blogend.mapper.ArtcleMapper;
import com.blogs.blogend.tool.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassServiceimpl implements ClassService{
    @Autowired
    ArtcleMapper artcleMapper;

    @Autowired
    ClassMapper classMapper;

    @Override
    public JSONObject uploadData(JSONObject requestjson) {
        Classes classes = new Classes();

        classes.setId(classMapper.getMaxID() + 1 + "");
        classes.setTitle(requestjson.getString("title"));
        classes.setDescription(requestjson.getString("description"));
        classes.setTimeline(String.valueOf(new Date().getTime()));

        if (classMapper.uploadData(classes)==1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    @Override
    public JSONObject getALlClassDataofPage(String page) {
        List<Classes> result = classMapper.getALlArtcleDataofPage((Integer.valueOf(page) - 1) * 10);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getALlClassData() {
        List<Classes> result = classMapper.getALlClassData();
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject tablesValue() {
        return CommonUtil.successJson(classMapper.tablesValue());
    }

    @Override
    public JSONObject deleteClassData(String classification)  {
        // 批量修改文章分类
        artcleMapper.updataArtcleClass(classification);

        if (classMapper.deleteClassData(classification) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }
}
