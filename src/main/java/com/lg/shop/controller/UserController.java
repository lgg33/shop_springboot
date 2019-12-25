package com.lg.shop.controller;

import com.lg.shop.common.response.CommonCode;
import com.lg.shop.common.response.ResponseResult;
import com.lg.shop.entity.User;
import com.lg.shop.service.serviceimpl.UserServiceImpl;
import com.lg.shop.utils.CodeUtil;
import com.lg.shop.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author L
 * @version 1.0
 * @ClassName: UserController
 * @date: 2019/12/22 14:01
 * @since JDK 1.8
 */
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        String code = UUID.randomUUID().toString().replace("-", "");
        user.setUid(UUID.randomUUID().toString().replace("-", ""));
        user.setCode(code);
        userService.addUser(user);
        try {
            MailUtils.sendMail(user.getEmail(), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/userActive")
    public String active(String code) {
        User user = userService.findByCode(code);
        if (user == null) return "激活失败！";
        user.setState(1);
        userService.addUser(user);
        return "激活成功!";
    }

    @GetMapping("/checkUser")
    public ResponseResult checkUser(String username) {
        User user = userService.findByUsername(username);
        if (user == null) return new ResponseResult(CommonCode.SUCCESS);
        return new ResponseResult(CommonCode.FAIL);
    }

    @GetMapping("/login")
    public ResponseResult login(String username, String password, String code, HttpServletRequest req) {
        String checkCode = (String) req.getSession().getAttribute("code");
        if (code.equalsIgnoreCase(checkCode)) {
            User user = userService.login(username, password);
            if (user == null) return new ResponseResult(CommonCode.LOGIN_FAIL);
            if (user.getState() == 0) return new ResponseResult(CommonCode.UNACTIVE);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.ERROR_CHECKCODE);
    }

    @GetMapping("/getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
