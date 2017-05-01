<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工首页</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<%@include file="empheader.jsp"%>
	<table width="780" align="center"
		background="${pageContext.request.contextPath}/images/bodybg.jpg">
		<tr height="60">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><s:if test="tip!=null">
					<div class="mytitle">
						<s:property value="tip" />
					</div>
				</s:if></td>
		</tr>
		<tr height="80">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><s:property value="#session.user" />
				，欢迎您使用JavaEE简单工作流系统，您是普通员工</td>
		</tr>
		<tr height="60">
			<td>&nbsp;</td>
		</tr>
	</table>
	<%@include file="../footer.jsp"%>
</body>
</html>