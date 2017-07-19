package com.tc.ta.interfaces.http;

import com.tc.ta.util.framework.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SysStartupController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass());

    public static final String URL_GOTO_INDEX_PAGE = "/goto_index_page";

    @RequestMapping(value = URL_GOTO_INDEX_PAGE)
    public String gotoIndexPage() {
        return "common/login";
    }


}
