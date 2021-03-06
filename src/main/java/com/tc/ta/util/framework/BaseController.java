package com.tc.ta.util.framework;

import com.tc.ta.common.web.CommonKeys;
import com.tc.ta.core.user.pojo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class BaseController {

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public User getCurUser() {
		return (User) getSession().getAttribute(CommonKeys.SESSION_USER);
	}

	public Integer getCurUserId() {
		User curUser = getCurUser();
		return curUser != null ? curUser.getId() : 0;
	}
}
