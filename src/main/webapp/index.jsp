<%@ page import="com.tc.ta.interfaces.http.SysStartupController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%
        String path = request.getContextPath();
        path = "/".equals(path)?"":path;
        response.sendRedirect(path + SysStartupController.URL_GOTO_INDEX_PAGE);
    %>
</head>
<body>
</body>
</html>

