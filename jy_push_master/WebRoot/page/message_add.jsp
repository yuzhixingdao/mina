<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>������Ϣ</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("������Ϣ");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
    
			<form class="form-horizontal form-horizontal-simple" action="<s:url value='/back/ntConLp_addIssuanceDataSubmit' />" method="post" onsubmit="return formSubmit();" enctype="multipart/form-data">
			
		    	<div class="formtitle"><span>��Ϣ��Ϣ</span></div>
		    	
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>�״η���ʱ�䣺</label>
	                <div class="controls">
	                	<input name="message." value="" type="text" data-options="required:true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:150px;"/>
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>���֤�ţ�</label>
	                <div class="controls">
	                	<input name="message." value="" type="text" class="dfinput" data-options="required:true"/>
	                </div>
	              </div>
	            </div>
	            
				<div class="row">
	              <div class="control-group span8">
	                <label class="control-label"><s></s>�ƶ��绰��</label>
	                <div class="controls">
	                  <span id="contractorDTO_conPhone"></span>
	                </div>
	              </div>
	              <div class="control-group span8">
	                <label class="control-label"><s></s>�̶��绰��</label>
	                <div class="controls">
	                  <span id="contractorDTO_conLandlineTelephone"></span>
	                </div>
	              </div>
	            </div>
	            
	        </form>
	        
		</div>
		
	</body>
</html>