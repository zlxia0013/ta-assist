package com.tc.ta.interfaces.http;

import com.tc.ta.common.web.CommonKeys;
import com.tc.ta.common.web.Json;
import com.tc.ta.common.web.ReturnCodes;
import com.tc.ta.core.user.bo.UserBo;
import com.tc.ta.core.user.pojo.User;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.util.exception.ComSystemException;
import com.tc.ta.util.framework.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SysLoginController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserBo userBo;

    public static final String URL_GOTO_LOGIN_PAGE = "/goto_login_page";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";

    @RequestMapping(value = URL_GOTO_LOGIN_PAGE)
    public String gotoLoginPage() {
        return "common/login";
    }

    @RequestMapping(value = URL_LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public Json login(String userName, String password) {
        Json json = new Json(true);
        try {
            User user = userBo.login(userName, password);
            getSession().setAttribute(CommonKeys.SESSION_USER, user);
        } catch (ComRuntimeException e) {
            json = new Json(e.getErrorCode(), e.getMessage());
        } catch (ComSystemException e) {
            log.error("error", e);
            json = new Json(e.getErrorCode(), "系统忙, 请稍后重试... ");
        } catch (Throwable e) {
            log.error("error", e);
            json = new Json(ReturnCodes.SYSTEM_EXCEPTION, "系统忙, 请稍后重试... ");
        }

        return json;
    }

    @RequestMapping(value = URL_LOGOUT, method = RequestMethod.GET)
    public String logout() {
        if (getSession() != null) {
            getSession().invalidate();
        }
        return "redirect:" + URL_GOTO_LOGIN_PAGE;
    }
}
