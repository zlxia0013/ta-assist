package com.tc.ta.interfaces.http;

import com.tc.ta.common.web.CommonJspKeys;
import com.tc.ta.common.web.CommonKeys;
import com.tc.ta.common.web.Json;
import com.tc.ta.common.web.ReturnCodes;
import com.tc.ta.core.fund.FundJspKeys;
import com.tc.ta.core.fund.bo.FundBo;
import com.tc.ta.core.fund.dto.FundDto;
import com.tc.ta.core.fund.dto.FundMainPageModel;
import com.tc.ta.core.fund.dto.FundSearchCondition;
import com.tc.ta.core.fund.pojo.Fund;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class FundController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private FundBo fundBo;

    public static final String URL_ENTITY = "/fund";
    public static final String URL_GOTO_MAIN_PAGE = URL_ENTITY + "/goto_main_page";

    public static final String URL_GOTO_ADD_UPDATE_INDEX_PAGE = URL_ENTITY + "/goto_add_update_index_page";

    public static final String URL_GOTO_ADD_UPDATE_PAGE = URL_ENTITY + "/goto_add_update_page";
    public static final String URL_ADD_UPDATE = URL_ENTITY + "/add_update";

    public static final String URL_DELETE = URL_ENTITY + "/delete";

    @RequestMapping(value = URL_GOTO_MAIN_PAGE, method = RequestMethod.GET)
    public ModelAndView gotoMainPage(FundSearchCondition usc, HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        //search client list
        List<FundDto> fundList = fundBo.searchDto(usc);

        Integer totalRecordCount = fundBo.searchCnt(usc);

        FundMainPageModel model = new FundMainPageModel();
        model.setObjList(fundList);
        model.setTotalRecordCount(totalRecordCount);
        model.setCurPage(usc.getCurPage());
        model.setPageSize(usc.getPageSize());

        ModelAndView modelAndView = new ModelAndView("fund/fund_main");
        modelAndView.addObject(CommonJspKeys.JspParam_SessionUserInfo, userInfo);
        modelAndView.addObject(FundJspKeys.JspParam_FundMainPageModel, model);
        return modelAndView;
    }

    @RequestMapping(value = URL_GOTO_ADD_UPDATE_INDEX_PAGE, method = RequestMethod.GET)
    public ModelAndView URL_GOTO_ADD_UPDATE_INDEX_PAGE(HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        ModelAndView modelAndView = new ModelAndView("fund/fund_add_update_index");
        modelAndView.addObject(CommonJspKeys.JspParam_SessionUserInfo, userInfo);
        return modelAndView;
    }

    @RequestMapping(value = URL_GOTO_ADD_UPDATE_PAGE, method = RequestMethod.GET)
    public ModelAndView URL_GOTO_ADD_UPDATE_PAGE(HttpSession session) {
        User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);

        ModelAndView modelAndView = new ModelAndView("fund/fund_add_update");
        modelAndView.addObject(CommonJspKeys.JspParam_SessionUserInfo, userInfo);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = URL_ADD_UPDATE, method = RequestMethod.POST)
    public Json add(Fund fund, HttpSession session) {
        Json json = new Json();
        try {
            User userInfo = (User) session.getAttribute(CommonKeys.SESSION_USER);
            fund.setAddUserId(userInfo.getId());
            fund.setAddTime(new Date());
            fundBo.add(fund);
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
    public Json delete(Integer fundId) {
        Json json = new Json();
        try {
            fundBo.delete(fundId);
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
