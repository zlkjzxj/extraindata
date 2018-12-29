package com.zlkj.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.zlkj.train.bean.FrmSysUser;
import com.zlkj.train.bean.SysUser;
import com.zlkj.train.result.Result;
import com.zlkj.train.utils.Constant;
import com.zlkj.train.service.ExportToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zxj
 * @Date: 2018\12\4 0004 13:27
 * @Description:
 */

@Controller
public class IndexController {
    @Autowired
    private ExportToolService exportToolService;

    @RequestMapping("/index")
    public String index(Model model) {
        /*SysUser user = (SysUser) request.getSession().getAttribute("userSession");
        if (user != null) {
            model.addAttribute("useDb", Constant.USE_DB);
            model.addAttribute("user", user);
        }*/
        model.addAttribute("useDb", Constant.USE_DB);
        return "index";
    }


    /**
     * 初始化设备
     *
     * @param request
     * @param disk
     * @param trainInfo
     * @return
     */
    @RequestMapping("/init")
    public @ResponseBody
    Result init(HttpServletRequest request, String disk, String trainInfo, String exportUser) {
        //给设备写入初始化文件
        String path = disk.concat("\\");//拼接路径
        FrmSysUser user = JSONObject.parseObject(exportUser, FrmSysUser.class);
        return exportToolService.InitFile(request, path, trainInfo, user);
    }
}
