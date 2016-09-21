<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Session�б�</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Session�б�");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
		
			<form id="form_page" class="form-horizontal" action="<s:url value="/back/session_queryPage.do" />" method="post">
			
			<div>
	            <div class="control-group span7">
	              <label class="control-label">sessionId/token��</label>
	              <div class="controls">
	                <input type="text" name="minaSession.token" value="<s:property value='minaSession.token' />" class="control-text"/>
	              </div>
	            </div>
	            <div class="form-actions span5">
	              <button id="btnSearch" type="submit" onclick="Page.submit();" class="button button-primary">��ѯ</button>
	            </div>
	            
            </div>
			<div style="border: 1px solid #fff;">
				<table class="table table-bordered">
					<thead>
						<tr>
			    			<th colspan="12" style="text-align: right;" align="right">
				            <ul class="toolbar">
				              <li><input type="button" class="button button-info" onclick="Form.removes('<s:url value="/back/session_removeData.do" />','#form_page [name=\'ids\']');" value="ɾ��"/></li>
				            </ul>
				            </th>
				        </tr>
						<tr>
					        <th style="text-align: center; width:35px;">���</th>
					        <th style="text-align: center; width:30px;"><input id="checkboxAll" type="checkbox" value="" onclick="Base.checkboxAll(this,'ids')"/></th>
					        <th style="text-align: center; width:100px;">sessionId/token</th>
					        <th style="text-align: center; width:100px;">Զ�̶�</th>
					        <th style="text-align: center; width:100px;">���ض�</th>
					        <th style="text-align: center; width:100px;">����ʱ��</th>
					        <th style="text-align: center; width:100px;">�Ƿ�����</th>
					        <th style="text-align: center; width:100px;">�豸 NO-USERID-OTHER</th>
					        <th width="150" style="text-align: center; width:100px;">����</th>
				        </tr>
					</thead>
					<tbody>
						<s:iterator value="page.List" status="status">
					        <tr>
						        <td style="text-align: center;"><s:property value="#status.index+1" /></td>
						        <td style="text-align: center;"><input id="checkboxId" name="ids" type="checkbox" value="<s:property value='session.id' />" /></td>
						        <td style="text-align: center;">
						        	<label title='<s:property value="session.sessionId" />'><s:property value="session.sessionId" /></label>
						        </td>
						        
						        <td style="text-align: center;"><s:property value="session.remoteIp" />:<s:property value="session.remotePort" /></td>
						        <td style="text-align: center;"><s:property value="session.locaIp" />:<s:property value="session.locaPort" /></td>
						        
						        <td style="text-align: center;"><s:date name="session.connectDate" format="yyyy-MM-dd hh:mm:ss" /></td>
						        <td style="text-align: center;"><s:property value="session.sessionState" /></td>
						        <td style="text-align: center;" title="${devices.deviceNo}-${devices.userId}-${devices.other}">
						        	<label title='<s:property value="devices.deviceNo" />'><tag:string value="${devices.deviceNo }" operation="substringLast" sublength="5" /></label> - 
						        	<label title='<s:property value="devices.userId" />'><tag:string value="${devices.userId }" operation="substringLast" sublength="5" /></label> - 
						        	<label title='<s:property value="devices.other" />'><s:property value="devices.other" /></label>
						        </td>
						        <td style="text-align: center;">
						        	<a href="<s:url value='/back/session_queryData.do' />?id=<s:property value='session.id' />" class="tablelink">����</a>
						        </td>
					        </tr>
				        </s:iterator>
					</tbody>
				</table>
				
				<tag:page page="${page }" formId="form_page"></tag:page>
			
			</div>
			
			</form>
			
			<br/>
		</div>
		
	</body>
</html>