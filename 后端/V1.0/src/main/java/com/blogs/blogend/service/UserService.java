package com.blogs.blogend.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    //登录
    JSONObject authLogin(JSONObject requestjson, HttpServletRequest request);
}
