<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%String path = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>left</title>
<link href="<%=path %>/library/uimaker/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=path %>/library/uimaker/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//�����л�
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>��&nbsp;&nbsp;��</div>
    
    <dl class="leftmenu">
        
	    <dd>
		    <div class="title"><span><img src="<%=path %>/library/uimaker/images/leftico01.png" /></span>ϵͳ����</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="<%=path %>/back/access_queryPage.do" target="rightFrame">Access����</a><i></i></li>
		        <li><cite></cite><a href="<%=path %>/back/servicer_queryPage.do" target="rightFrame">Service����</a><i></i></li>
		        <li><cite></cite><a href="<%=path %>/back/session_queryPage.do" target="rightFrame">Session����</a><i></i></li>
		        <li><cite></cite><a href="<%=path %>/back/message_queryPage.do" target="rightFrame">��Ϣ����</a><i></i></li>
	        </ul>
	    </dd>
	    
	    <dd>
		    <div class="title"><span><img src="<%=path %>/library/uimaker/images/leftico01.png" /></span>����վ</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="#">����</a><i></i></li>
		    </ul>
	    </dd>   
    
    </dl>
    
</body>
</html>
