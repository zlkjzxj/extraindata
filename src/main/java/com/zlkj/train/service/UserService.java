package com.zlkj.train.service;

import com.alibaba.fastjson.JSONObject;
import com.zlkj.train.bean.FrmSysUser;
import com.zlkj.train.bean.SysUser;
import com.zlkj.train.exception.GlobalException;
import com.zlkj.train.result.CodeMsg;
import com.zlkj.train.utils.Constant;
import com.zlkj.train.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018\12\4 0004 11:23
 * @Description:
 */
@Service
public class UserService {
    /**
     * 1.先根据用户名去查用户
     * 2.取出插过来用户的密码和盐，
     * 把参数密码经过盐加密之后比对数据库密码和加密后的密码是否相同
     * 3.随机生成token，保存到数据库并添加到cookie中
     *
     * @param sysUser
     * @return
     */
    public boolean login(FrmSysUser sysUser) {
        /*User user = userDao.getUserByName(name);
        //如果查到用户 就用加密的密码和数据库的salt再次加密比对密码是否一样，如果一样，登录成功
        if (user == null) {
            throw new GlobalException(CodeMsg.USERNOTEXIST_ERROR);
        }
        String pass = MD5Util.formPassToDbPass(password, user.getSalt());
        if (!pass.equals(user.getPassword())) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        Cookie cookie = addCookie(token, user);
        response.ddCookie(cookie);*/
        Map<String, String> map = new HashMap<>();
        map.put("yhdh", sysUser.getYhdh());
        map.put("mm", sysUser.getMm());
        map.put("tdsourcetag", "s_pcqq_aiomsg");
        String result = HttpClientUtil.doPost(Constant.HOST.concat(Constant.LOGIN_URL), map, "UTF8");
        JSONObject json = JSONObject.parseObject(result);
        if (json.get("code") != null && json.get("code").equals("201")) {
            throw new GlobalException(CodeMsg.USERNAME_PASSOERD_ERR);
        }
        return true;
    }

}
