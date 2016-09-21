<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Session����</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Session����");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
	    	<div class="formtitle"><span>Session����</span></div>
	    	
	    	<form class="form-horizontal">
	            
	            <div class="row">
	              <div class="control-group span9">
	                <label class="control-label"><s></s>sessionId��</label>
	                <div class="controls">
	                  <s:property value="minaSession.sessionId" />
	                </div>
	              </div>
	              <div class="control-group span9">
	                <label class="control-label"><s></s>token��</label>
	                <div class="controls">
	                  <s:property value="minaSession.token" />
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="control-group span9">
	                <label class="control-label"><s></s>���ӵ�IP��</label>
	                <div class="controls">
	                  <s:property value="minaSession.msIp" />
	                </div>
	              </div>
	              <div class="control-group span9">
	                <label class="control-label"><s></s>���ӵĶ˿ںţ�</label>
	                <div class="controls">
	                  <s:property value="minaSession.msPort" />
	                </div>
	              </div>
	              <div class="control-group span9">
	                <label class="control-label"><s></s>����ʱ�䣺</label>
	                <div class="controls">
	                  <s:date name="minaSession.connectDate" format="yyyy-MM-dd hh:mm:ss" />
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="control-group span20">
	                <label class="control-label" title="NO-USERID-OTHER"><s></s>�豸��</label>
	                <div class="controls">
	                  <s:property value="devices.deviceNo" /> - <s:property value="devices.userId" /> - <s:property value="devices.other" />
	                </div>
	              </div>
	            </div>
	            
	            
	        </form>
	        
		</div>
		
	</body>
</html>