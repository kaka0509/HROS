<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提出异动申请</title>
</head>
<body>
<table width="780" align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
<tr>
<td>
<div align="center"> 
<s:form action="processApp">
  <tr bgcolor="#e1e1e1" >
	<td colspan="2"><div class="mytitle">
	当前用户：<s:property value="#session.user"/></div></td> 
  </tr>
  <tr bgcolor="#e1e1e1" >
	<td colspan="2">请填写异动申请</td> 
  </tr> 
  <!-- ${param.attid}：取出url中attid的值 -->
	<input type="hidden" name="attId" value="${param.attid}"/> 
	<s:select name="typeId" label="申请类别" labelposition="left"
	list="types"
	listKey="id"
	listValue="name"/>	
	<s:textarea name="reason"  rows="5" cols = "20" label="申请理由"/>
	<tr><td colspan="2">
	<s:submit value="提交申请" theme="simple"/>
	<s:reset  theme="simple" value="重新填写"/>
	</td></tr>
</s:form>
</div>
</td>
</tr>
</table>
<%@include file="../footer.jsp"%>
</body>
</html>