<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>��Ϣ����</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("��Ϣ����");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
	    	<div class="formtitle"><span>��Ϣ����</span></div>
	    	
	    	<form class="form-horizontal">
	            
	            <div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>�״η���ʱ�䣺</label>
	                <div class="controls">
	                  <s:date name="message.firstDate" format="yyyy-MM-dd hh:mm:ss" />
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>����ʱ�䣺</label>
	                <div class="controls">
	                  <s:date name="message.sendDate" format="yyyy-MM-dd hh:mm:ss" />
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>�Ƿ���գ�</label>
	                <div class="controls">
	                  <s:property value='dictionaryInit.getDataByTypeCode("MESSAGE_SUCCESS_FLAG", message.successFlag)' />
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>����ʱ�䣺</label>
	                <div class="controls">
	                  <s:date name="message.receiveDate" format="yyyy-MM-dd hh:mm:ss" />
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="control-group span20">
	                <label class="control-label"><s></s>�������ݣ�</label>
	                <div class="controls">
	                  <s:property value="message.messageContent" />
	                </div>
	              </div>
	            </div>
	            
	            
	        </form>
	        
		</div>
		
	</body>
</html>