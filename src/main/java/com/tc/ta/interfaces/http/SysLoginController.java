package com.tc.ta.interfaces.http;

import com.tc.ta.util.framework.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SysLoginController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass());

    public static final String URL_GOTO_LOGIN_PAGE = "/goto_login_page";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";

    @RequestMapping(value = URL_GOTO_LOGIN_PAGE)
    public String gotoLoginPage() {
        return "common/login";
    }


    @RequestMapping(value = URL_LOGOUT, method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
       if (request.getSession() !=null ){
           request.getSession().invalidate();
       }

        return "redirect:" + URL_GOTO_LOGIN_PAGE;
    }
}
