package com.tc.ta.interfaces.http;

import com.tc.ta.common.web.CommonKeys;
import com.tc.ta.common.web.Json;
import com.tc.ta.common.web.JspKeys;
import com.tc.ta.core.user.bo.UserBo;
import com.tc.ta.core.user.dto.UserDto;
import com.tc.ta.core.user.dto.UserMainPageModel;
import com.tc.ta.core.user.dto.UserSearchCondition;
import com.tc.ta.core.user.pojo.User;
import com.tc.ta.common.web.ReturnCodes;
import com.tc.ta.util.exception.ComRuntimeException;
import com.tc.ta.util.exception.ComSystemException;
import com.tc.ta.util.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

@Controller
public class UserController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private UserBo userBo;

    public static final String URL_ENTITY = "/user";
    public static final String URL_GOTO_MAIN_PAGE = URL_ENTITY + "/goto_main_page";
    public static final String URL_GOTO_ADD_PAGE = URL_ENTITY + "/goto_add_page";
    public static final String URL_GOTO_UPDATE_PAGE = URL_ENTITY + "/goto_update_page";
    public static final String URL_GOTO_UPDATE_PASSWORD_PAGE = URL_ENTITY + "/goto_update_password_page";

    public static final String URL_ADD = URL_ENTITY + "/add";
    public static final String URL_UPDATE = URL_ENTITY + "/update";
    public static final String URL_UPDATE_PASSWORD = URL_ENTITY + "/update_password";
    public static final String URL_DELETE = URL_ENTITY + "/delete";

    @RequestMapping(value = URL_GOTO_MAIN_PAGE, method = RequestMethod.GET)
    public ModelAndView gotoMainPage(UserSearchCondition usc, HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        //search client list
        List<UserDto> userList = userBo.searchDto(usc);

        Integer totalRecordCount = userBo.searchCnt(usc);

        UserMainPageModel model = new UserMainPageModel();
        model.setObjList(userList);
        model.setTotalRecordCount(totalRecordCount);
        model.setCurPage(usc.getCurPage());
        model.setPageSize(usc.getPageSize());

        ModelAndView modelAndView = new ModelAndView("user/user_main");
        modelAndView.addObject(JspKeys.JspParam_SessionUserInfo, userInfo);
        modelAndView.addObject(JspKeys.JspParam_UserMainPageModel, model);
        return modelAndView;
    }

    @RequestMapping(value = URL_GOTO_ADD_PAGE, method = RequestMethod.GET)
    public ModelAndView gotoAddPage(HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        ModelAndView modelAndView = new ModelAndView("user/user_add");
        modelAndView.addObject(JspKeys.JspParam_SessionUserInfo, userInfo);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = URL_ADD, method = RequestMethod.POST)
    public Json add(User user, HttpSession session) {
        Json json = new Json();
        try {
            User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);
            user.setAddUserId(userInfo.getId());
            user.setAddTime(new Date());
            userBo.add(user);
            json.setSuccess(true);
        } catch (ComRuntimeException e) {
            json = new Json(e.getErrorCode(), e.getMessage());
        } catch (ComSystemException e) {
            log.error("error", e);
            json = new Json(e.getErrorCode(), "系统忙... " + e.getMessage());
        } catch (Throwable e) {
            log.error("error", e);
            json = new Json(ReturnCodes.SYSTEM_EXCEPTION, "系统忙... " + e.getMessage());
        }
        return json;
    }

    @RequestMapping(value = URL_GOTO_UPDATE_PAGE, method = RequestMethod.GET)
    public ModelAndView gotoUpdatePage(Integer userId, HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        User user = userBo.getById(userId);

        ModelAndView modelAndView = new ModelAndView("user/user_update");
        modelAndView.addObject(JspKeys.JspParam_SessionUserInfo, userInfo);
        modelAndView.addObject(JspKeys.JspParam_UserInfo, user);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = URL_UPDATE, method = RequestMethod.POST)
    public Json update(User user) {
        Json json = new Json();
        try {
            userBo.update(user);
            json.setSuccess(true);
        } catch (ComRuntimeException e) {
            json = new Json(e.getErrorCode(), e.getMessage());
        } catch (ComSystemException e) {
            log.error("error", e);
            json = new Json(e.getErrorCode(), "系统忙... " + e.getMessage());
        } catch (Throwable e) {
            log.error("error", e);
            json = new Json(ReturnCodes.SYSTEM_EXCEPTION, "系统忙... " + e.getMessage());
        }
        return json;
    }

    @RequestMapping(value = URL_GOTO_UPDATE_PASSWORD_PAGE, method = RequestMethod.GET)
    public ModelAndView gotoUpdatePwdPage(HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        ModelAndView modelAndView = new ModelAndView("user/user_update_pwd");
        modelAndView.addObject(JspKeys.JspParam_SessionUserInfo, userInfo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = URL_UPDATE_PASSWORD, method = RequestMethod.POST)
    public Json updatePwd(String oldPwd, String newPwd, HttpSession session) {
        Json json = new Json();
        try {
            User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);
            userBo.updatePwd(userInfo.getName(), oldPwd, newPwd);
            json.setSuccess(true);
        } catch (ComRuntimeException e) {
            json = new Json(e.getErrorCode(), e.getMessage());
        } catch (ComSystemException e) {
            log.error("error", e);
            json = new Json(e.getErrorCode(), "系统忙... " + e.getMessage());
        } catch (Throwable e) {
            log.error("error", e);
            json = new Json(ReturnCodes.SYSTEM_EXCEPTION, "系统忙... " + e.getMessage());
        }
        return json;
    }


    @ResponseBody
    @RequestMapping(value = URL_DELETE, method = RequestMethod.POST)
    public Json delete(Integer userId) {
        Json json = new Json();
        try {
            userBo.delete(userId);
            json.setSuccess(true);
        } catch (ComRuntimeException e) {
            json = new Json(e.getErrorCode(), e.getMessage());
        } catch (ComSystemException e) {
            log.error("error", e);
            json = new Json(e.getErrorCode(), "系统忙... " + e.getMessage());
        } catch (Throwable e) {
            log.error("error", e);
            json = new Json(ReturnCodes.SYSTEM_EXCEPTION, "系统忙... " + e.getMessage());
        }
        return json;
    }
}
