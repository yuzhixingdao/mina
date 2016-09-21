<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Access����</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Access����");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
	    	<div class="formtitle"><span>Access����</span></div>
	    	
	    	<form class="form-horizontal">
	            
	            <div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>AccessKey��</label>
	                <div class="controls">
	                  <s:property value="access.accessKey" />
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>AccessId��</label>
	                <div class="controls">
	                  <s:property value="access.accessId" />
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>SecretKey��</label>
	                <div class="controls">
	                  <s:property value="access.secretKey" />
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>ƽ̨���ͣ�</label>
	                <div class="controls">
	                  <s:property value='dictionaryInit.getDataByTypeCode("TERMINAL_TYPE", access.terminalType)' />
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>����ʱ�䣺</label>
	                <div class="controls">
	                  <s:date name="access.createDate" format="yyyy-MM-dd hh:mm:ss" />
	                </div>
	              </div>
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>