package com.sxx.sivir.web.controller;

import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.UserManager;
import com.sxx.sivir.core.service.LoginRegisterService;
import com.sxx.sivir.web.security.token.TokenHelper;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.sxx.sivir.core.common.constant.SivirURL.*;
import static com.sxx.sivir.core.common.enums.GlobalErrorCode.*;


@Slf4j
@Controller
public class LoginRegisterController {

    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private LoginRegisterService loginRegisterService;
    @Autowired
    private UserManager userManager;


    @ApiOperation(value = "登录", notes = "登录")
    @ResponseBody
    @RequestMapping(value = SIVIR_LOGIN, method = RequestMethod.POST)
    public APIResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        user = loginRegisterService.checkLogin(user);
        if (null != user) {
            request.getSession().setAttribute("user", user);

            log.info("login -> " + user.getUserName() + "用户已登录 ");
            return APIResult.ok(user);
        }
        return APIResult.error(LOGIN_FAILURE.getCode(), LOGIN_FAILURE.getMessage());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = SIVIR_LOGOUT, method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        if (null == session.getAttribute("user")) {
            log.info("logout -> 用户已注销 ");
            return "redirect:to/login";
        }
        return "/to/index";
    }


    @ApiOperation(value = "注册", notes = "注册")
    @ResponseBody
    @RequestMapping(value = SIVIR_REGISTER, method = RequestMethod.POST)
    public APIResult register(@RequestBody User user) {
        if (null == user.getPhone() || user.getPhone().isEmpty()) {
            log.info("register -> 用户注册失败，未检测到手机号 ");
            return APIResult.error(REGISTER_FAILURE.getCode(), REGISTER_FAILURE.getMessage());
        }

        if (loginRegisterService.checkRegister(user.getPhone())) {
            try {
                return APIResult.ok(loginRegisterService.registerUser(user));
            } catch (Exception e) {
                return APIResult.error(REGISTER_FAILURE.getCode(), REGISTER_FAILURE.getMessage());
            }

        } else {
            log.info("register -> 用户注册失败，手机号" + user.getPhone() + "已存在！");
            return APIResult.error(REGISTER_FAILURE_PHONE_REPEAT.getCode(), REGISTER_FAILURE_PHONE_REPEAT.getMessage());
        }
    }


    @ApiOperation(value = "获取登录人", notes = "获取登录人")
    @ResponseBody
    @RequestMapping(value = PUBLIC_FIND_USER, method = RequestMethod.POST)
    public APIResult<User> getCurrentLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (null != user) {
            log.info("当前登录用户 -> " + user.toString());
            return APIResult.ok(user);
        }
        return APIResult.error(NO_LOGIN_USER.getCode(), NO_LOGIN_USER.getMessage());
    }

//    @RequestMapping(value = TO_INDEX, method = RequestMethod.GET)
//    public ModelAndView toIndex() {
//        return new ModelAndView(new RedirectView("http://127.0.0.1:8000"));
//    }


}
