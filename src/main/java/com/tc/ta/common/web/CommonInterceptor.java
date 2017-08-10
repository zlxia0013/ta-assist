package com.tc.ta.common.web;


import com.tc.ta.core.privilege.CheckPrivilegeResultEnum;
import com.tc.ta.core.privilege.bo.PrivilegeBo;
import com.tc.ta.core.user.pojo.User;
import com.tc.ta.interfaces.http.SysLoginController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Jack on 2017/2/26.
 */

public class CommonInterceptor extends HandlerInterceptorAdapter {
    protected Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private PrivilegeBo privilegeBo;

	/*
     * 利用正则映射到需要拦截的路径

    private String mappingURL;

    public void setMappingURL(String mappingURL) {
               this.mappingURL = mappingURL;
    }
  */

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p>
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if ("/".equals(contextPath)) {
            contextPath = "";
        }
        String url = requestUri.substring(contextPath.length());

        log.info("requestUri:" + requestUri);
        log.info("contextPath:" + contextPath);
        log.info("url:" + url);

        User userInfo = (User) request.getSession().getAttribute(CommonKeys.SESSION_USER);

        boolean rtn = true;
        //
        CheckPrivilegeResultEnum result = privilegeBo.checkPrivilege(userInfo, url);
        switch (result) {
            case RETURN_TRUE:
                rtn = true;
                break;
            case NEED_LOGIN:
                response.sendRedirect(SysLoginController.URL_GOTO_LOGIN_PAGE);
//                request.getRequestDispatcher(SysLoginController.URL_GOTO_LOGIN_PAGE).forward(request, response);
                rtn = false;
                break;
            case NO_AUTHORITY:
                response.sendRedirect("/error/no_access.html");
//                request.getRequestDispatcher("/error/no_access.html").forward(request, response);
                rtn = false;
                break;
        }

        return rtn;
    }
}
