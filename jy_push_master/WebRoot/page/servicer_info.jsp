<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Servicer����</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Session����");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
	    	<div class="formtitle"><span>Servicer����</span></div>
	    	
	    	<form class="form-horizontal">
	            
	            <div class="row">
	            
	              <div class="control-group span9">
	                <label class="control-label"><s></s>IP��</label>
	                <div class="controls">
	                  <s:property value="minaServicer.ip" />
	                </div>
	              </div>
	              
	              <div class="control-group span9">
	                <label class="control-label"><s></s>�˿ںţ�</label>
	                <div class="controls">
	                  <s:property value="minaServicer.port" />
	                </div>
	              </div>
	              
	              <div class="control-group span9">
	                <label class="control-label"><s></s>״̬��</label>
	                <div class="controls">
	                  <s:property value="minaServicer.state" />
	                </div>
	              </div>
	              
	              <div class="control-group span9">
	                <label class="control-label"><s></s>session������</label>
	                <div class="controls">
	                  <s:property value="minaServicer.sessionNumber" />
	                </div>
	              </div>
	              
	              <div class="control-group span9">
	                <label class="control-label"><s></s>ifc_url��</label>
	                <div class="controls">
	                  <s:property value="minaServicer.ifcUrl" />
	                </div>
	              </div>
	              
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>