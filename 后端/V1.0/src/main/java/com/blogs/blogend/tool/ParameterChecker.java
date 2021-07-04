package com.blogs.blogend.tool;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.exception.CommonJsonException;

public class ParameterChecker {
    // ==== Swiper ==== //
    public static void idCheck(JSONObject requestJson) {
        if (requestJson.getString("id") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void addSwiperData(JSONObject requestJson) {
        if (requestJson.getString("title") == null ||
                requestJson.getString("text") == null||
                requestJson.getString("img") == null)   {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void changeSwiperSwquence(JSONObject requestJson) {
        if (requestJson.getString("id") == null ||
                requestJson.getString("status") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void updataSwiperAttributes(JSONObject requestJson) {
        if (requestJson.getString("id") == null ||
                requestJson.getString("title") == null ||
                requestJson.getString("text") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void getALlSwiperDataofPage(JSONObject requestJson) {
        if (requestJson.getString("page") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }
    // ======== //

    // ==== Timeline ==== //
    public static void timelineuploadData(JSONObject requestJson) {
        if (requestJson.getString("classification") == null ||
                requestJson.getString("title") == null ||
                requestJson.getString("text") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void getTimelineImage(JSONObject requestJson) {
        if (requestJson.getString("img") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void getALlTimelineDataofPage(JSONObject requestJson) {
        if (requestJson.getString("page") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }
    // ======== //

    // ==== Artcle ==== //
    public static void artcleuploadData(JSONObject requestJson) {
        if (requestJson.getString("classification") == null ||
                requestJson.getString("title") == null ||
                requestJson.getString("text") == null ||
                requestJson.getString("isshow") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void getALlArtcleDataofPage(JSONObject requestJson) {
        if (requestJson.getString("page") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }
    // ======== //

    // ==== Class ==== //
    public static void classuploadData(JSONObject requestJson) {
        if (    requestJson.getString("title") == null ||
                requestJson.getString("description") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }

    public static void getALlClassDataofPage(JSONObject requestJson) {
        if (requestJson.getString("page") == null) {
            throw new CommonJsonException(ErrorEnum.E_90003);
        }
    }
    // ======== //

    // ==== Class ==== //
    public static void loginCheck(JSONObject requestJson){
        if(     requestJson.getString("username") == null ||
                requestJson.getString("password")== null ||
                requestJson.getString("code")== null
        ){
            throw new CommonJsonException(ErrorEnum.E_90003);
        }

    }
    // ======== //
}
