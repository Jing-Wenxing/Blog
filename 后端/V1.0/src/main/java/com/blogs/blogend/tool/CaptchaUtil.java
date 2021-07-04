package com.blogs.blogend.tool;

import com.blogs.blogend.exception.CommonJsonException;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CaptchaUtil {

    public String createCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException,NullPointerException {
        HttpSession session = request.getSession();

        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Captcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String verCode = specCaptcha.text().toLowerCase();

        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
        specCaptcha.out(response.getOutputStream());

        return verCode;
    }

    public void check(String value, Object codeInRedis) {
        if (StringUtils.isBlank(value)) {
            throw new CommonJsonException(ErrorEnum.E_3001);
        }
        if (codeInRedis == null) {
            throw new CommonJsonException(ErrorEnum.E_3002);
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new CommonJsonException(ErrorEnum.E_3003);
        }
    }
}
