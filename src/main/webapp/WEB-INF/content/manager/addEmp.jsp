<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加新员工</title>
</head>
<body>
<%@include file="../header.jsp"%> 
<%@include file="mgrheader.jsp"%> 
<table width=780 align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
<tr>
<td>
请您输入新员工的资料：<br/>
<s:if test="tip!=null">
<div class="error">
	<s:property value="tip"/>
</div>
</s:if>
<div align="center">
<s:form action="processAdd">
	<!-- 数据绑定：对应AddEmpAction有一个Employee类型的成员变量，名为emp -->
	<s:textfield name="emp.name" label="员工用户名"/>
	<s:textfield name="emp.pass" label="员工密码"/>
	<s:textfield name="emp.salary" label="员工月薪"/>
	<s:token/>
	<tr><td colspan="2">
	<s:submit value="添加新员工" theme="simple"/>
	<s:reset  theme="simple" value="重新输入"/>
	</td></tr>
</s:form>
</div>
</td>
</tr>
</table>
<%@include file="../footer.jsp"%> 
</body>
</html>