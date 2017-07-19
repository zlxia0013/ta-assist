<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%
        String path = request.getContextPath();
        path = "/".equals(path)?"":path;
        response.sendRedirect(path + "/goto_index_page");
    %>
</head>
<body>
</body>
</html>

