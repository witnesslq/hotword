<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <jsp:include page="head.jsp"/>
	<body>
	<%
		String datetime=new SimpleDateFormat("yyyy-MM-ddHH").format(Calendar.getInstance().getTime());
	%>
	<form action="hotword.do">
		时间：<input name="dataHour" value=<%=datetime%>>
		TopN：<select name="topN">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="50">50</option>
	</select>
		<input type="submit" value="查询">
	</form>

	</body>
</html>

