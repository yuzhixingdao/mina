<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Access��Ϣ</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Access��Ϣ");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
			<form id="formId" class="form-horizontal form-horizontal-simple" action="<s:url value='/back/access_editDataSubmit.do' />" method="post" 
				onsubmit="return Form.submit('#formId');" enctype="multipart/form-data">
			
		    	<div class="formtitle"><span>Access��Ϣ</span></div>
		    	
		    	<input type="hidden" name="access.id" value="<s:property value='access.id' />" />
		    	
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>ƽ̨���ͣ�</label>
	                <div class="controls">
	                  	<select name="access.terminalType" class="select1" data-options="{required:true}">
				    		<option value="" selected="selected">��ѡ��</option>
				    		<tag:dict operation="option" dictType="TERMINAL_TYPE" selected="${access.terminalType }"></tag:dict>
					    </select>
					    <span name="msg" style="color:red;"></span>
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="form-actions offset3">
	                <button type="submit" class="button button-primary">�ύ</button>
	                <button type="reset" class="button">����</button>
	                <input type="button" class="button" value="�� ��" onclick="javascript:history.go(-1);"/>
	              </div>
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>