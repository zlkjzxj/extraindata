package com.zlkj.train.controller;


import com.zlkj.train.bean.FrmSysUser;
import com.zlkj.train.bean.SysUser;
import com.zlkj.train.result.Result;
import com.zlkj.train.service.UserService;
import com.zlkj.train.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String toLogin(Model model) {
        /*通过USEDB判断是否需要连接数据库*/
        model.addAttribute("useDb", Constant.USE_DB);
        return "index";
    }


    @RequestMapping("/login")
    @ResponseBody
    public Result<FrmSysUser> login(HttpServletRequest request, FrmSysUser sysUser) {
        userService.login(sysUser);
//        request.getSession().setAttribute("userSession", sysUser);
        return Result.success(sysUser);
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public Result<Boolean> loginOut(FrmSysUser sysUser) {
        userService.login(sysUser);
        return Result.success(true);
    }


    @RequestMapping("/vue")
    public String vue() {
        return "vue";
    }
}
