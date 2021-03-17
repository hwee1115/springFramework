<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%response.sendRedirect(application.getContextPath()+ "/home");%>

<%-- 방법2 --%>

<%-- <c:redirect url="/home"/> --%>