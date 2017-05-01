<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经理人员电子打卡</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%> 
<table width="780" align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
  <tr>
	<td colspan="3"><br><br><div class="mytitle">电子打卡系统</div></td>
  </tr>
  <tr>
	<td colspan="3">
		<br><br><br>
<!-- 当punchIsValid为1、3时，可上班打卡 -->
<s:if test="punchIsValid==1 
	|| punchIsValid==3">
<s:form action="managerCome">
	<s:submit key="come.punch"/>
</s:form>
</s:if>
		<br><br><br>
	</td>
  </tr>
  <tr>
	<td colspan="3">
<!-- 当punchIsValid为2、3时，可下班打卡 -->	
<s:if test="punchIsValid==2
	|| punchIsValid==3">
<s:form action="managerLeave">
	<s:submit  key="leave.punch"/>
</s:form>
</s:if>
		<br>
	</td>
  </tr>
</TABLE>

<%@include file="../footer.jsp"%> 
</body>
</html>