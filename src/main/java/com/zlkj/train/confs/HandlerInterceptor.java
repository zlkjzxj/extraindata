package com.zlkj.train.confs;

import com.zlkj.train.bean.SysUser;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zxj
 * @Date: 2018\12\17 0017 17:16
 * @Description:
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling");
       /* SysUser user = (SysUser) request.getSession().getAttribute("userSession");
        if (user != null) {
            return true;
        }
        // 跳转登录
        String url = "/";
        response.sendRedirect(url);*/
        return true;
    }

    @Override
    //postHandle方法是在当前请求进行处理之后，也就是Controller方法调用结束之后执行，但是它会在DispatcherServlet进行视图渲染之前被调用，
    // 所以可以在这个方法中可以对Controller处理之后的ModelAndView对象进行操作
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    //实现一个interceptor拦截器类后，需要在配置中配置使它生效：实现 WebMvcConfigurerAdapter并重写 addInterceptors，
    // 同时在这个方法里设置要过滤的URL。
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Request and Response is completed");
    }
}
