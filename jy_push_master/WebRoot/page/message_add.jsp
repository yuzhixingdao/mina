<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>发送消息</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("发送消息");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
			<form class="form-horizontal form-horizontal-simple" action="<s:url value='/back/ntConLp_addIssuanceDataSubmit' />" method="post" onsubmit="return formSubmit();" enctype="multipart/form-data">
			
		    	<div class="formtitle"><span>消息信息</span></div>
		    	
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>首次发送时间：</label>
	                <div class="controls">
	                	<input name="message." value="" type="text" data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:150px;"/>
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>身份证号：</label>
	                <div class="controls">
	                	<input name="message." value="" type="text" class="dfinput" data-options="required:true"/>
	                </div>
	              </div>
	            </div>
	            
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>移动电话：</label>
	                <div class="controls">
	                  <span id="contractorDTO_conPhone"></span>
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>固定电话：</label>
	                <div class="controls">
	                  <span id="contractorDTO_conLandlineTelephone"></span>
	                </div>
	              </div>
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>