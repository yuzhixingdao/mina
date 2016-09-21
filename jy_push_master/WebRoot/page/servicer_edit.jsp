<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Access信息</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Servicer信息");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
			<form id="formId" class="form-horizontal form-horizontal-simple" action="<s:url value='/back/servicer_editDataSubmit.do' />" method="post" 
				onsubmit="return Form.submit('#formId');" enctype="multipart/form-data">
			
		    	<div class="formtitle"><span>Servicer信息</span></div>
		    	
		    	<input type="hidden" name="minaServicer.id" value="<s:property value='minaServicer.id' />" />
		    	
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>IP：</label>
	                <div class="controls">
	                  	<input name="minaServicer.ip" value="<s:property value='minaServicer.ip' />" type="text" class="dfinput" data-options="required:true"/>
					    <span name="msg" style="color:red;"></span>
	                </div>
	              </div>
	              
	              <div class="control-group span8">
	                <label class="control-label"><s></s>端口号：</label>
	                <div class="controls">
	                  	<input name="minaServicer.port" value="<s:property value='minaServicer.port' />" type="text" class="dfinput" data-options="required:true"/>
					    <span name="msg" style="color:red;"></span>
	                </div>
	              </div>
	              
	              <div class="control-group span8">
	                <label class="control-label"><s></s>状态：</label>
	                <div class="controls">
	                  	<input name="minaServicer.state" value="<s:property value='minaServicer.state' />" type="text" class="dfinput" data-options="required:true"/>
					    <span style="color:red;">0-下线,1-上线</span>
					    <span name="msg" style="color:red;"></span>
	                </div>
	              </div>
	              
	              <div class="control-group span8">
	                <label class="control-label"><s></s>ifcUrl：</label>
	                <div class="controls">
	                  	<input name="minaServicer.ifcUrl" value="<s:property value='minaServicer.ifcUrl' />" type="text" class="dfinput" data-options="required:true"/>
					    <span name="msg" style="color:red;"></span>
	                </div>
	              </div>
	              
	            </div>
	            
	            <div class="row">
	              <div class="form-actions offset3">
	                <button type="submit" class="button button-primary">提交</button>
	                <button type="reset" class="button">重置</button>
	                <input type="button" class="button" value="返 回" onclick="javascript:history.go(-1);"/>
	              </div>
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>