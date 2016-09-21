<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%String path = request.getContextPath();%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />

<title>欢迎登录管理系统</title>

<link href="<%=path %>/library/uimaker/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=path %>/library/uimaker/js/jquery.js"></script>

<script src="<%=path %>/library/uimaker/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(<%=path %>/library/uimaker/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

	<div class="logintop">    
	    <span>欢迎登录后台管理界面平台</span>    
	    <ul>
		    <li><a href="#">帮助</a></li>
		    <li><a href="#">关于</a></li>
	    </ul>    
    </div>
    
    <div class="loginbody">
    
	    <span class="systemlogo"></span> 
	    
	    <div class="loginbox">
		    <form action="<%=path %>/login.do" method="post">
			    <ul>
				    <li><input name="loginName" type="text" class="loginuser" value="" /></li>
				    <li><input name="password" type="password" class="loginpwd" value="" /></li>
				    <li><input name="" type="submit" class="loginbtn" value="登录" /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
				    <li style="color:red; margin-top:-10px;">${msg }</li>
			    </ul>
		    </form>
	    </div>
    
    </div>
    
    <div class="loginbm">版权所有 北京精友时代信息技术发展有限公司 </div>
	
</body>

</html>
