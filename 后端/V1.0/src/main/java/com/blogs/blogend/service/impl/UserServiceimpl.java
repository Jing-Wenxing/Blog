package com.blogs.blogend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blogs.blogend.exception.CommonJsonException;
import com.blogs.blogend.tool.*;
import com.blogs.blogend.service.UserService;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.blogs.blogend.tool.ErrorEnum.E_20010;
import static com.blogs.blogend.tool.ErrorEnum.E_3003;


@Service
public class UserServiceimpl implements UserService {
    @Override
    public JSONObject authLogin(JSONObject requestJson, HttpServletRequest request) {
        String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        String code = requestJson.getString("code").toLowerCase();

        HttpSession session = request.getSession();
        if (code.equals(session.getAttribute("captcha").toString().toLowerCase())) {
            if (username.equals("yourusername") && password.equals("yourpassword")) {
                // 账号密码正确
                request.getSession().setAttribute("login", true);
                return CommonUtil.successJson();

            } else {
                return CommonUtil.errorJson(E_20010);
            }
        } else {
            return CommonUtil.errorJson(E_3003);
        }
    }

}
