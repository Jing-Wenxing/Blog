package com.blogs.blogend.controller;

import com.blogs.blogend.service.UserService;
import com.blogs.blogend.tool.*;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserContorller {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject requestJson,
                            HttpServletRequest request) {
        ParameterChecker.loginCheck(requestJson);
        return userService.authLogin(requestJson, request);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public JSONObject logout(HttpSession session, SessionStatus sessionStatus) throws IOException {
        //session.invalidate();
        //sessionStatus.setComplete();
        session.setAttribute("login", false);
        return CommonUtil.successJson("退出成功");
    }

    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil captcha = new CaptchaUtil();
        String ver = captcha.createCaptcha(request, response);
        String id = Constants.CODE_PREFIX + request.getSession().getId();
    }

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public JSONObject profile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject result = new JSONObject();
        result.put("login", session.getAttribute("login"));
        return CommonUtil.successJson(result);
    }
}
