<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%String path = request.getContextPath() + "/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>管理系统</title>
</head>

<!--<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">-->
<!--  <frame src="<%=path %>top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />-->
<!--  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">-->
<!--    <frame src="<%=path %>left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />-->
<!--    <frame src="<%=path %>main.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />-->
<!--  </frameset>-->
<!--</frameset>-->

<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=path %>top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=path %>left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frameset rows="40,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=path %>info.jsp" name="infoFrame" id="infoFrame" title="infoFrame" />
		<frame src="<%=path %>main.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
  </frameset>
</frameset>

</html>
