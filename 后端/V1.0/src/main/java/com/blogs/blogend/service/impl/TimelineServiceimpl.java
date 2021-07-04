package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.entity.Timeline;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.blogend.service.TimelineService;
import com.blogs.blogend.mapper.TimelineMapper;
import com.blogs.blogend.tool.*;

import java.util.Date;
import java.util.List;

@Service
public class TimelineServiceimpl implements TimelineService {
    @Autowired
    TimelineMapper timelineMapper;

    @Override
    public JSONObject uploadData(JSONObject requestjson) {
        Timeline timeline = new Timeline();

        timeline.setId(timelineMapper.getMaxID() + 1 + "");
        timeline.setTimeline(String.valueOf(new Date().getTime()));
        timeline.setClassification(requestjson.getString("classification"));
        timeline.setTitle(requestjson.getString("title"));
        timeline.setText(requestjson.getString("text"));
        timeline.setImg(requestjson.getString("img"));
        timeline.setIcon(requestjson.getString("icon"));
        timeline.setColor(requestjson.getString("color"));

        if (timelineMapper.uploadData(timeline) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }

    @Override
    public JSONObject getALlTimelineData() {
        List<Timeline> result = timelineMapper.getALlTimelineData();
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getALlTimelineDataofPage(String page) {
        List<Timeline> result = timelineMapper.getALlTimelineDataofPage((Integer.valueOf(page) - 1) * 10);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject tablesValue() {
        return CommonUtil.successJson(timelineMapper.tablesValue());
    }

    @Override
    public JSONObject deleteTimelineData(String id) throws InvalidPortException, InvalidEndpointException {
        if (!timelineMapper.selectOFID(id).getImg().equals("")) {
            MinioUtil minioUtil = new MinioUtil();
            minioUtil.removeObject("timelines", timelineMapper.selectOFID(id).getImg());
        }

        if (timelineMapper.deleteTimelineData(id) == 1) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_00000);
    }
}
